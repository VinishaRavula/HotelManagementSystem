package com.usermanagement.service.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.service.models.Users;

@Repository
public interface UserRepo extends MongoRepository<Users, String> {

	Optional<Users> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
