package com.inventory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.service.controller.InventoryController;
import com.inventory.service.exception.InventoryNotFoundException;
import com.inventory.service.model.Inventory;
import com.inventory.service.repo.InventoryRepository;
import com.inventory.service.service.InventoryService;


public class InventoryMicroServiceController {
	   @Mock
	    private InventoryService inventoryService;

	    @InjectMocks
	    private InventoryController inventoryController;
	    @Mock
	    private InventoryRepository inventoryrepo;
	public InventoryMicroServiceController() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllInventory_ReturnsListOfInventory() {
        // Arrange
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(new Inventory("2", "chairs", "big", 10));
        inventoryList.add(new Inventory("3", "glasses", "big", 8));

        // Stub the behavior of getAllInventorys method directly
        doReturn(inventoryList).when(inventoryService).getAllInventorys();

        // Act
        List<Inventory> result = inventoryController.getAllInventory();

        // Assert
        assertEquals(2, result.size());
        assertEquals("chairs", result.get(0).getInventoryName());
        assertEquals("glasses", result.get(1).getInventoryName());
    }
    @Test
    public void testAddInventory_ValidInventory_ReturnsSuccessMessage() throws InventoryNotFoundException {
        // Arrange
        Inventory inventory = new Inventory("5", "spoons", "small", 10);

        // Act
        String result = inventoryController.addInventory(inventory);

        // Assert
        assertEquals("Inventory Added", result);
        verify(inventoryService, times(1)).addInventory(inventory);

}
    @Test
    public void testModifyInventoryById_ValidInventoryId_ReturnsSuccessMessage() throws InventoryNotFoundException {
        // Arrange
        Inventory inventory = new Inventory("5", "spoons", "small", 10);

        // Act
        String result = inventoryController.modifyInventoryById("5", inventory);

        // Assert
        assertEquals("Inventory Updated with inventoryId 5", result);
        verify(inventoryService, times(1)).modifyInventoryById("5", inventory);
    }

    @Test
    public void testDeleteInventoryById_ValidInventoryId_ReturnsSuccessMessage() throws InventoryNotFoundException {
        // Arrange

        // Act
        String result = inventoryController.deleteInventoryById("5");

        // Assert
        assertEquals("Inventory Deleted with inventoryId 5", result);
        verify(inventoryService, times(1)).deleteInventoryById("5");
    }

    @Test
    public void testGetInventoryById_ValidInventoryId_ReturnsInventoryObject() throws InventoryNotFoundException {
        // Arrange
        Inventory inventory = new Inventory("3", "glasses", "big", 8);
        when(inventoryService.getInventoryById("1")).thenReturn(Optional.of(inventory));

        // Act
        Optional<Inventory> result = inventoryController.getInventoryById("1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("glasses", result.get().getInventoryName());
        assertEquals("big", result.get().getInventoryType());
        assertEquals(8, result.get().getInventoryStock());
    }

    @Test
    public void testGetInventoryById_InvalidInventoryId_ReturnsEmptyOptional() throws InventoryNotFoundException {
        // Arrange
        when(inventoryService.getInventoryById("2")).thenThrow(new InventoryNotFoundException());

        // Act
        Optional<Inventory> result = inventoryController.getInventoryById("2");

        // Assert
        assertFalse(result.isPresent());
    }
    @Test
    public void testGetSetInventoryId() {
        // Arrange
        String expectedInventoryId = "3";
        Inventory inventory = new Inventory();

        // Act
        inventory.setInventoryId(expectedInventoryId);
        String actualInventoryId = inventory.getInventoryId();

        // Assert
        assertEquals(expectedInventoryId, actualInventoryId);
    }
    @Test
    public void testToString() {
        // Arrange
        String expectedToString = "Inventory [inventoryId=123, inventoryName=Item1, inventoryType=Type1, inventoryStock=10]";
        Inventory inventory = new Inventory("3", "glasses", "big", 8);

        // Act
        String actualToString = inventory.toString();

        // Assert
        assertEquals(expectedToString, actualToString);
    }
//    @Test
//    public void testModifyInventoryById_InventoryNotFound() {
//        // Arrange
//        String nonExistentInventoryId = "nonExistentId";
//        Inventory inventoryToUpdate = new Inventory("123", "Name1", "Type1",10);
//
//        // Mocking repository to return Optional.empty() indicating no inventory found with given ID
//        when(inventoryrepo.findById(nonExistentInventoryId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        InventoryNotFoundException exception = assertThrows(InventoryNotFoundException.class,
//                () -> inventoryService.modifyInventoryById(nonExistentInventoryId, inventoryToUpdate));
//        assertEquals("No inventory ID is present", exception.getMessage());
//
//        // Verify that save method is not called when inventory ID is not found
//        verify(inventoryrepo, never()).save(any());
//    }
    @Test
    public void testModifyInventoryById_InventoryNotFound() {
        // Arrange
        String nonExistentInventoryId = "nonExistentId";
        Inventory inventoryToUpdate = new Inventory("1", "Name", "Type",10);

        // Mock behavior to return Optional.empty() when findById is called with non-existent inventory ID
        when(inventoryrepo.findById(nonExistentInventoryId)).thenReturn(Optional.empty());

        // Act & Assert
        InventoryNotFoundException exception = assertThrows(InventoryNotFoundException.class,
                () -> inventoryService.modifyInventoryById(nonExistentInventoryId, inventoryToUpdate));
        assertEquals("No inventory ID is present", exception.getMessage());

        // Verify that save method is not called when inventory ID is not found
        verify(inventoryrepo, never()).save(any());
    }
    @Test
    public void testAddInventory_ExceptionThrown() throws InventoryNotFoundException {
        // Arrange
        Inventory inventory = new Inventory(); // Add necessary details to the inventory object if required
        String errorMessage = "An error occurred while adding inventory.";

        // Mock behavior to throw an exception when addInventory is called
        doThrow(new RuntimeException(errorMessage)).when(inventoryService).addInventory(inventory);

        // Act
        String result = inventoryController.addInventory(inventory);

        // Assert
        assertEquals(errorMessage, result);
    }
}

