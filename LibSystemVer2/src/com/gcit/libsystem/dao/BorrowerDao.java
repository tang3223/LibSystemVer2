package com.gcit.libsystem.dao;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.Borrower;

public class BorrowerDao extends BaseDao {
	
	public BorrowerDao(Connection conn) {
		super(conn);
	}
	
	public void addBorrower(Borrower borrower) throws SQLException {
		String  addBorrower  = "INSERT INTO tbl_borrower (name) VALUE (?)";
		List<?> borrowerInfo = Arrays.asList(borrower.getBorrowerName());
		save(addBorrower, borrowerInfo);
	}
	
	public void updateBorrowerAddress(Borrower borrower) throws SQLException {
		String  updateBorrower  = "UPDATE tbl_borrower SET address=? WHERE cardNo=?";
		List<?> borrowerInfo = Arrays.asList(borrower.getBorrowerAddress(),borrower.getBorrowerID());
		save(updateBorrower, borrowerInfo);
	}
	
	public void updateBorrowerName(Borrower borrower) throws SQLException{
		String  updateBorrower = "UPDATE tbl_borrower SET name=? WHERE cardNo=?";
		List<?> borrowerInfo   = Arrays.asList(borrower.getBorrowerName(),borrower.getBorrowerID());
		save(updateBorrower, borrowerInfo);
	}
	
	public void updateBorrowerPhone(Borrower borrower) throws SQLException{
		String  updateBorrower = "UPDATE tbl_borrower SET phone=? WHERE cardNo=?";
		List<?> borrowerInfo   = Arrays.asList(borrower.getBorrowerPhone(),borrower.getBorrowerID());
		save(updateBorrower, borrowerInfo);
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException{
		String  deleteBorrower = "DELETE * FROM tbl_borrower WHERE cardNo=?";
		List<?> borrowerInfo   = Arrays.asList(borrower.getBorrowerID());
		save(deleteBorrower, borrowerInfo);
	}
	
	public List<Borrower> readAllBorrower() throws SQLException{
		String readBorrower = "SELECT * FROM tbl_borrower";
		return read(readBorrower, null);
	}
	
	public Borrower readBorrower(Integer borrowerID) throws SQLException{
		String  readBorrower  = "SELECT * FROM tbl_borrower WHERE cardNo=?";
		List<?> borrowerInfo  = Arrays.asList(borrowerID);
		List<Borrower> borrower = read(readBorrower, borrowerInfo);
		if(borrower!=null && !borrower.isEmpty()){
			return borrower.get(0);
		}
		return null;
	}
	
	public List<Borrower> readBorrower(String borrowerName) throws SQLException{
		String  readBorrower = "SELECT * FROM tbl_publisher WHERE publisherName LIKE ?";
		List<?> borrowerInfo = Arrays.asList(borrowerName);
		return read(readBorrower,borrowerInfo);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		BookDao bookDao = new BookDao(conn);
		List<Publisher> publishers = new ArrayList<>();
		String readBook = "SELECT * FROM tbl_book JOIN tbl_publisher ON pubId=publisherId WHERE publisherId=?";
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
