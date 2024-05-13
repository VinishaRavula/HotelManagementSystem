package com.room.microservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import com.room.microservice.exception.RoomNotAvailableException;
import com.room.microservice.exception.RoomNotFoundException;
import com.room.microservice.models.Room;
import com.room.microservice.repository.RoomRepo;
import com.room.microservice.service.RoomServiceImpl;


//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import com.room.microservice.exception.RoomNotAvailableException;
//import com.room.microservice.exception.RoomNotFoundException;
//import com.room.microservice.models.Room;
//import com.room.microservice.repository.RoomRepo;
//import com.room.microservice.service.RoomServiceImpl;

//class RoomServiceImplTest {

//    @Mock
//    private RoomRepo roomRepo;
//
//    @InjectMocks
//    private RoomServiceImpl roomService;
//
//    @Test
//    void testGetAllRooms() {
//        // Mock data
//        List<Room> rooms = Arrays.asList(new Room(), new Room());
//        when(roomRepo.findAll()).thenReturn(rooms);
//
//        // Call the service method
//        List<Room> result = roomService.getAllRooms();
//
//        // Verify the result
//        assertEquals(2, result.size());
//        verify(roomRepo).findAll();
//    }
//
//    @Test
//    void testAddRoom() {
//        // Mock data
//        Room room = new Room();
//
//        // Call the service method
//        roomService.addRoom(room);
//
//        // Verify the result
//        verify(roomRepo).save(room);
//        assertNotNull(room.getRoomId());
//    }
//
//    @Test
//    void testModifyRoomById() {
//        // Mock data
//        Room room = new Room();
//        String roomId = "123";
//        Optional<Room> existingRoomOptional = Optional.of(new Room());
//
//        // Mock the repository
//        when(roomRepo.findById(roomId)).thenReturn(existingRoomOptional);
//
//        // Call the service method
//        assertDoesNotThrow(() -> roomService.modifyRoomById(roomId, room));
//
//        // Verify the result
//        verify(roomRepo).findById(roomId);
//        verify(roomRepo).save(any());
//    }
//
//    @Test
//    void testModifyRoomById_InvalidRoomId() {
//        // Mock data
//        Room room = new Room();
//        String roomId = "invalid";
//        Optional<Room> existingRoomOptional = Optional.empty();
//
//        // Mock the repository
//        when(roomRepo.findById(roomId)).thenReturn(existingRoomOptional);
//
//        // Call the service method
//        assertThrows(RoomNotFoundException.class, () -> roomService.modifyRoomById(roomId, room));
//
//        // Verify the result
//        verify(roomRepo).findById(roomId);
//        verifyNoInteractions(roomRepo.save(any()));
//    }
//
//    @Test
//    void testDeleteRoomById() {
//        // Mock data
//        String roomId = "123";
//
//        // Call the service method
//        assertDoesNotThrow(() -> roomService.deleteRoomById(roomId));
//
//        // Verify the result
//        verify(roomRepo).deleteById(roomId);
//    }
//
//    @Test
//    void testDeleteRoomById_InvalidRoomId() {
//        // Mock data
//        String roomId = "invalid";
//
//        // Mock the repository to throw RoomNotFoundException
//        doThrow(RoomNotFoundException.class).when(roomRepo).deleteById(roomId);
//
//        // Call the service method
//        assertThrows(RoomNotFoundException.class, () -> roomService.deleteRoomById(roomId));
//
//        // Verify the result
//        verify(roomRepo).deleteById(roomId);
//    }
//
//    @Test
//    void testGetRoomById() throws RoomNotFoundException {
//        // Mock data
//        Room room = new Room();
//        String roomId = "123";
//        Optional<Room> optionalRoom = Optional.of(room);
//
//        // Mock the repository
//        when(roomRepo.findById(roomId)).thenReturn(optionalRoom);
//
//        // Call the service method
//        Optional<Room> result = roomService.getRoomById(roomId);
//
//        // Verify the result
//        assertTrue(result.isPresent());
//        assertEquals(room, result.get());
//        verify(roomRepo).findById(roomId);
//    }
//
//    @Test
//    void testGetRoomById_InvalidRoomId() throws RoomNotFoundException {
//        // Mock data
//        String roomId = "invalid";
//        Optional<Room> optionalRoom = Optional.empty();
//
//        // Mock the repository
//        when(roomRepo.findById(roomId)).thenReturn(optionalRoom);
//
//        // Call the service method
//        Optional<Room> result = roomService.getRoomById(roomId);
//
//        // Verify the result
//        assertFalse(result.isPresent());
//        verify(roomRepo).findById(roomId);
//    }
//
//    @Test
//    void testGetRoomAvailable() throws RoomNotAvailableException {
//        // Mock data
//        boolean roomAvail = true;
//        List<Room> rooms = Arrays.asList(new Room(), new Room());
//
//        // Mock the repository
//        when(roomRepo.findByRoomAvail(roomAvail)).thenReturn(rooms);
//
//        // Call the service method
//        List<Room> result = roomService.getRoomAvailable(roomAvail);
//
//        // Verify the result
//        assertEquals(2, result.size());
//        verify(roomRepo).findByRoomAvail(roomAvail);
//    }
//
//    @Test
//    void testGetRoomAvailable_NotAvailable() {
//        // Mock data
//        boolean roomAvail = false;
//
//        // Mock the repository to throw RoomNotAvailableException
//        doThrow(RoomNotAvailableException.class).when(roomRepo).findByRoomAvail(roomAvail);
//
//        // Call the service method
//        assertThrows(RoomNotAvailableException.class, () -> roomService.getRoomAvailable(roomAvail));
//
//        // Verify the result
//        verify(roomRepo).findByRoomAvail(roomAvail);
//    }

