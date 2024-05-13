package com.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.usermanagement.service.models.EnumRole;
import com.usermanagement.service.models.Roles;
import com.usermanagement.service.repo.RoleRepo;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.*"})
@EnableMongoRepositories
public class UserManagementMicroServiceApplication implements CommandLineRunner {

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(UserManagementMicroServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Check if roles already exist in the collection
		if (roleRepo.count() == 0) {
			// Insert roles from EnumRole into the collection
			for (EnumRole enumRole : EnumRole.values()) {
				roleRepo.save(new Roles(enumRole));
			}
		}
	}
}