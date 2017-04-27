package com.gcit.libsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gcit.libsystem.entity.Book;

public class BookDao extends BaseDao{
	
	public BookDao(Connection conn) {
		super(conn);
	}

	public void addBook(Book book) throws SQLException {
		String  addBook  = "INSERT INTO tbl_book (title) VALUE (?)";
		List<?> bookInfo = Arrays.asList(book.getTitle());
		save(addBook, bookInfo);
	}
	
	public void updateBook(Book book) throws SQLException{
		String  updateBook = "UPDATE tbl_book SET title=? WHERE bookId=?";
		List<?> bookInfo   = Arrays.asList(book.getTitle(), book.getBookId());
		save(updateBook, bookInfo);
	}
	
	public void deleteBook(Book book) throws SQLException{
		String  deleteBook = "DELETE * FROM tbl_book WHERE bookId=?";
		List<?> bookInfo   = Arrays.asList(book.getBookId());
		save(deleteBook, bookInfo);
	}
	
	public void addBookAuthors(Integer bookID, Integer authorID) throws SQLException {
		String  addBookAuthor  = "INSERT INTO tbl_book_authors VALUE (?,?)";
		List<?> bookAuthorInfo = Arrays.asList(bookID, authorID);
		save(addBookAuthor, bookAuthorInfo);
	}
	
	public void updateBookPublisher(Integer publisherID, Book book) throws SQLException {
		String  updateBookPublisher  = "UPDATE tbl_book SET pubId=? WHERE bookId=?";
		List<?> bookPublisherInfo = Arrays.asList(publisherID, book.getBookId());
		save(updateBookPublisher,bookPublisherInfo);
	}
	
	public void updateBookAuthors(Integer bookID, Integer authorID) throws SQLException {
		String  updateBookAuthor  = "UPDATE tbl_book_authors SET authorId=? WHERE bookId=?";
		List<?> bookAuthorInfo = Arrays.asList(authorID, bookID);
		save(updateBookAuthor, bookAuthorInfo);
	}

	public Integer addBookWithID(Book book) throws SQLException{
		String  addBook = "INSERT INTO tbl_book (title) VALUE (?)";
		List<?> bookID  = Arrays.asList(book.getBookId());
		return saveWithID(addBook, bookID);
	}
	
	public List<Book> readAllBook() throws SQLException{
		String readBook = "SELECT * FROM tbl_book";
		return read(readBook,null);
	}
	
	public Book readBook(Integer bookID) throws SQLException{
		String  readBook = "SELECT * FROM tbl_book WHERE bookId=?";
		List<?> bookInfo = Arrays.asList(bookID);
		List<Book> books = read(readBook, bookInfo);
		if (books != null && !books.isEmpty()) {
			return books.get(0);
		}
		return null;
	}
	
	public List<Book> readBook(String bookName) throws SQLException{
		String readBook  = "SELECT * FROM tbl_book WHERE title LIKE ?";
		List<?> bookInfo = Arrays.asList(bookName);
		return read(readBook,bookInfo);
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException{
		List<Book> books  = new ArrayList<>();
		AuthorDao  adao   = new AuthorDao(conn);
		GenreDao   gdao   = new GenreDao(conn);	
		String readAuthor = "SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId=?)";
		String readGenre  = "SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId=?)";
		while(rs.next()){
			Book book = new Book();
			book.setTitle(rs.getString("title"));
			book.setBookId(rs.getInt("bookId"));
			List<?> bookID = Arrays.asList(book.getBookId());
			book.setAuthors(adao.readOnly(readAuthor, bookID));
			book.setGenres(gdao.readOnly(readGenre, bookID));
			books.add(book);
		}
		return books;
	}

	@Override
	public List<Book> extractDataReadOnly(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			Book book = new Book();
			book.setTitle(rs.getString("title"));
			book.setBookId(rs.getInt("bookId"));
			books.add(book);
		}
		return books;
	}

	@Override
	protected <T> T extractSingleData(ResultSet rs) throws SQLException {
		return null;
	}
}
