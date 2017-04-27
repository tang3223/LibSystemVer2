package com.gcit.libsystem.entity;
import java.io.*;
import java.util.*;

public class Author implements Serializable {

	private static final long serialVersionUID = -7620067259207103271L;
	
	private Integer authorID;
	private String  authorName;
	private List<Book> books;
	/**
	 * @return the authorID
	 */
	public Integer getAuthorID() {
		return authorID;
	}
	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorID(Integer authorID) {
		this.authorID = authorID;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorID == null) ? 0 : authorID.hashCode());
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		Author other = (Author) obj;
		if (authorID == null) {
			if (other.authorID != null) {
				return false;
			}
		} else if (!authorID.equals(other.authorID)) {
			return false;
		}
		if (authorName == null) {
			if (other.authorName != null) {
				return false;
			}
		} else if (!authorName.equals(other.authorName)) {
			return false;
		}
		return true;
	}

	
}
