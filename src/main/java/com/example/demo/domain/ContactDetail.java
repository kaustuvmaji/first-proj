package com.example.demo.domain;

import java.io.Serializable;
import java.util.List;

public class ContactDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 799818822284620731L;

	private List<Address> address;
	private String emailId;
	private Long phoneNumber;
	private Long mobileNumber;

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public ContactDetail() {
	}

	public ContactDetail(List<Address> address, String emailId, Long phoneNumber, Long mobileNumber) {
		super();
		this.address = address;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "ContactDetail [" + (address != null ? "address=" + address + ", " : "")
				+ (emailId != null ? "emailId=" + emailId + ", " : "")
				+ (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
				+ (mobileNumber != null ? "mobileNumber=" + mobileNumber : "") + "]";
	}

}
