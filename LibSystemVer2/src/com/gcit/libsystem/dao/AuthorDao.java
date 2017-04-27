package com.gcit.libsystem.dao;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.Author;

public class AuthorDao extends BaseDao {
	
	public AuthorDao(Connection conn) {
		super(conn);
	}
	
	public void addAuthor(Author author) throws SQLException {
		String  addAuthor   = "INSERT INTO tbl_author (authorName) VALUE (?)";
		List<?> authorsInfo = Arrays.asList(author.getAuthorName());
		save(addAuthor, authorsInfo);
	}
	
	public void updateAuthor(Author author) throws SQLException{
		String  updateAuthor = "UPDATE tbl_author SET authorName=? WHERE authorId=?";
		List<?> authorsInfo  = Arrays.asList(author.getAuthorID());
		save(updateAuthor, authorsInfo);
	}
	
	public void deleteAuthor(Author author) throws SQLException{
		String  deleteAuthor = "DELETE * FROM tbl_author WHERE authorId=?";
		List<?> authorsInfo  = Arrays.asList(author.getAuthorID());
		save(deleteAuthor, authorsInfo);
	}
	
	public List<Author> readAllAuthors() throws SQLException{
		String readAuthor = "SELECT * FROM tbl_author";
		return read(readAuthor, null);
	}
	
	public Author readAuthor(Integer authorID) throws SQLException{
		String  readAuthor   = "SELECT * FROM tbl_author WHERE authorId=?";
		List<?> authorsInfo  = Arrays.asList(authorID);
		List<Author> authors = read(readAuthor, authorsInfo);
		if(authors!=null && !authors.isEmpty()){
			return authors.get(0);
		}
		return null;
	}
	
	public List<Author> readAuthor(String authorName) throws SQLException{
		String  readAuthor  = "SELECT * FROM tbl_author WHERE authorName LIKE ?";
		List<?> authorsInfo = Arrays.asList(authorName);
		return read(readAuthor,authorsInfo);
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		BookDao bookDao = new BookDao(conn);
		List<Author> authors = new ArrayList<>();
		String readBook = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId=?";
		while(rs.next()){
			Author author = new Author();
			author.setAuthorID(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			List<?> authorsInfo = Arrays.asList(author.getAuthorID());
			author.setBooks(bookDao.readOnly(readBook,authorsInfo));
			authors.add(author);
		}
		return authors;
	}

	@Override
	public List<Author> extractDataReadOnly(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author author = new Author();
			author.setAuthorID(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}
	
	@Override
	protected <T> T extractSingleData(ResultSet rs) throws SQLException {
		return null;
	}
	
}
