package com.room.microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.room.microservice.exception.RoomNotAvailableException;
import com.room.microservice.exception.RoomNotFoundException;
import com.room.microservice.models.Room;
import com.room.microservice.repository.RoomRepo;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomRepo roomRepo;

	@Override
	public List<Room> getAllRooms() {
		// TODO Auto-generated method stub
		return roomRepo.findAll();
	}

	@Override
	public void addRoom(Room room) {
		// TODO Auto-generated method stub
		
		roomRepo.save(room);
		
	}

	@Override
	public void modifyRoomById(String roomId, Room room) throws RoomNotFoundException{
	    Optional<Room> existingRoomOptional = roomRepo.findById(roomId);
	    if (existingRoomOptional.isPresent()) {
	        Room existingRoom = existingRoomOptional.get();
	        existingRoom.setRoomPrice(room.getRoomPrice());
	        existingRoom.setRoomType(room.getRoomType());
	        existingRoom.setRoomAvail(room.isRoomAvail());
	        roomRepo.save(existingRoom);
	    }
	    else throw new RoomNotFoundException("Room with this ID is not present");
	}

	@Override
	public void deleteRoomById(String roomId) throws RoomNotFoundException{
		// TODO Auto-generated method stub
		Optional<Room> existingRoomOptional = roomRepo.findById(roomId);
	    if (existingRoomOptional.isPresent()) {
		roomRepo.deleteById(roomId);
	    }
	    else throw new RoomNotFoundException("Room with this ID is not present");
		
	}

	@Override
	public Optional<Room> getRoomById(String roomId) throws RoomNotFoundException{
		// TODO Auto-generated method stub
		Optional<Room> existingRoomOptional = roomRepo.findById(roomId);
	    if (existingRoomOptional.isPresent()) {
		return roomRepo.findById(roomId);
	    }
	    else throw new RoomNotFoundException("Room with this ID is not present");
	}

	@Override
	public List<Room> getRoomAvailable(boolean roomAvail) throws RoomNotAvailableException{
		// TODO Auto-generated method stub
		List<Room> existingRoomOptional = roomRepo.findByRoomAvail(roomAvail);
	    if (!existingRoomOptional.isEmpty()) {
		return roomRepo.findByRoomAvail(roomAvail);
	}
	    else throw new RoomNotAvailableException("Room is not Available");
	
	}

}