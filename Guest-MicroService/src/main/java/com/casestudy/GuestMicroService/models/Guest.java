package com.casestudy.GuestMicroService.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Document(collection = "Guest-Service")
public class Guest {

	@Id
	@NotNull(message = "Guest ID cannot be null")
	private String guestId;
	
	
	@NotNull(message = "Guest Contact cannot be null")
	@Positive
	private long guestContact;
	
	@NotNull(message = "Guest Name cannot be null")
	@Size(min = 3, message = "Guest Name should be a minimum of 3 characters")
	private String guestName;

	@NotEmpty(message = "Guest Email cannot be null")
	private String guestEmail;

	@NotNull(message = "Guest Gender cannot be null")
	@Size(min = 3, message = "Guest Gender should be a maximum of 5 characters")
	private String guestGender;

	@NotNull(message = "Guest Address cannot be null")
	@Size(min = 3, message = "Guest Address should be a minimum of 5 characters")
	private String guestAddress;

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

	@Override
	public String toString() {
		return "Guest [guestId=" + guestId + ", guestContact=" + guestContact + ", guestName=" + guestName
				+ ", guestEmail=" + guestEmail + ", guestGender=" + guestGender + ", guestAddress=" + guestAddress
				+ "]";
	}

	public Guest(@NotNull(message = "Guest ID cannot be null") String guestId,
			@NotNull(message = "Guest Contact cannot be null") @Positive long guestContact,
			@NotNull(message = "Guest Name cannot be null") @Size(min = 3, message = "Guest Name should be a minimum of 3 characters") String guestName,
			@NotNull(message = "Guest Email cannot be null") @Size(min = 12, message = "Guest Email should be a minimum of 12 characters") String guestEmail,
			@NotNull(message = "Guest Gender cannot be null") @Size(max = 5, message = "Guest Gender should be a maximum of 5 characters") String guestGender,
			@NotNull(message = "Guest Address cannot be null") @Size(min = 5, message = "Guest Address should be a minimum of 5 characters") String guestAddress) {
		super();
		this.guestId = guestId;
		this.guestContact = guestContact;
		this.guestName = guestName;
		this.guestEmail = guestEmail;
		this.guestGender = guestGender;
		this.guestAddress = guestAddress;
	}

	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

	

}