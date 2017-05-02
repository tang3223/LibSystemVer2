package com.gcit.libsystem.service;

import java.sql.*;
import java.util.List;

import com.gcit.libsystem.dao.*;
import com.gcit.libsystem.entity.*;

public class LibrarianService {
	
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
	
	public void addNoOfCopies(Integer bookID, Branch branch) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BranchDao bhdao = new BranchDao(conn);
			bhdao.deleteNoOfCopies(bookID, branch);
			bhdao.addNoOfCopies(bookID, branch);
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
	
	public void updateNoOfCopies(Integer bookID, Branch branch) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BranchDao bhdao = new BranchDao(conn);
			bhdao.updateNoOfCopies(bookID, branch);
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
	
	public void deleteNoOfCopies(Integer bookID, Branch branch) throws SQLException{
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			BranchDao bhdao = new BranchDao(conn);
			bhdao.deleteNoOfCopies(bookID, branch);
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









