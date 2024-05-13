package com.room.microservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.room.microservice.models.Room;

@Repository
public interface RoomRepo extends MongoRepository<Room, String>{

	List<Room> findByRoomAvail(boolean roomAvail);

}