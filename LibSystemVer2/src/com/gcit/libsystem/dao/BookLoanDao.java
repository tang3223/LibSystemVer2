package com.gcit.libsystem.dao;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.Book;
import com.gcit.libsystem.entity.BookLoan;
import com.gcit.libsystem.entity.Borrower;
import com.gcit.libsystem.entity.Branch;

public class BookLoanDao extends BaseDao {
	
	public BookLoanDao(Connection conn) {
		super(conn);
	}
	
	public void addBookLoan(Integer bookID, Integer branchID, Integer borrowerID) throws SQLException {
		String  addBookLoan  = "INSERT INTO tbl_book_loans "
				+ "(bookId,branchId,cardNo,dateOut,dueDate) VALUE (?,?,?,NOW(),DATE_ADD(NOW(),INTERVAL 7 DAY))";
		List<?> bookLoanInfo = Arrays.asList(bookID,branchID,borrowerID);
		save(addBookLoan, bookLoanInfo);
	}
	
	public void updateBookLoanDate(BookLoan bookLoan) throws SQLException {
		String  updateBookLoanDate = "UPDATE tbl_book_loans SET dueDate=? WHERE bookId=? AND branchId=? AND cardNo=?";
		List<?> bookLoanInfo = Arrays.asList(bookLoan.getDueDate(), bookLoan.getBook().getBookId(), 
				bookLoan.getBranch().getBranchID(), bookLoan.getBorrower().getBorrowerID());
		save(updateBookLoanDate, bookLoanInfo);
	}
	
	public boolean checkBookLoan(Integer bookID, Integer branchID, Integer borrowerID) throws SQLException{
		String  readBookLoan  = "SELECT * FROM tbl_book_loans WHERE bookId=? AND branchId=? AND cardNo=?";
		List<?> bookLoanInfo  = Arrays.asList(bookID, branchID, borrowerID);
		List<BookLoan> bookLoan = read(readBookLoan, bookLoanInfo);
		if(bookLoan == null || bookLoan.isEmpty()){
			return true;
		}
		return false;
	}
	
	public void returnBookLoanDate(Integer bookID, Integer branchID, Integer borrowerID, String dayOut) throws SQLException {
		String  returnBookLoanDate = "UPDATE tbl_book_loans SET dateIn=NOW() WHERE bookId=? AND branchId=? AND cardNo=? AND dateOut=?";
		List<?> bookLoanInfo = Arrays.asList(bookID, branchID, borrowerID, dayOut);
		save(returnBookLoanDate, bookLoanInfo);
	}
	
	public void deleteBookLoan(Integer bookID, Integer branchID, Integer borrowerID) throws SQLException{
		String  deleteBookLoan = "DELETE FROM tbl_book_loans WHERE bookId=? AND branchId=? AND cardNo=?";
		List<?> bookLoanInfo   = Arrays.asList(bookID,branchID,borrowerID);
		save(deleteBookLoan, bookLoanInfo);
	}
	
	public List<BookLoan> readAllBookLoan() throws SQLException{
		String readBookLoan = "SELECT * FROM tbl_book_loans";
		return read(readBookLoan, null);
	}
	
	public BookLoan readBookLoan(Integer bookID, Integer branchID, Integer borrowerID, Integer num) throws SQLException{
		String  readBookLoan  = "SELECT * FROM tbl_book_loans WHERE bookId=? AND branchId=? AND cardNo=?";
		List<?> bookLoanInfo  = Arrays.asList(bookID, branchID, borrowerID);
		List<BookLoan> bookLoan = read(readBookLoan, bookLoanInfo);
		if(bookLoan!=null && !bookLoan.isEmpty()){
			return bookLoan.get(num);
		}
		return null;
	}
	
	public List<BookLoan> readBookLoan(Integer borrowerID) throws SQLException{
		String  readBookLoan  = "SELECT * FROM tbl_book_loans WHERE cardNo=?";
		List<?> bookLoanInfo  = Arrays.asList(borrowerID);
		List<BookLoan> bookLoan = read(readBookLoan, bookLoanInfo);
		if(bookLoan!=null && !bookLoan.isEmpty()){
			return bookLoan;
		}
		return null;
	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		BookDao bookDao = new BookDao(conn);
		BorrowerDao borrowerDao = new BorrowerDao(conn);
		BranchDao branchDao = new BranchDao(conn);
		List<BookLoan> bookLoans = new ArrayList<>();
		String readBook = "SELECT * FROM tbl_book WHERE bookId=?";
		String readBranch = "SELECT * FROM tbl_library_branch WHERE branchId=?";
		String readBorrower = "SELECT * FROM tbl_borrower WHERE cardNo=?";
		while(rs.next()){
			BookLoan bookLoan = new BookLoan();
			List<?> bookInfo = Arrays.asList(rs.getInt("bookId")); 
			List<?>	borrowerInfo = Arrays.asList(rs.getInt("cardNo"));	
			List<?> branchInfo = Arrays.asList(rs.getInt("branchId"));
			bookLoan.setBook((Book)bookDao.readOnly(readBook, bookInfo).get(0));
			bookLoan.setBranch((Branch)branchDao.readOnly(readBranch, branchInfo).get(0));
			bookLoan.setBorrower((Borrower)borrowerDao.readOnly(readBorrower, borrowerInfo).get(0));
			bookLoan.setDateOut(rs.getTimestamp("dateOut"));
			bookLoan.setDueDate(rs.getTimestamp("dueDate"));
			bookLoan.setDateIn(rs.getTimestamp("dateIn"));;
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

	@Override
	public List<BookLoan> extractDataReadOnly(ResultSet rs) throws SQLException {
		return null;
	}
	
	@Override
	protected <T> T extractSingleData(ResultSet rs) throws SQLException {
		return null;
	}
	
}
