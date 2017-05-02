package com.gcit.libsystem.entity;

import java.io.Serializable;

public class Borrower implements Serializable{
	
	private static final long serialVersionUID = 6142818108544891581L;
	private Integer borrowerID;
	private String  borrowerName;
	private String  borrowerAddress;
	private String  borrowerPhone;
//	private List<BookLoan> loans;

	public Integer getBorrowerID() {
		return borrowerID;
	}
	public void setBorrowerID(Integer borrowerID) {
		this.borrowerID = borrowerID;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getBorrowerAddress() {
		return borrowerAddress;
	}
	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}
	public String getBorrowerPhone() {
		return borrowerPhone;
	}
	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}
//	public List<BookLoan> getLoans() {
//		return loans;
//	}
//	public void setLoans(List<BookLoan> loans) {
//		this.loans = loans;
//	}
//	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((borrowerAddress == null) ? 0 : borrowerAddress.hashCode());
		result = prime * result + ((borrowerID == null) ? 0 : borrowerID.hashCode());
		result = prime * result + ((borrowerName == null) ? 0 : borrowerName.hashCode());
		result = prime * result + ((borrowerPhone == null) ? 0 : borrowerPhone.hashCode());
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
		Borrower other = (Borrower) obj;
		if (borrowerAddress == null) {
			if (other.borrowerAddress != null) {
				return false;
			}
		} else if (!borrowerAddress.equals(other.borrowerAddress)) {
			return false;
		}
		if (borrowerID == null) {
			if (other.borrowerID != null) {
				return false;
			}
		} else if (!borrowerID.equals(other.borrowerID)) {
			return false;
		}
		if (borrowerName == null) {
			if (other.borrowerName != null) {
				return false;
			}
		} else if (!borrowerName.equals(other.borrowerName)) {
			return false;
		}
		if (borrowerPhone == null) {
			if (other.borrowerPhone != null) {
				return false;
			}
		} else if (!borrowerPhone.equals(other.borrowerPhone)) {
			return false;
		}
		return true;
	}

	
}
