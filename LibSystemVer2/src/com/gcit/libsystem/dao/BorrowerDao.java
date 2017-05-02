package com.gcit.libsystem.dao;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.Borrower;

public class BorrowerDao extends BaseDao {
	
	public BorrowerDao(Connection conn) {
		super(conn);
	}
	
	public void addBorrower(Borrower borrower) throws SQLException {
		String  addBorrower  = "INSERT INTO tbl_borrower (name, address, phone) VALUE (?,?,?)";
		List<?> borrowerInfo = Arrays.asList(borrower.getBorrowerName(), 
				borrower.getBorrowerAddress(), borrower.getBorrowerPhone());
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
		String  deleteBorrower = "DELETE FROM tbl_borrower WHERE cardNo=?";
		List<?> borrowerInfo   = Arrays.asList(borrower.getBorrowerID());
		save(deleteBorrower, borrowerInfo);
	}
	
	public boolean checkBorrower(Integer borrowerID) throws SQLException{
		String  readBorrower  = "SELECT * FROM tbl_borrower WHERE cardNo=?";
		List<?> borrowerInfo  = Arrays.asList(borrowerID);
		return check(readBorrower,borrowerInfo);
	}
	
	public List<Borrower> readAllBorrower() throws SQLException{
		String readBorrower = "SELECT * FROM tbl_borrower";
		return readOnly(readBorrower, null);
	}
	
	public Borrower readBorrower(Integer borrowerID) throws SQLException{
		String  readBorrower  = "SELECT * FROM tbl_borrower WHERE cardNo=?";
		List<?> borrowerInfo  = Arrays.asList(borrowerID);
		List<Borrower> borrower = readOnly(readBorrower, borrowerInfo);
		if(borrower!=null && !borrower.isEmpty()){
			return borrower.get(0);
		}
		return null;
	}
	
	public List<Borrower> readBorrower(String borrowerName) throws SQLException{
		String  readBorrower = "SELECT * FROM tbl_publisher WHERE publisherName LIKE ?";
		List<?> borrowerInfo = Arrays.asList(borrowerName);
		return readOnly(readBorrower,borrowerInfo);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		return null;
	}

	@Override
	public List<Borrower> extractDataReadOnly(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower borrower = new Borrower();
			borrower.setBorrowerID(rs.getInt("cardNo"));
			borrower.setBorrowerName(rs.getString("name"));
			borrower.setBorrowerAddress(rs.getString("address"));
			borrower.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}
		return borrowers;
	}
	
	@Override
	protected <T> T extractSingleData(ResultSet rs) throws SQLException {
		return null;
	}
	
}
