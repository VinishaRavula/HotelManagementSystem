package com.staff.service.controller;

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
import com.staff.service.exception.StaffNotFoundException;
import com.staff.service.model.Staff;
import com.staff.service.repository.StaffRepository;
import com.staff.service.service.StaffService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private StaffService staffServiceImpl;

	@GetMapping("/get")
	public List<Staff> getAllStaffs() {
		return staffServiceImpl.getAllStaffs();
	}

	@GetMapping("/get/id/{staffId}")
	public Optional<Staff> getStaffById(@PathVariable String staffId) throws StaffNotFoundException {
			return staffServiceImpl.getStaffById(staffId);
		}


	@GetMapping("/get/name/{staffName}")
	public ResponseEntity<List<Staff>> getStaffByName(@PathVariable String staffName) throws StaffNotFoundException {
			List<Staff> staff = staffServiceImpl.getStaffByName(staffName);
			return ResponseEntity.ok(staff);

		}


	@DeleteMapping("/delete/{staffId}")
	public String deleteStaffById(@PathVariable String staffId) throws StaffNotFoundException {
		
			staffServiceImpl.deleteStaffById(staffId);
			return "{\"message\": \"Staff deleted successfully\"}";
		
		}
	

	@PutMapping("/modify/{staffId}")
	public String modifyStaffById(@RequestBody Staff staff, @PathVariable String staffId) throws StaffNotFoundException {

			staffServiceImpl.modifyStaffById(staffId, staff);
			return "{\"message\": \"Staff modified succesfully\"}";
		
	}

	@PostMapping("/add")
	public String addStaff(@Valid @RequestBody Staff staff) throws StaffNotFoundException {
		Optional<Staff>existingById=staffRepository.findById(staff.getStaffId());
        if(existingById.isPresent())
        {
      	  throw new StaffNotFoundException("Staff with this Id already exists");
        }
		staffServiceImpl.addStaff(staff);
		return "{\"message\": \"Added Staff\"}";

		
	}


}
