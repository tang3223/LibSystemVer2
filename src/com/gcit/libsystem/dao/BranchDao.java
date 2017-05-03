package com.gcit.libsystem.dao;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.Book;
import com.gcit.libsystem.entity.Branch;

public class BranchDao extends BaseDao {
	
	public BranchDao(Connection conn) {
		super(conn);
	}
	
	public void addBranch(Branch branch) throws SQLException {
		String  addBranch   = "INSERT INTO tbl_library_branch (branchName, branchAddress) VALUE (?,?)";
		List<?> branchInfo = Arrays.asList(branch.getBranchName(),branch.getBranchAddress());
		save(addBranch, branchInfo);
	}
	
	public void updateBranchAddress(Branch branch) throws SQLException {
		String  updateBranch   = "UPDATE tbl_library_branch SET branchAddress=? WHERE branchId=?";
		List<?> branchInfo = Arrays.asList(branch.getBranchAddress(), branch.getBranchID());
		save(updateBranch, branchInfo);
	}
	
	public void updateBranchName(Branch branch) throws SQLException{
		String  updateBranch = "UPDATE tbl_library_branch SET branchName=? WHERE branchId=?";
		List<?> branchInfo  = Arrays.asList(branch.getBranchName(),branch.getBranchID());
		save(updateBranch, branchInfo);
	}
	
	public void updateNoOfCopies(Integer bookID, Branch branch) throws SQLException{
		String  updateNoOfCopies = "UPDATE tbl_book_copies SET noOfCopies=? WHERE branchId=? AND bookId=?";
		List<?> noOdCopiesInfo = Arrays.asList(branch.getnoOfCopies(bookID), branch.getBranchID(), bookID);
		save(updateNoOfCopies,noOdCopiesInfo);
	}
	
	public void addNoOfCopies(Integer bookID, Branch branch) throws SQLException{
		String  updateNoOfCopies = "INSERT INTO tbl_book_copies VALUE (?,?,?)";
		List<?> noOdCopiesInfo = Arrays.asList(bookID, branch.getBranchID(), branch.getnoOfCopies(bookID));
		save(updateNoOfCopies,noOdCopiesInfo);
	}
	
	public void deleteNoOfCopies(Integer bookID, Branch branch) throws SQLException{
		String  deleteNoOfCopies = "DELETE FROM tbl_book_copies WHERE branchId=? AND bookId=?";
		List<?> noOdCopiesInfo = Arrays.asList(branch.getBranchID(), bookID);
		save(deleteNoOfCopies,noOdCopiesInfo);
	}
	
	public void incNoOfCopies(Branch branch, Integer bookID) throws SQLException{
		Integer copies = branch.getnoOfCopies(bookID);
		branch.setnoOfCopies(bookID, (copies+1));
		updateNoOfCopies(bookID,branch);
	}
	
	public void decNoOfCopies(Branch branch, Integer bookID) throws SQLException{
		Integer copies = branch.getnoOfCopies(bookID);
		branch.setnoOfCopies(bookID, (copies-1));
		updateNoOfCopies(bookID,branch);
	}
	
	public void deleteBranch(Branch branch) throws SQLException{
		String  deleteBranch = "DELETE FROM tbl_library_branch WHERE branchId=?";
		List<?> branchInfo  = Arrays.asList(branch.getBranchID());
		save(deleteBranch, branchInfo);
	}
	
	public List<Branch> readAllBranch() throws SQLException{
		String readBranch = "SELECT * FROM tbl_library_branch";
		return read(readBranch, null);
	}
	
	public Branch readBranch(Integer branchID) throws SQLException{
		String  readBranch   = "SELECT * FROM tbl_library_branch WHERE branchId=?";
		List<?> branchInfo  = Arrays.asList(branchID);
		List<Branch> branchs = read(readBranch, branchInfo);
		if(branchs!=null && !branchs.isEmpty()){
			return branchs.get(0);
		}
		return null;
	}
	
	public List<Branch> readBranch(String branchName) throws SQLException{
		String  readBranch  = "SELECT * FROM tbl_library_branch WHERE branchName LIKE ?";
		List<?> branchInfo = Arrays.asList(branchName);
		return read(readBranch,branchInfo);
	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
		BookDao bookDao = new BookDao(conn);
		BranchDao branchDao = new BranchDao(conn);
		List<Branch> branchs = new ArrayList<>();
		String readBook = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId=?)";
		String readNoOfCopies = "SELECT * FROM tbl_book_copies WHERE bookId=? AND branchId=?";
		while(rs.next()){
			Branch branch = new Branch();		
			branch.setBranchID(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			List<?> branchInfo = Arrays.asList(branch.getBranchID());
			branch.setBooks(bookDao.readOnly(readBook,branchInfo));
			for (Book book : branch.getBooks()){
				List<?> copyInfo = Arrays.asList(book.getBookId(),branch.getBranchID());
				Integer noOfCopies = branchDao.readSingleOnly(readNoOfCopies, copyInfo);
				branch.setnoOfCopies(book.getBookId(), noOfCopies);
			}
			branchs.add(branch);
		}
		return branchs;
	}

	@Override
	public List<Branch> extractDataReadOnly(ResultSet rs) throws SQLException {
		List<Branch> branchs = new ArrayList<>();
		while(rs.next()){
			Branch branch = new Branch();
			branch.setBranchID(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			branchs.add(branch);
		}
		return branchs;
	}

	@Override
	protected Integer extractSingleData(ResultSet rs) throws SQLException {
		while(rs.next()) {
			return rs.getInt("noOfCopies");
		}
		return null;
	}
	
	
	
}
