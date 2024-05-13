package com.casestudy.GuestMicroService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.GuestMicroService.models.Guest;



@Repository
public interface GuestRepo extends MongoRepository<Guest, String> {

}