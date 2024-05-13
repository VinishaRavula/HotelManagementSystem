package com.inventory.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryMicroServiceApplication.class, args);
	}

}
