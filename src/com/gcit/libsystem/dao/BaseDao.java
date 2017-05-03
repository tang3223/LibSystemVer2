package com.gcit.libsystem.dao;
import java.util.*;
import java.sql.*;

public abstract class BaseDao {
	
	public static Connection conn = null;
	
	protected Integer pageNum, pageSize = 10;
	
	public BaseDao(Connection conn) {
		this.conn = conn;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	protected Integer save(String SQL, List<?> para) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		if (para !=null && !para.isEmpty()) {
			for (int i = 0; i < para.size(); i++){
					pstmt.setObject(i+1, para.get(i));
			}
		}
		return pstmt.executeUpdate();
	}
	
	protected Integer saveWithID(String SQL, List<?> para) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
		if (para !=null && !para.isEmpty()) {
			for (int i = 0; i < para.size(); i++) {
				pstmt.setObject(i+1, para.get(i));
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if(rs.next()){
			return rs.getInt(1);
		}
		return null;
	}
	
	protected abstract <T> List<T> extractData(ResultSet rs) throws SQLException;
	
	protected <T> List<T> read(String SQL, List<?> para) throws SQLException{
		Integer index = 0;
		
		if (getPageNum() != null) {
			index = (getPageNum()-1)*10;
			SQL += " LIMIT " + index + ", " + getPageSize();
		}
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		if (para !=null && !para.isEmpty()){
			for (int i = 0; i < para.size(); i++){
				pstmt.setObject(i+1, para.get(i));
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}
		
	protected abstract <T> List<T> extractDataReadOnly(ResultSet rs) throws SQLException;
	
	protected <T> List<T> readOnly(String SQL, List<?> para) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		if (para !=null && !para.isEmpty()){
			for (int i = 0; i < para.size(); i++){
				pstmt.setObject(i+1, para.get(i));
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractDataReadOnly(rs);
	}
	
	protected abstract <T> T extractSingleData(ResultSet rs) throws SQLException;
	
	protected <T> T readSingleOnly(String SQL, List<?> para) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		if (para !=null && !para.isEmpty()){
			for (int i = 0; i < para.size(); i++){
				pstmt.setObject(i+1, para.get(i));
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractSingleData(rs);
	}
	
	protected boolean check(String SQL, List<?> para) throws SQLException{
		Integer index = 0;
		
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		if (para !=null && !para.isEmpty()){
			for (int i = 0; i < para.size(); i++){
				pstmt.setObject(i+1, para.get(i));
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}
	
	
	
	
	
	
	
}
