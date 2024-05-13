package com.staff.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.service.exception.StaffNotFoundException;
import com.staff.service.model.Staff;
import com.staff.service.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepo;

	@Override
	public List<Staff> getAllStaffs() {
		// TODO Auto-generated method stub
		return staffRepo.findAll();
	}

	@Override
	public Optional<Staff> getStaffById(String staffId) throws StaffNotFoundException {
		// TODO Auto-generated method stub
		Optional<Staff> existingStaffOptional = staffRepo.findById(staffId);

		if (existingStaffOptional.isPresent()) {
		return staffRepo.findById(staffId);
		}
		else throw new StaffNotFoundException("Staff with this ID is not present");
		
	}

	@Override
	public void modifyStaffById(String staffId, Staff staff) throws StaffNotFoundException {
		// TODO Auto-generated method
		Optional<Staff> existingStaffOptional = staffRepo.findById(staffId);

		if (existingStaffOptional.isPresent()) {
			Staff existingStaff = existingStaffOptional.get();

			// Update the necessary fields of the existing staff record
			existingStaff.setStaffName(staff.getStaffName());
			existingStaff.setStaffAge(staff.getStaffAge());
			existingStaff.setStaffRole(staff.getStaffRole());
			existingStaff.setStaffAddress(staff.getStaffAddress());
			existingStaff.setStaffSalary(staff.getStaffSalary());
			existingStaff.setStaffEmail(staff.getStaffEmail());
			staffRepo.save(existingStaff);
		}
		else throw new StaffNotFoundException("Staff with this ID is not present");
	}

	@Override
	public void deleteStaffById(String staffId) throws StaffNotFoundException {
		// TODO Auto-generated method stub
		Optional<Staff> existingStaffOptional = staffRepo.findById(staffId);

		if (existingStaffOptional.isPresent()) {
		staffRepo.deleteById(staffId);
		}
		else throw new StaffNotFoundException("Staff with this ID is not present");
	}

	@Override
	public void addStaff(Staff staff) {	    
		staffRepo.save(staff);
	    }
	

	@Override
	public List<Staff> getStaffByName(String staffName) throws StaffNotFoundException {
		// TODO Auto-generated method stub
		if (!staffName.matches("^[a-zA-Z\\s]+$")) {
	        throw new StaffNotFoundException("Invalid Staff Name");
	    }
		List<Staff> staff = staffRepo.findByStaffName(staffName);
		return staff;

	}

}
