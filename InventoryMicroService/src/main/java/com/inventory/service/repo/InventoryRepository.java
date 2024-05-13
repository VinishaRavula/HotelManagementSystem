package com.inventory.service.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.service.model.Inventory;


@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String>{

}
