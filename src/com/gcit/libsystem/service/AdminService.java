package com.gcit.libsystem.service;

import java.sql.*;
import java.util.*;
import com.gcit.libsystem.dao.*;
import com.gcit.libsystem.entity.*;



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
			BookDao bdao = new BookDao(conn);
			book.setBookId(bdao.addBookReplyID(book));
			bdao.addBookAuthors(book);
			bdao.addBookGenres(book);
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
	
	public void addGenre(Genre genre) throws SQLException{
		Connection conn = null;	
		try {
			conn = ConnectionUtil.getConnection();
			GenreDao gdao = new GenreDao(conn);
			gdao.addGenre(genre);
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
	
	public void addPublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDao pdao = new PublisherDao(conn);
			pdao.addPublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void addBranch(Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDao bhdao = new BranchDao(conn);
			bhdao.addBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void addBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDao brdao = new BorrowerDao(conn);
			brdao.addBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}

	public void updateAuthor(Author author) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			adao.updateAuthor(author);
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
	
	public void updateBook(Book book) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			bdao.updateBook(book);
			bdao.deleteBookAuthors(book);
			bdao.addBookAuthors(book);
			bdao.deleteBookGenres(book);
			bdao.addBookGenres(book);
			bdao.updateBookPublisher(book);
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
	
	public void updateGenre(Genre genre) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			GenreDao gdao = new GenreDao(conn);
			gdao.updateGenre(genre);
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
	
	public void updatePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDao pdao = new PublisherDao(conn);
			pdao.updatePublisherName(publisher);
			pdao.updatePublisherAddress(publisher);
			pdao.updatePublisherPhone(publisher);
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
	
	public void updateBranch(Branch branch) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BranchDao bhdao = new BranchDao(conn);
			bhdao.updateBranchName(branch);
			bhdao.updateBranchAddress(branch);		
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
 	
	public void updateBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDao brdao = new BorrowerDao(conn);
			brdao.updateBorrowerName(borrower);
			brdao.updateBorrowerAddress(borrower);
			brdao.updateBorrowerPhone(borrower);
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
	
	public void deleteAuthor(Author author) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			adao.deleteAuthor(author);
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
	
	public void deleteBook(Book book) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			bdao.deleteBook(book);
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
	
	public void deleteGenre(Genre genre) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			GenreDao gdao = new GenreDao(conn);
			gdao.deleteGenre(genre);
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
	
	public void deletePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDao pdao = new PublisherDao(conn);
			pdao.deletePublisher(publisher);
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
	
	public void deleteBranch(Branch branch) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BranchDao bhdao = new BranchDao(conn);
			bhdao.deleteBranch(branch);
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

	public void deleteBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDao brdao = new BorrowerDao(conn);
			brdao.deleteBorrower(borrower);
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
	//!!!!!
	public void readBook(Book book) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			bdao.readAllBook();
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
	
	public void updateDueDate(BookLoan bookLoan) throws SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDao brdao = new BookLoanDao(conn);
			brdao.updateBookLoanDate(bookLoan);
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
	
	public List<Author> readAllAuthors(Integer pageNum) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			return adao.readAllAuthors(pageNum);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}

	public Author readAuthor(Integer authorID) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			return adao.readAuthor(authorID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Author> readAuthor(Integer pageNum, String authorName) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			return adao.readAuthor(pageNum, authorName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Integer countAuthors() throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			return adao.countAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
		}

	public List<Book> readAllBook() throws SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			return bdao.readAllBook();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Book readBook(Integer bookID) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			return bdao.readBook(bookID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Genre> readAllGenre() throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			GenreDao gdao = new GenreDao(conn);
			return gdao.readAllGenre();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Publisher> readAllPublisher() throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDao pdao = new PublisherDao(conn);
			return pdao.readAllPublisher();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Publisher readPublisher(Integer publisherID) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDao pdao = new PublisherDao(conn);
			return pdao.readPublisher(publisherID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Genre readGenre(Integer genreID) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			GenreDao gdao = new GenreDao(conn);
			return gdao.readGenre(genreID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Branch> readAllBranchs() throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BranchDao bhdao = new BranchDao(conn);
			return bhdao.readAllBranch();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Branch readBranch(Integer branchID) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BranchDao bhdao = new BranchDao(conn);
			return bhdao.readBranch(branchID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public String parseListItems(List<?> items) {
		if (items == null || items.isEmpty()) {
			return "";
		}
		if (items.get(0).getClass() == Author.class) {
			String authorName = "";
			Author author = null;
			for (int i = 0; i < items.size(); i++){
				author = (Author) items.get(i);
				authorName += author.getAuthorName();
				if (i >= 0 && i < items.size()-1 && items.size()>1){
					authorName += " | ";
				}
			}
			return authorName;
		}
		if (items.get(0).getClass() == Genre.class){
			String genreName = "";
			for (int i = 0; i < items.size(); i++){
				Genre genre = (Genre) items.get(i);
				genreName += genre.getGenreName();
				if (i >= 0 && i < items.size()-1 && items.size()>1){
					genreName += " | ";
				}
			}
			return genreName;
		}
		return null;
	}
	
	public List<Borrower> readAllBorrower() throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDao brdao = new BorrowerDao(conn);
			return brdao.readAllBorrower();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Borrower readBorrower(Integer borrowerID) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDao brdao = new BorrowerDao(conn);
			return brdao.readBorrower(borrowerID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<BookLoan> readAllBookLoans() throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			return bldao.readAllBookLoan();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public BookLoan readBookLoan(Integer borrowerID, Integer branchID, Integer bookID) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			return bldao.readBookLoan(bookID, branchID, borrowerID,0);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
}
























