package com.reservation.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reservation.service.models.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

}