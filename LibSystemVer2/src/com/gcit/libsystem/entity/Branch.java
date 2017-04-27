package com.gcit.libsystem.entity;

import java.io.Serializable;
import java.util.*;

public class Branch implements Serializable{
	

	private static final long serialVersionUID = 8693080021098507927L;
	private Integer branchID;
	private String branchName;
	private String branchAddress;
	private List<Book> books;
	private HashMap<Book,Integer> noOfCopies = new HashMap<>();
	
	public Integer getBranchID() {
		return branchID;
	}
	public void setBranchID(Integer branchID) {
		this.branchID = branchID;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public Integer getnoOfCopies(Book book) {
		if (noOfCopies.containsKey(book)){
			return noOfCopies.get(book);
		}
		return null;
	}
	public void setnoOfCopies(Book book, Integer copy) {
			noOfCopies.put(book, copy);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchAddress == null) ? 0 : branchAddress.hashCode());
		result = prime * result + ((branchID == null) ? 0 : branchID.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Branch other = (Branch) obj;
		if (branchAddress == null) {
			if (other.branchAddress != null) {
				return false;
			}
		} else if (!branchAddress.equals(other.branchAddress)) {
			return false;
		}
		if (branchID == null) {
			if (other.branchID != null) {
				return false;
			}
		} else if (!branchID.equals(other.branchID)) {
			return false;
		}
		if (branchName == null) {
			if (other.branchName != null) {
				return false;
			}
		} else if (!branchName.equals(other.branchName)) {
			return false;
		}
		return true;
	}
	
	
}
