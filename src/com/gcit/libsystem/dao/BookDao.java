package com.gcit.libsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gcit.libsystem.entity.Book;
import com.gcit.libsystem.entity.Publisher;

public class BookDao extends BaseDao{
	
	public BookDao(Connection conn) {
		super(conn);
	}

	public void addBook(Book book) throws SQLException {
		String  addBook  = "INSERT INTO tbl_book (title, pubId) VALUE (?,?)";
		List<?> bookInfo = Arrays.asList(book.getTitle(),book.getPublisher().getPublisherId());
		save(addBook, bookInfo);
	}
	
	public void updateBook(Book book) throws SQLException{
		String  updateBook = "UPDATE tbl_book SET title=? WHERE bookId=?";
		List<?> bookInfo   = Arrays.asList(book.getTitle(), book.getBookId());
		save(updateBook, bookInfo);
	}
	
	public void deleteBook(Book book) throws SQLException{
		String  deleteBook = "DELETE FROM tbl_book WHERE bookId=?";
		List<?> bookInfo   = Arrays.asList(book.getBookId());
		save(deleteBook, bookInfo);
	}
	
	public void addBookAuthors(Integer bookID, Integer authorID) throws SQLException {
		String  addBookAuthor  = "INSERT INTO tbl_book_authors VALUE (?,?)";
		List<?> bookAuthorInfo = Arrays.asList(bookID, authorID);
		save(addBookAuthor, bookAuthorInfo);
	}
	
	public void addBookGenres(Integer genreID, Integer bookID) throws SQLException {
		String  addBookGenres  = "INSERT INTO tbl_book_genres VALUE (?,?)";
		List<?> bookGenreInfo = Arrays.asList(genreID, bookID);
		save(addBookGenres, bookGenreInfo);
	}
	
	public void updateBookGenres(Integer genreID, Integer bookID) throws SQLException{
		String  updateBookGenres = "UPDATE tbl_book_genres SET genre_id=? WHERE bookId=?";
		List<?> bookGenreInfo = Arrays.asList(genreID, bookID);
		save(updateBookGenres,bookGenreInfo);
	}
	
	public void deleteBookGenres(Book book) throws SQLException{
		String  deleteBookGenres = "DELETE FROM tbl_book_genres WHERE bookId=?";
		List<?> bookGenreInfo = Arrays.asList(book.getBookId());
		save(deleteBookGenres,bookGenreInfo);
	}
	
	public void addBookGenres(Book book) throws SQLException{
		for (int i = 0 ; i < book.getGenres().size(); i++){
			addBookGenres(book.getGenres().get(i).getGenreId(), book.getBookId());
		}
	}
	
	public void updateBookPublisher(Book book) throws SQLException {
		String  updateBookPublisher  = "UPDATE tbl_book SET pubId=? WHERE bookId=?";
		List<?> bookPublisherInfo = Arrays.asList(book.getPublisher().getPublisherId(), book.getBookId());
		save(updateBookPublisher,bookPublisherInfo);
	}
	
	public void deleteBookAuthors(Book book) throws SQLException {
		String  deleteBookAuthor  = "DELETE FROM tbl_book_authors WHERE bookId=?";
		List<?> bookAuthorInfo = Arrays.asList(book.getBookId());
		save(deleteBookAuthor, bookAuthorInfo);
	}
	
	public void addBookAuthors(Book book) throws SQLException {
		for (int i = 0 ; i < book.getAuthors().size(); i++){
			addBookAuthors(book.getBookId(), book.getAuthors().get(i).getAuthorID());
		}
	}

	public Integer addBookReplyID(Book book) throws SQLException{
		String  addBook = "INSERT INTO tbl_book (title, pubId) VALUE (?,?)";
		List<?> bookID  = Arrays.asList(book.getTitle(), book.getPublisher().getPublisherId());
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
		List<Book> books     = new ArrayList<>();
		AuthorDao  adao      = new AuthorDao(conn);
		GenreDao   gdao      = new GenreDao(conn);	
		PublisherDao pdao    = new PublisherDao(conn);
		String readAuthor    = "SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId=?)";
		String readGenre     = "SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId=?)";
		String readPublisher = "SELECT * FROM tbl_publisher p JOIN tbl_book b ON (p.publisherId=b.pubId) WHERE bookId=?";
		while(rs.next()){
			Book book = new Book();
			book.setTitle(rs.getString("title"));
			book.setBookId(rs.getInt("bookId"));
			List<?> bookID = Arrays.asList(book.getBookId());
			book.setPublisher((Publisher)pdao.readOnly(readPublisher, bookID).get(0));
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
