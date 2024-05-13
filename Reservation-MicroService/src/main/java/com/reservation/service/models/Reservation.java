package com.reservation.service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

//import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Document("reservationmicroservice")
public class Reservation {

	@Id
 	private String reservationId;


	private String roomId;

	
	private String guestId;

	
	@NotNull(message = "Date cannot be null")
	@Size(min = 2, max = 10, message = "Date should be in format yyyy-mm-dd")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date should be in format yyyy-mm-dd")
	private String checkInDate;

	
	@NotNull(message = "Date cannot be null")
	@Size(min = 2, max = 10, message = "Date should be in format yyyy-mm-dd")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date should be in format yyyy-mm-dd")
	private String checkOutDate;

	
	private int numOfGuest;

	//@NotNull(message = "It should not be Null")
	@PositiveOrZero
	private double totalPrice;
	
	//@Transient
	private Guest guest;

	public String getReservationId() {
		return reservationId;
	}

	public Reservation(String reservationId, String roomId, String guestId,
			@NotNull(message = "Date cannot be null") @Size(min = 10, max = 10, message = "Date should be in format yyyy-mm-dd") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date should be in format yyyy-mm-dd") String checkInDate,
			@NotNull(message = "Date cannot be null") @Size(min = 10, max = 10, message = "Date should be in format yyyy-mm-dd") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date should be in format yyyy-mm-dd") String checkOutDate,
			int numOfGuest, @PositiveOrZero double totalPrice, Guest guest) {
		super();
		this.reservationId = reservationId;
		this.roomId = roomId;
		this.guestId = guestId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numOfGuest = numOfGuest;
		this.totalPrice = totalPrice;
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", roomId=" + roomId + ", guestId=" + guestId
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", numOfGuest=" + numOfGuest
				+ ", totalPrice=" + totalPrice + ", guest=" + guest + "]";
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	

	public int getNumOfGuest() {
		return numOfGuest;
	}

	public void setNumOfGuest(int numOfGuest) {
		this.numOfGuest = numOfGuest;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	

	public Reservation() {

	}
	
	
}