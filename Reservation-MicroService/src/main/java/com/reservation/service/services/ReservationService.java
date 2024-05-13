package com.reservation.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.reservation.service.exception.InvalidReservationIdException;
import com.reservation.service.models.Reservation;
import com.reservation.service.models.TransactionDetails;

@Service
public interface ReservationService {
	
	String addReservation(Reservation reservation);

	List<Reservation> gettAllReservations();
	
	public void modifyReservationById(Reservation reservation, String resId) throws InvalidReservationIdException;
	
	String deleteReservationById(String resId) throws InvalidReservationIdException;

	Optional<Reservation> getReservationById(String resId) throws InvalidReservationIdException;

	TransactionDetails createTransaction(Double amount);
	

}
