package com.staff.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.staff.service.exception.StaffNotFoundException;
import com.staff.service.model.Staff;

@Service
public interface StaffService {

	List<Staff> getAllStaffs();

	Optional<Staff> getStaffById(String staffId) throws StaffNotFoundException;

	void modifyStaffById(String staffId, Staff staff) throws StaffNotFoundException;

	void deleteStaffById(String staffId) throws StaffNotFoundException;

	void addStaff(Staff staff);

	public List<Staff> getStaffByName(String staffName) throws StaffNotFoundException;

}
