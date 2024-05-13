package com.casestudy.GuestMicroService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.GuestMicroService.exception.GuestNotFoundException;
import com.casestudy.GuestMicroService.models.Guest;
import com.casestudy.GuestMicroService.repository.GuestRepo;



@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepo guestRepo;
	@Autowired
 //private EmailServiceImpl emailServiceImpl;
	@Override
	public List<Guest> getAllGuests() {
		return guestRepo.findAll();
	}

	@Override
	public void addGuest(Guest guest) {
	
		guestRepo.save(guest);

	}

	@Override
	public void modifyGuest(String guestId, Guest guest) throws GuestNotFoundException {
		Optional<Guest> existingGuestOptional = guestRepo.findById(guestId);
		if (existingGuestOptional.isPresent()) {
			Guest existingGuest = existingGuestOptional.get();
			existingGuest.setGuestName(guest.getGuestName());
			existingGuest.setGuestAddress(guest.getGuestAddress());
			existingGuest.setGuestContact(guest.getGuestContact());
			existingGuest.setGuestEmail(guest.getGuestEmail());
			existingGuest.setGuestGender(guest.getGuestGender());
			guestRepo.save(existingGuest);	
		}
		else throw new GuestNotFoundException("No guest with that ID");
	}

	@Override
	public void deleteGuest(String guestId) throws GuestNotFoundException {
		Optional<Guest> existingGuestOptional = guestRepo.findById(guestId);
		if (existingGuestOptional.isPresent()) {
		guestRepo.deleteById(guestId);
		}
		
		else throw new GuestNotFoundException("No guest with that ID");

	}

	@Override
	public Optional<Guest> getGuestById(String guestId) throws GuestNotFoundException {
		Optional<Guest> existingGuestOptional = guestRepo.findById(guestId);
		if (existingGuestOptional.isPresent()) {
		return guestRepo.findById(guestId);
	}
		else throw new GuestNotFoundException("No guest with that ID");

}
}