package com.room.microservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.room.microservice.controller.RoomController;
import com.room.microservice.exception.RoomNotAvailableException;
import com.room.microservice.exception.RoomNotFoundException;
import com.room.microservice.models.Room;
import com.room.microservice.service.RoomService;

@ExtendWith(MockitoExtension.class)
public class RoomMicroServiceContrllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;
    public RoomMicroServiceContrllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("123", "Single Room", true, 100.0));
        rooms.add(new Room("456", "Double Room", true, 150.0));

        when(roomService.getAllRooms()).thenReturn(rooms);

        List<Room> result = roomController.getAllRooms();

        assertEquals(2, result.size());
        assertEquals("Single Room", result.get(0).getRoomType());
        assertEquals("Double Room", result.get(1).getRoomType());
    }

    @Test
    public void testAddRoom() throws RoomNotFoundException {
        Room room = new Room("789", "Single Room", true, 200.0);
        String expectedResult = "Room Added with roomId 789";

        String result = roomController.addRoom(room);

        assertEquals(expectedResult, result);
        verify(roomService, times(1)).addRoom(room);
    }
    @Test
    public void testModifyRoomById_ValidRoomId_ReturnsSuccessMessage() throws RoomNotFoundException {
        Room room = new Room("123", "Single Room", true, 100.0);
        doReturn(true).when(roomService).modifyRoomById("123", room);

        String result = roomController.modifyRoomById(room, "123");

        assertEquals("Room Modified with roomId 123", result);
    }
//    @Test
//    public void testModifyRoomById_ValidRoomId_ReturnsSuccessMessage() {
//        Room room = new Room("123", "Single", true, 100.0);
//
//        when(roomService.modifyRoomById("123", room)).thenReturn(true);
//
//        String result = roomController.modifyRoomById(room, "123");
//
//        assertEquals("Room Modified with roomId 123", result);
//    }

    @Test
    public void testModifyRoomById_InvalidRoomId_ReturnsErrorMessage() throws RoomNotFoundException {
        Room room = new Room("456", "Double Room", true, 150.0);
        doThrow(new RoomNotFoundException()).when(roomService).modifyRoomById("456", room);

        String result = roomController.modifyRoomById(room, "456");

        assertEquals("Invalid Room Id", result);
    }
    @Test
    public void testToString_ReturnsExpectedStringRepresentation() {
        // Create a room object with specific values
        Room room = new Room("123", "Single", true, 100.0);

        // Define the expected string representation
        String expectedString = "Room [roomId=123, roomType=Single, roomAvail=true, roomPrice=100.0]";

        // Call the toString method and verify the result
        String actualString = room.toString();

        assertEquals(expectedString, actualString);
    }
    @Test
    void testDeleteRoomById_ValidRoomId() throws RoomNotFoundException {
        String result = roomController.deleteRoomById("106");
 
        
        verify(roomService, times(1)).deleteRoomById("106");
        assertEquals("Room Deleted with roomId 106", result);
    }
 
@Test
    void testDeleteRoomById_InvalidRoomId() throws RoomNotFoundException {
        // Service throws RoomNotFoundException
        doThrow(new RoomNotFoundException()).when(roomService).deleteRoomById("107");
 
        // Test
        String result = roomController.deleteRoomById("107");
 
        // Verify
        verify(roomService, times(1)).deleteRoomById("107");
        assertEquals("Invalid Room Id", result);
    }
 
    @Test
    void testGetRoomById_ValidRoomId() throws RoomNotFoundException {
        Room room = new Room("108", "Single", true, 90.0);
        Optional<Room> optionalRoom = Optional.of(room);
 
        when(roomService.getRoomById("108")).thenReturn(optionalRoom);
 
        
        Optional<Room> result = roomController.getRoomById("108");
 
        
        verify(roomService, times(1)).getRoomById("108");
        assertEquals(optionalRoom, result);
    }
 
    @Test
    void testGetRoomById_InvalidRoomId() throws RoomNotFoundException {
        when(roomService.getRoomById("109")).thenReturn(Optional.empty());
 
        
        Optional<Room> result = roomController.getRoomById("109");
 
        
        verify(roomService, times(1)).getRoomById("109");
        assertEquals(Optional.empty(), result);
    }
 
    @Test
    void testGetAvailRoom_RoomAvailable() throws RoomNotAvailableException {
        List<Room> availableRooms = new ArrayList<>();
        availableRooms.add(new Room("110", "Single", true, 80.0));
        availableRooms.add(new Room("111", "Double", true, 120.0));
 
        when(roomService.getRoomAvailable(true)).thenReturn(availableRooms);
 
        
        ResponseEntity<List<Room>> result = roomController.getAvailRoom(true);
 
        
        verify(roomService, times(1)).getRoomAvailable(true);
        assertEquals(availableRooms, result.getBody());
    }
 
    @Test
    void testGetAvailRoom_RoomNotAvailable() throws RoomNotAvailableException {
        when(roomService.getRoomAvailable(false)).thenThrow(new RoomNotAvailableException());
 
        
        ResponseEntity<List<Room>> result = roomController.getAvailRoom(false);
 
      
        verify(roomService, times(1)).getRoomAvailable(false);
        assertEquals(ResponseEntity.notFound().build(), result);
    }
//    @Test
//    void testModifyRoomById_ValidRoomId() {
//        Room roomToUpdate = new Room("104", "Standard", true, 120.0);
// 
//        when(roomService.modifyRoomById("104", roomToUpdate)).thenReturn("Room Modified with roomId 104");
// 
//      
//        String result = roomController.modifyRoomById(roomToUpdate, "104");
// 
//        
//        verify(roomService, times(1)).modifyRoomById("104", roomToUpdate);
//        assertEquals("Room Modified with roomId 104", result);
//    }
// 
    @Test
    void testModifyRoomById_ValidRoomId() throws RoomNotFoundException {
        Room roomToUpdate = new Room("104", "Standard", true, 120.0);
     
        
        doNothing().when(roomService).modifyRoomById("104", roomToUpdate);
     
        
        String result = roomController.modifyRoomById(roomToUpdate, "104");
     
        
        verify(roomService, times(1)).modifyRoomById("104", roomToUpdate);
     
       
        assertEquals("Room Modified with roomId 104", result);
    }
    @Test
    void testModifyRoomById_InvalidRoomId() throws RoomNotFoundException {
        Room roomToUpdate = new Room("105", "Deluxe", false, 180.0);
 
        
        doThrow(new RoomNotFoundException()).when(roomService).modifyRoomById("105", roomToUpdate);
 
       
        String result = roomController.modifyRoomById(roomToUpdate, "105");
 
     
        verify(roomService, times(1)).modifyRoomById("105", roomToUpdate);
        assertEquals("Invalid Room Id", result);
    }
}
