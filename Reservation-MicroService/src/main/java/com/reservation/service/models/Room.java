package com.reservation.service.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Room-Service")
public class Room {

	private String roomId;
	private String roomType;
	private boolean roomAvail;
	private double roomPrice;
	
	public Room() {}
	
	public Room(String roomId, String roomType, boolean roomAvail, double roomPrice) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomAvail = roomAvail;
		this.roomPrice = roomPrice;
	}
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomType=" + roomType + ", roomAvail=" + roomAvail + ", roomPrice="
				+ roomPrice + "]";
	}

	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public boolean isRoomAvail() {
		return roomAvail;
	}
	public void setRoomAvail(boolean roomAvail) {
		this.roomAvail = roomAvail;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	
}