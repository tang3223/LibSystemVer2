package com.gcit.libsystem.dao;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.BookLoan;

public class BookLoanDao extends BaseDao {
	
	public BookLoanDao(Connection conn) {
		super(conn);
	}
	
	public void addBookLoan(BookLoan bookLoan) throws SQLException {
		String  addBookLoan  = "INSERT INTO tbl_book_loans "
				+ "(bookId,branchId,cardNo,dateOut,dueDate) VALUE (?,?,?,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 7 DAY))";
		List<?> bookLoanInfo = Arrays.asList(bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchID(),
				bookLoan.getBorrower().getBorrowerID());
		save(addBookLoan, bookLoanInfo);
	}
	
	public void updateBookLoanDate(BookLoan bookLoan) throws SQLException {
		String  updateBookLoanDate = "UPDATE tbl_book_loans SET dueDate=? WHERE bookId=? AND branchId=? AND cardNo=?";
		List<?> bookLoanInfo = Arrays.asList(bookLoan.getDueDate(), bookLoan.getBook().getBookId(), 
				bookLoan.getBranch().getBranchID(), bookLoan.getBorrower().getBorrowerID());
		save(updateBookLoanDate, bookLoanInfo);
	}
	
	public void deleteBookLoan(BookLoan bookLoan) throws SQLException{
		String  deleteBookLoan = "DELETE * FROM tbl_book_loans WHERE bookId=? AND branchId=? AND cardNo=?";
		List<?> bookLoanInfo   = Arrays.asList(bookLoan.getBook().getBookId(), 
				bookLoan.getBranch().getBranchID(), bookLoan.getBorrower().getBorrowerID());
		save(deleteBookLoan, bookLoanInfo);
	}
	
	public List<BookLoan> readAllBookLoan() throws SQLException{
		String readBookLoan = "SELECT * FROM tbl_book_loans";
		return read(readBookLoan, null);
	}
	
	public BookLoan readBookLoan(Integer bookLoanID, Integer branchID, Integer borrowerID) throws SQLException{
		String  readBookLoan  = "SELECT * FROM tbl_book_loans WHERE bookId=? AND branchId=? AND cardNo=?";
		List<?> bookLoanInfo  = Arrays.asList(bookLoanID, branchID, borrowerID);
		List<BookLoan> bookLoan = read(readBookLoan, bookLoanInfo);
		if(bookLoan!=null && !bookLoan.isEmpty()){
			return bookLoan.get(0);
		}
		return null;
	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		BookDao bookDao = new BookDao(conn);
		BorrowerDao borrowerDao = new BorrowerDao(conn);
		BranchDao branchDao = new BranchDao(conn);
		List<BookLoan> bookLoans = new ArrayList<>();
		String readBook = "SELECT * FROM tbl_book JOIN tbl_publisher ON pubId=publisherId WHERE publisherId=?";
		String readBranch = "";
		String readBorrower = "";
		while(rs.next()){
			Publisher publisher = new Publisher();
			List<?> publisherInfo = Arrays.asList(publisher.getPublisherId());
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherAddress"));
			publisher.setBooks(bookDao.readOnly(readBook,publisherInfo));
			publishers.add(publisher);
		}
		return publishers;
	}

	@Override
	public List<Publisher> extractDataReadOnly(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherAddress"));
			publishers.add(publisher);
		}
		return publishers;
	}
	
	@Override
	protected <T> T extractSingleData(ResultSet rs) throws SQLException {
		return null;
	}
	
}
