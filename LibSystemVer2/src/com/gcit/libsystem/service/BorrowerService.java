package com.gcit.libsystem.service;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.dao.*;
import com.gcit.libsystem.entity.*;

public class BorrowerService {
	
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
	
	public Branch readBranchWithLimit(Integer branchID) throws SQLException{
		Connection conn = null;
		Branch branch = readBranch(branchID);
		
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		BookDao bdao = new BookDao(conn);
		List<Book> unprocessed = branch.getBooks();
		List<Book> processed = new ArrayList<>();
		for (Book book : unprocessed){
			if (branch.getnoOfCopies(book.getBookId()) > 0){
				Book fullBook = bdao.readBook(book.getBookId());
				processed.add(fullBook);
			}
		}
		branch.setBooks(processed);
		return branch;
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

	public boolean checkCardID(Integer cardID) throws SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDao brdao = new BorrowerDao(conn);
			return brdao.checkBorrower(cardID);
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
			return false;
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}

	public void checkOutBook(Integer bookID, Integer branchID, Integer borrowerID) throws SQLException{
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			BranchDao brdao = new BranchDao(conn);		
			bldao.addBookLoan(bookID, branchID, borrowerID);
			brdao.decNoOfCopies(brdao.readBranch(branchID),bookID);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public boolean checkDupBook(Integer bookID, Integer branchID, Integer borrowerID) throws SQLException{
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			BranchDao brdao = new BranchDao(conn);		
			return bldao.checkBookLoan(bookID, branchID, borrowerID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void returnBook(Integer bookID, Integer branchID, Integer borrowerID, String dayOut) throws SQLException{
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			BranchDao brdao = new BranchDao(conn);	
			bldao.returnBookLoanDate(bookID, branchID, borrowerID, dayOut);
			brdao.incNoOfCopies(brdao.readBranch(branchID),bookID);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
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
	
	public List<BookLoan> readBookLoan(Integer borrowerID) throws SQLException{
		Connection conn = null;
		List<BookLoan> processed = new ArrayList<>();
		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			BranchDao bhdao = new BranchDao(conn);
			List<BookLoan> unprocessed = bldao.readBookLoan(borrowerID);
			for (BookLoan bookLoan : unprocessed){
				Integer branchID = bookLoan.getBranch().getBranchID();
				Branch branch = bhdao.readBranch(branchID);
				bookLoan.setBranch(branch);
				if (bookLoan.getDateIn() == null || bookLoan.getDateIn().toString().equals("")){
					processed.add(bookLoan);
				}		
			}
			return processed;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public BookLoan readBookLoan(Integer borrowerID, Integer bookID, Integer branchID, Integer num) throws SQLException{
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			BookLoan bookLoan = bldao.readBookLoan(bookID, branchID, borrowerID, num);
			return bookLoan;
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










