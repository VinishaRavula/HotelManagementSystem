package com.inventory.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventory.service.exception.InventoryNotFoundException;
import com.inventory.service.model.Inventory;

@Service
public interface InventoryService {

	List<Inventory> getAllInventorys();

	void addInventory(Inventory inventory) throws InventoryNotFoundException;


	void modifyInventoryById(String inventoryId, Inventory inventory) throws InventoryNotFoundException;

	void deleteInventoryById(String inventoryId) throws InventoryNotFoundException;

	Optional<Inventory> getInventoryById(String inventoryId) throws InventoryNotFoundException;

}
