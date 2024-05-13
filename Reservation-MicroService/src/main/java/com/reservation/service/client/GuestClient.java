package com.reservation.service.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reservation.service.models.Guest;

@FeignClient(name = "Guest-Microservice", url="http://localhost:8099/")
public interface GuestClient {

	@GetMapping("/guest/get/{guestId}")
	public Optional<Guest> getGuestById(@PathVariable String guestId);
	
	@GetMapping("/guest/get")
	public List<Guest> getAllGuests();
}