public class RoomMicroServiceApplicationTests {

    @Mock
    private RoomRepo roomRepo;

    @InjectMocks
    private RoomServiceImpl roomService;

    public RoomMicroServiceApplicationTests() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("123", "Single Room", true, 100.0));
        rooms.add(new Room("456", "Double Room", true, 150.0));

        when(roomRepo.findAll()).thenReturn(rooms);

        List<Room> result = roomService.getAllRooms();

        assertEquals(2, result.size());
        assertEquals("Single Room", result.get(0).getRoomType());
        assertEquals("Double Room", result.get(1).getRoomType());
    }

    @Test
    public void testAddRoom() {
        Room room = new Room("789", "Junior Suite", true, 200.0);

        roomService.addRoom(room);

        verify(roomRepo, times(1)).save(room);
    }
    @Test
    public void testModifyRoomById_ExistingRoomId_SuccessfullyUpdatesRoom() throws RoomNotFoundException {
        // Arrange
        Room existingRoom = new Room("123", "Single", true, 100.0);
        Room modifiedRoom = new Room("123", "Double", true, 150.0);

        when(roomRepo.findById("123")).thenReturn(Optional.of(existingRoom));

        // Act
        roomService.modifyRoomById("123", modifiedRoom);

        // Assert
        assertEquals("Double", existingRoom.getRoomType());
        assertEquals(150.0, existingRoom.getRoomPrice());
        assertTrue(existingRoom.isRoomAvail());
        verify(roomRepo, times(1)).save(existingRoom);
    }

    @Test
    public void testModifyRoomById_NonExistingRoomId_ThrowsRoomNotFoundException() {
        // Arrange
        when(roomRepo.findById("456")).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RoomNotFoundException.class, () -> roomService.modifyRoomById("456", new Room()));
        verify(roomRepo, never()).save(any());
    }

    @Test
    public void testDeleteRoomById_ExistingRoomId_SuccessfullyDeletesRoom() throws RoomNotFoundException {
        // Arrange
        Room existingRoom = new Room("123", "Single Room", true, 100.0);

        // Act
        roomService.deleteRoomById("123");

        // Assert
        verify(roomRepo, times(1)).deleteById("123");
    }

    @Test
    public void testDeleteRoomById_NonExistingRoomId_ThrowsRoomNotFoundException() {
        // Arrange
        doThrow(RoomNotFoundException.class).when(roomRepo).deleteById("456");

        // Act and Assert
        assertThrows(RoomNotFoundException.class, () -> roomService.deleteRoomById("456"));
        verify(roomRepo, never()).deleteById("456");
    }

    @Test
    public void testGetRoomById_ExistingRoomId_ReturnsRoom() throws RoomNotFoundException {
        // Arrange
        Room room = new Room("123", "Single Room", true, 100.0);
        when(roomRepo.findById("123")).thenReturn(Optional.of(room));

        // Act
        Optional<Room> result = roomService.getRoomById("123");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Single Room", result.get().getRoomType());
        assertEquals(100.0, result.get().getRoomPrice());
        assertTrue(result.get().isRoomAvail());
    }

    @Test
    public void testGetRoomById_NonExistingRoomId_ReturnsEmptyOptional() throws RoomNotFoundException {
        // Arrange
        when(roomRepo.findById("456")).thenReturn(Optional.empty());

        // Act
        Optional<Room> result = roomService.getRoomById("456");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetRoomAvailable_RoomAvailable_ReturnsListOfRooms() throws RoomNotAvailableException {
        // Arrange
        Room room1 = new Room("123", "Single Room", true, 100.0);
        Room room2 = new Room("456", "Double Room", true, 150.0);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);

        when(roomRepo.findByRoomAvail(true)).thenReturn(rooms);

        // Act
        List<Room> result = roomService.getRoomAvailable(true);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Single Room", result.get(0).getRoomType());
        assertEquals("Double Room", result.get(1).getRoomType());
    }

    @Test
    public void testGetRoomAvailable_RoomNotAvailable_ThrowsRoomNotAvailableException() {
        // Arrange
        when(roomRepo.findByRoomAvail(false)).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(RoomNotAvailableException.class, () -> roomService.getRoomAvailable(false));
    }
    @Test
    public void testDeleteRoomById() throws RoomNotFoundException {
        // Arrange
        String roomIdToDelete = "roomIdToDelete";
        Room roomToDelete = new Room();
        roomToDelete.setRoomId(roomIdToDelete);

        // Mock behavior of findById method
        when(roomRepo.findById(roomIdToDelete)).thenReturn(Optional.of(roomToDelete));

        // Act
        roomService.deleteRoomById(roomIdToDelete);

        // Assert
        verify(roomRepo, times(1)).deleteById(roomIdToDelete);
}
}