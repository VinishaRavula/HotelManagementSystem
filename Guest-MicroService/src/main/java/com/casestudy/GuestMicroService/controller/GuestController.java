package com.casestudy.GuestMicroService.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.GuestMicroService.exception.GuestNotFoundException;
import com.casestudy.GuestMicroService.models.Guest;
import com.casestudy.GuestMicroService.repository.GuestRepo;
//import com.casestudy.GuestMicroService.service.EmailServiceImpl;
import com.casestudy.GuestMicroService.service.GuestService;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/guest")
@CrossOrigin(origins = "*")
public class GuestController {
     @Autowired
	private GuestRepo guestRepo;
	@Autowired
	private GuestService guestServiceImpl;
	

	@GetMapping("/get")
	@ApiOperation(value = "Getting Guest Details")
	public List<Guest> getAllGuests() {
		return guestServiceImpl.getAllGuests();
	}

	@PostMapping("/add")
	public String addGuest(@Valid @RequestBody Guest guest) throws GuestNotFoundException {
	          Optional<Guest> existingById=guestRepo.findById(guest.getGuestId());
	          if(existingById.isPresent())
	          {
	        	  throw new GuestNotFoundException("Guest with this Id already exists");
	          }
			guestServiceImpl.addGuest(guest);
			return "{\"message\": \"Guest added successfully\"}";
		}
	

	@PutMapping("/modify/{guestId}")
	public String modifyGuest(@PathVariable String guestId, @RequestBody Guest guest) throws GuestNotFoundException {
		Optional<Guest> existingById=guestRepo.findById(guest.getGuestId());
        if(!existingById.isPresent())
        {
      	  throw new GuestNotFoundException("Guest with this Id is not present");
        }
			guestServiceImpl.modifyGuest(guestId, guest);
			return "{\"message\": \"Guest details updated successfully\"}";

	}

	@DeleteMapping("/delete/{guestId}")
	public String deleteGuest(@PathVariable String guestId) throws GuestNotFoundException {
			guestServiceImpl.deleteGuest(guestId);
			return "Guest Deleted with guestId " + guestId;
		}

	@GetMapping("/get/{guestId}")
	public Optional<Guest> getGuestById(@PathVariable String guestId) throws GuestNotFoundException {
			return guestServiceImpl.getGuestById(guestId);
		
		}
	}

