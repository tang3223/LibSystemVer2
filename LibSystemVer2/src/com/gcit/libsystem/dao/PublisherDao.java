package com.gcit.libsystem.dao;

import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.Publisher;

public class PublisherDao extends BaseDao {
	
	public PublisherDao(Connection conn) {
		super(conn);
	}
	
	public void addPublisher(Publisher publisher) throws SQLException {
		String  addPublisher  = "INSERT INTO tbl_publisher (publisherName) VALUE (?)";
		List<?> publisherInfo = Arrays.asList(publisher.getPublisherName());
		save(addPublisher, publisherInfo);
	}
	
	public void updatePublisherAddress(Publisher publisher) throws SQLException {
		String  updatePublisher  = "UPDATE tbl_publisher SET publisherAddress=? WHERE publisherId=?";
		List<?> publisherInfo = Arrays.asList(publisher.getPublisherAddress(),publisher.getPublisherId());
		save(updatePublisher, publisherInfo);
	}
	
	public void updatePublisherName(Publisher publisher) throws SQLException{
		String  updatePublisher = "UPDATE tbl_publisher SET publisherName=? WHERE publisherId=?";
		List<?> publisherInfo   = Arrays.asList(publisher.getPublisherName(),publisher.getPublisherId());
		save(updatePublisher, publisherInfo);
	}
	
	public void updatePublisherPhone(Publisher publisher) throws SQLException{
		String  updatePublisher = "UPDATE tbl_publisher SET publisherPhone=? WHERE publisherId=?";
		List<?> publisherInfo   = Arrays.asList(publisher.getPublisherPhone(),publisher.getPublisherId());
		save(updatePublisher, publisherInfo);
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException{
		String  deletePublisher = "DELETE * FROM tbl_publisher WHERE publisherId=?";
		List<?> publisherInfo   = Arrays.asList(publisher.getPublisherId());
		save(deletePublisher, publisherInfo);
	}
	
	public List<Publisher> readAllPublisher() throws SQLException{
		String readPublisher = "SELECT * FROM tbl_publisher";
		return read(readPublisher, null);
	}
	
	public Publisher readPublisher(Integer publisherID) throws SQLException{
		String  readPublisher  = "SELECT * FROM tbl_publisher WHERE publisherId=?";
		List<?> publisherInfo  = Arrays.asList(publisherID);
		List<Publisher> publisher = read(readPublisher, publisherInfo);
		if(publisher!=null && !publisher.isEmpty()){
			return publisher.get(0);
		}
		return null;
	}
	
	public List<Publisher> readPublisher(String publisherName) throws SQLException{
		String  readPublisher = "SELECT * FROM tbl_publisher WHERE publisherName LIKE ?";
		List<?> publisherInfo = Arrays.asList(publisherName);
		return read(readPublisher,publisherInfo);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
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
