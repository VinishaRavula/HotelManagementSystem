package com.inventory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventory.service.exception.InventoryNotFoundException;
import com.inventory.service.model.Inventory;
import com.inventory.service.repo.InventoryRepository;
import com.inventory.service.service.InventoryServiceImpl;


@ExtendWith(MockitoExtension.class)
public class InventoryMicroServiceApplicationTests {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    public void testGetAllInventorys_ReturnsListOfInventory() {
        // Arrange
        List<Inventory> expectedInventoryList = new ArrayList<>();
        expectedInventoryList.add(new Inventory("2", "chairs", "big", 10));
        expectedInventoryList.add(new Inventory("3", "glasses", "big", 8));
        doReturn(expectedInventoryList).when(inventoryRepository).findAll();

        // Act
        List<Inventory> result = inventoryService.getAllInventorys();

        // Assert
        assertEquals(expectedInventoryList, result);
    }

    @Test
    public void testAddInventory_ValidInventory_SavesInventory() throws InventoryNotFoundException {
        // Arrange
        Inventory inventory = new Inventory("2", "chairs", "big", 10);

        // Act
        inventoryService.addInventory(inventory);

        // Assert
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    public void testModifyInventoryById_ValidInventoryId_UpdatesInventory() throws InventoryNotFoundException {
        // Arrange
        Inventory existingInventory = new Inventory("2", "chairs", "big", 10);
        Inventory updatedInventory = new Inventory("2", "chairs", "small", 20);
        doReturn(Optional.of(existingInventory)).when(inventoryRepository).findById("2");

        // Act
        inventoryService.modifyInventoryById("2", updatedInventory);

        // Assert
        assertEquals("chairs", existingInventory.getInventoryName());
        assertEquals("small", existingInventory.getInventoryType());
        assertEquals(20, existingInventory.getInventoryStock());
        verify(inventoryRepository, times(1)).save(existingInventory);
    }

    @Test
    public void testDeleteInvenotryById_ValidInventoryId_DeletesInventory() throws InventoryNotFoundException {
        // Act
        inventoryService.deleteInventoryById("2");

        // Assert
        verify(inventoryRepository, times(1)).deleteById("2");
    }

    @Test
    public void testGetInventoryById_ValidInventoryId_ReturnsInventoryObject() throws InventoryNotFoundException {
        // Arrange
        Inventory expectedInventory = new Inventory("3", "glasses", "big", 8);
        doReturn(Optional.of(expectedInventory)).when(inventoryRepository).findById("1");

        // Act
        Optional<Inventory> result = inventoryService.getInventoryById("3");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedInventory, result.get());
    }

    @Test
    public void testGetInventoryById_InvalidInventoryId_ReturnsEmptyOptional() throws InventoryNotFoundException {
        // Arrange
        doReturn(Optional.empty()).when(inventoryRepository).findById("3");

        // Act
        Optional<Inventory> result = inventoryService.getInventoryById("3");

        // Assert
        assertFalse(result.isPresent());
    }
    @Test
    public void testAddInventory_NullOrEmptyFields() {
        // Arrange
        Inventory inventoryWithNullName = new Inventory("1", null, "Type",10);
        Inventory inventoryWithEmptyName = new Inventory("1", "", "Type",20);
        Inventory inventoryWithNullType = new Inventory("1", "Name", null,8);
        Inventory inventoryWithEmptyType = new Inventory("1", "Name", "",7);

        // Act & Assert
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> inventoryService.addInventory(inventoryWithNullName));
        assertEquals("Inventory Field cannot be null or empty", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> inventoryService.addInventory(inventoryWithEmptyName));
        assertEquals("Inventory Field cannot be null or empty", exception2.getMessage());

        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class,
                () -> inventoryService.addInventory(inventoryWithNullType));
        assertEquals("Inventory Field cannot be null or empty", exception3.getMessage());

        IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class,
                () -> inventoryService.addInventory(inventoryWithEmptyType));
        assertEquals("Inventory Field cannot be null or empty", exception4.getMessage());

        // Verify that save method is not called when inventory fields are null or empty
        verify(inventoryRepository, never()).save(any());
    }
    @Test
    public void testDeleteInventoryById_ExistingInventory() throws InventoryNotFoundException {
        // Arrange
        String existingInventoryId = "existingId";
        Inventory existingInventory = new Inventory(existingInventoryId, "Name", "Type",10);

        // Mock behavior to return an existing inventory when findById is called with existing ID
        when(inventoryRepository.findById(existingInventoryId)).thenReturn(Optional.of(existingInventory));

        // Act
        inventoryService.deleteInventoryById(existingInventoryId);

        // Assert
        verify(inventoryRepository, times(1)).deleteById(existingInventoryId);
    }

    @Test
    public void testDeleteInventoryById_NonExistentInventory() {
        // Arrange
        String nonExistentInventoryId = "nonExistentId";

        // Mock behavior to return Optional.empty() when findById is called with non-existent inventory ID
        when(inventoryRepository.findById(nonExistentInventoryId)).thenReturn(Optional.empty());

        // Act & Assert
        InventoryNotFoundException exception = assertThrows(InventoryNotFoundException.class,
                () -> inventoryService.deleteInventoryById(nonExistentInventoryId));
        assertEquals("No inventory ID is present", exception.getMessage());

        // Verify that deleteById method is not called when inventory ID is not found
        verify(inventoryRepository, never()).deleteById(any());
    }
    @Test
    public void testGetInventoryById_ReturnsExistingInventory() throws InventoryNotFoundException {
        // Arrange
        String existingInventoryId = "existingId";
        Inventory existingInventory = new Inventory(existingInventoryId, "Name", "Type",10);

        // Mock behavior to return an existing inventory when findById is called with existing ID
        when(inventoryRepository.findById(existingInventoryId)).thenReturn(Optional.of(existingInventory));

        // Act
        Optional<Inventory> result = inventoryService.getInventoryById(existingInventoryId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(existingInventory, result.get());
    }
    @Test
    public void testModifyInventoryById_InventoryNotFound() {
        // Arrange
        String nonExistentInventoryId = "nonExistentId";
        
        // Mock behavior to return Optional.empty() when findById is called with non-existent inventory ID
        when(inventoryRepository.findById(nonExistentInventoryId)).thenReturn(Optional.empty());

        // Act & Assert
        InventoryNotFoundException exception = assertThrows(InventoryNotFoundException.class,
                () -> inventoryService.modifyInventoryById(nonExistentInventoryId, new Inventory()));
        assertEquals("No inventory ID is present", exception.getMessage());
    }
}



