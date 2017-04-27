package com.gcit.libsystem.dao;
import java.util.*;
import java.sql.*;

public abstract class BaseDao {
	
	public static Connection conn = null;
	
	public BaseDao(Connection conn) {
		this.conn = conn;
	}
	
	protected Integer save(String SQL, List<?> para) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		if (!para.isEmpty()) {
			for (int i = 0; i < para.size(); i++){
					pstmt.setObject(i, para.get(i));
			}
		}
		return pstmt.executeUpdate();
	}
	
	protected Integer saveWithID(String SQL, List<?> para) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
		if (!para.isEmpty()) {
			for (int i = 0; i < para.size(); i++) {
				pstmt.setObject(i, para.get(i));
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if(rs.next()){
			return rs.getInt(1);
		}
		return null;
	}
	
	//List<?>
	protected abstract <T> List<T> extractData(ResultSet rs) throws SQLException;
	
	//List<?>
	protected <T> List<T> read(String SQL, List<?> para) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		if (!para.isEmpty()){
			for (int i = 0; i < para.size(); i++){
				pstmt.setObject(i, para.get(i));
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}
	
	protected abstract <T> List<T> extractDataReadOnly(ResultSet rs) throws SQLException;
	
	protected <T> List<T> readOnly(String SQL, List<?> para) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		if (!para.isEmpty()){
			for (int i = 0; i < para.size(); i++){
				pstmt.setObject(i, para.get(i));
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractDataReadOnly(rs);
	}
	
	
	
	
	
	
	
	
	
}
