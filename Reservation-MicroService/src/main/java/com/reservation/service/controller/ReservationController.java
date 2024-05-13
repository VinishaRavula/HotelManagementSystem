package com.reservation.service.controller;

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

import com.reservation.service.exception.InvalidReservationIdException;
import com.reservation.service.models.Reservation;
import com.reservation.service.models.TransactionDetails;
import com.reservation.service.services.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping("/add")
	public String addReservation(@Valid @RequestBody Reservation reservation) {
		return reservationService.addReservation(reservation);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Reservation>> getAllReservations() {
		return ResponseEntity.ok(reservationService.gettAllReservations());

	}
	@GetMapping("/get/{resId}")
	public ResponseEntity<Optional<Reservation>> getReservationById(@PathVariable String resId) throws InvalidReservationIdException {


			Optional<Reservation> reservation = reservationService.getReservationById(resId);
			if (reservation.isPresent()) {
				return ResponseEntity.ok(reservation);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

	
	@PutMapping("/modify/{resId}")
	public String modifyReservationById(@PathVariable String resId, @RequestBody Reservation reservation) throws InvalidReservationIdException {
		
			reservationService.modifyReservationById(reservation, resId);
			return "{\"message\": \"reservation details updated successfully\"}";
		
		}
	

	@DeleteMapping("/delete/{resId}") 
	public ResponseEntity<String> deleteReservationById(@PathVariable String resId) throws InvalidReservationIdException {
		
			return ResponseEntity.ok(reservationService.deleteReservationById(resId));
	
		}
	
	@GetMapping("/createTransaction/{amount}")
	public TransactionDetails createTransaction(@PathVariable Double amount) {
		 return reservationService.createTransaction(amount);
	}

}