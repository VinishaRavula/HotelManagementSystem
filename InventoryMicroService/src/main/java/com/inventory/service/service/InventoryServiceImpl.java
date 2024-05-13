package com.inventory.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.casestudy.GuestMicroService.exception.GuestNotFoundException;
//import com.casestudy.GuestMicroService.models.Guest;
import com.inventory.service.exception.InventoryNotFoundException;
import com.inventory.service.model.Inventory;
import com.inventory.service.repo.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepo;

	@Override
	public List<Inventory> getAllInventorys() {
		// TODO Auto-generated method stub
		return inventoryRepo.findAll();
	}

	@Override
	public void addInventory(Inventory inventory) throws InventoryNotFoundException {
	    // Check if inventoryName is null or empty
	    
		Optional<Inventory> existingById=inventoryRepo.findById(inventory.getInventoryId());
        if(existingById.isPresent())
        {
      	  throw new InventoryNotFoundException("Inventory with this Id already exists");
        }
	    // Save the inventory
	    inventoryRepo.save(inventory);
	    }
	


	@Override
	public void modifyInventoryById(String inventoryId, Inventory inventory) throws InventoryNotFoundException {
		Optional<Inventory> existingInventoryOptional = inventoryRepo.findById(inventoryId);
		if (existingInventoryOptional.isPresent()) {
			Inventory existingInventory = existingInventoryOptional.get();
			existingInventory.setInventoryName(inventory.getInventoryName());
			existingInventory.setInventoryStock(inventory.getInventoryStock());
			existingInventory.setInventoryType(inventory.getInventoryType());

						inventoryRepo.save(existingInventory);
		}
			else throw new InventoryNotFoundException("No inventory ID is present");
			}
		//}
	


	@Override
	public void deleteInventoryById(String inventoryId) throws InventoryNotFoundException {
		Optional<Inventory> existingInventoryOptional = inventoryRepo.findById(inventoryId);
		if (existingInventoryOptional.isPresent()) {
		// TODO Auto-generated method stub
		inventoryRepo.deleteById(inventoryId);
		} 
		else throw new InventoryNotFoundException("No inventory ID is present");

	}

	@Override
	public Optional<Inventory> getInventoryById(String inventoryId) throws InventoryNotFoundException {
		// TODO Auto-generated method stub
		Optional<Inventory> existingInventoryOptional = inventoryRepo.findById(inventoryId);
		if (existingInventoryOptional.isPresent()) {
		return inventoryRepo.findById(inventoryId);
		}
		else throw new InventoryNotFoundException("No inventory ID is present");
	}

}
