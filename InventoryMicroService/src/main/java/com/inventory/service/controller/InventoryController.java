package com.inventory.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.exception.InventoryNotFoundException;
import com.inventory.service.model.Inventory;
import com.inventory.service.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryServiceImpl;

	@GetMapping("/get")
	public List<Inventory> getAllInventory() {
		return inventoryServiceImpl.getAllInventorys();
	}
	
	@PostMapping("/add")
	public String addInventory(@Valid @RequestBody Inventory inventory) {
		try {
			inventoryServiceImpl.addInventory(inventory);
			return "{\"message\": \"Inventory added successfully\"}";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PutMapping("/modify/{inventoryId}")
	public String modifyInventoryById(@PathVariable String inventoryId, @RequestBody Inventory inventory) throws InventoryNotFoundException {
	
			inventoryServiceImpl.modifyInventoryById(inventoryId, inventory);
			return "{\"message\": \"Inventory updated succesfully\"}";
	
		}


	@DeleteMapping("/delete/{inventoryId}")
	public String deleteInventoryById(@PathVariable String inventoryId) throws InventoryNotFoundException {
		
			inventoryServiceImpl.deleteInventoryById(inventoryId);
			return "{\"message\": \"Inventory deleted successfully\"}";
		
		}


	@GetMapping("/get/{inventoryId}")
	public Optional<Inventory> getInventoryById(@PathVariable String inventoryId) throws InventoryNotFoundException {
	
			return inventoryServiceImpl.getInventoryById(inventoryId);
		
		}
	}


