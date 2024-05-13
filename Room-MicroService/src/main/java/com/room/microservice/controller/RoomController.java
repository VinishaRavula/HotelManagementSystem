package com.room.microservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.casestudy.GuestMicroService.exception.GuestNotFoundException;
//import com.casestudy.GuestMicroService.models.Guest;
import com.room.microservice.exception.RoomNotAvailableException;
import com.room.microservice.exception.RoomNotFoundException;
import com.room.microservice.models.Room;
import com.room.microservice.repository.RoomRepo;
import com.room.microservice.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "*")
public class RoomController {

	@Autowired
	private RoomService roomServiceImpl;
	@Autowired
	private RoomRepo roomRepo;

	@GetMapping("/get")
	public List<Room> getAllRooms() {
		return roomServiceImpl.getAllRooms();
	}

	@PostMapping("/add")
	public String addRoom(@Valid @RequestBody Room room) throws RoomNotFoundException {
		Optional<Room> existingById=roomRepo.findById(room.getRoomId());
        if(existingById.isPresent())
        {
      	  throw new RoomNotFoundException("Room with this Id already exists");
        }
		roomServiceImpl.addRoom(room);
		return "{\"message\": \"Room added successfully\"}";
	}

	@PutMapping("/modify/{roomId}")
	public String modifyRoomById(@RequestBody Room room, @PathVariable String roomId) throws RoomNotFoundException {

		roomServiceImpl.modifyRoomById(roomId, room);
		return "{\"message\": \"Room modified successfully\"}";
		}


	@DeleteMapping("/delete/{roomId}")
	public String deleteRoomById(@PathVariable String roomId) throws RoomNotFoundException {
	
		roomServiceImpl.deleteRoomById(roomId);
		return  "{\"message\": \"Room deleted successfully\"}";
		}


	@GetMapping("/get/{roomId}")
	public Optional<Room> getRoomById(@PathVariable String roomId) throws RoomNotFoundException {
		
		return roomServiceImpl.getRoomById(roomId);
		
		}
	
	@GetMapping("/search/{roomAvail}")
	public ResponseEntity<List<Room>> getAvailRoom(@PathVariable boolean roomAvail) throws RoomNotAvailableException {
	
		List<Room> room = roomServiceImpl.getRoomAvailable(roomAvail);
		return ResponseEntity.ok(room);
	}
	}

