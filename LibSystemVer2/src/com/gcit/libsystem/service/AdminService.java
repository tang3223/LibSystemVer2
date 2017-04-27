package com.gcit.libsystem.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.gcit.libsystem.dao.AuthorDao;
import com.gcit.libsystem.dao.BookDao;
import com.gcit.libsystem.entity.Author;
import com.gcit.libsystem.entity.Book;

public class AdminService {
	
	public void addAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			adao.addAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void addBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDao bdao   = new BookDao(conn);
			Integer bookID = bdao.addBookWithID(book);
			if(book.getAuthors()!=null && !book.getAuthors().isEmpty()){
				for(Author a: book.getAuthors()){
					bdao.addBookAuthors(book.getBookID(), a.getAuthorID());
				}
			}
			//repeat for Genres
			//repeat for Publisher
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	
	

}
