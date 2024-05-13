package com.room.microservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Document(collection="Room-Service")
public class Room {
	
	@Id
	private String roomId;
	@NotNull(message = "Room Type can not be Null")
	private String roomType;
	//@NotNull(message = "Room Available can not be Null")
	private boolean roomAvail;
	@NotNull(message = "Room Price can not be Null")
	@Positive 
	private double roomPrice;
	


	public Room() {
		super();
	}
	
	public Room(String roomId, @NotNull String roomType, @NotNull boolean roomAvail, @NotNull double roomPrice) {
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
