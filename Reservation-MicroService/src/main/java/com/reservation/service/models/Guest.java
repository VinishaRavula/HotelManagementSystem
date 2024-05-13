package com.reservation.service.models;

public class Guest {

	private String guestId;
	private long guestContact;
	private String guestName;
	private String guestEmail;
	private String guestGender;
	private String guestAddress;
	
	public Guest() {
		super();
	}
	
	@Override
	public String toString() {
		return "Guest [guestId=" + guestId + ", guestContact=" + guestContact + ", guestName=" + guestName
				+ ", guestEmail=" + guestEmail + ", guestGender=" + guestGender + ", guestAddress=" + guestAddress
				+ "]";
	}
	
	public Guest(String guestId, long guestContact, String guestName, String guestEmail, String guestGender,
			String guestAddress) {
		super();
		this.guestId = guestId;
		this.guestContact = guestContact;
		this.guestName = guestName;
		this.guestEmail = guestEmail;
		this.guestGender = guestGender;
		this.guestAddress = guestAddress;
	}

	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public long getGuestContact() {
		return guestContact;
	}
	public void setGuestContact(long guestContact) {
		this.guestContact = guestContact;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	public String getGuestGender() {
		return guestGender;
	}
	public void setGuestGender(String guestGender) {
		this.guestGender = guestGender;
	}
	public String getGuestAddress() {
		return guestAddress;
	}
	public void setGuestAddress(String guestAddress) {
		this.guestAddress = guestAddress;
	}
	
}