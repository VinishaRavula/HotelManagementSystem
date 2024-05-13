package com.room.microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.room.microservice.exception.RoomNotAvailableException;
import com.room.microservice.exception.RoomNotFoundException;
import com.room.microservice.models.Room;

@Service
public interface RoomService {
	
	List<Room> getAllRooms();
	
	void addRoom(Room room);
	
	void modifyRoomById(String roomId, Room room) throws RoomNotFoundException;
	
	void deleteRoomById(String roomId) throws RoomNotFoundException;
	
	Optional<Room> getRoomById(String roomId) throws RoomNotFoundException;
	
	List<Room> getRoomAvailable(boolean roomAvail) throws RoomNotAvailableException;

}