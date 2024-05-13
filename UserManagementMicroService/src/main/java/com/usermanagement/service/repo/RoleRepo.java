package com.usermanagement.service.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.service.models.EnumRole;
import com.usermanagement.service.models.Roles;

@Repository
public interface RoleRepo extends MongoRepository<Roles, String> {

	Optional<Roles> findByName(EnumRole name);

}
