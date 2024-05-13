package com.staff.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.staff.service.model.Staff;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String>{

	List<Staff> findByStaffName(String staffName);

}
