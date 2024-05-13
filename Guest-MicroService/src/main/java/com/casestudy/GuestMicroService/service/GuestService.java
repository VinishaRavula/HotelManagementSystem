package com.casestudy.GuestMicroService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.casestudy.GuestMicroService.exception.GuestNotFoundException;
import com.casestudy.GuestMicroService.models.Guest;
@Service
public interface GuestService {
	List<Guest> getAllGuests();

	void addGuest(Guest guest);

	void modifyGuest(String guestId, Guest guest) throws GuestNotFoundException;

	void deleteGuest(String guestId) throws GuestNotFoundException;

	Optional<Guest> getGuestById(String guestId) throws GuestNotFoundException;
}
