package com.casestudy.GuestMicroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.casestudy.GuestMicroService.controller.GuestController;
import com.casestudy.GuestMicroService.exception.GuestNotFoundException;
import com.casestudy.GuestMicroService.models.Guest;
import com.casestudy.GuestMicroService.repository.GuestRepo;
import com.casestudy.GuestMicroService.service.GuestService;
import com.casestudy.GuestMicroService.service.GuestServiceImpl;

@ExtendWith(MockitoExtension.class)
	public class GuestMicroServiceControllerTest {
	@Mock
  private GuestRepo guestRepo;
	    @Mock
	    private GuestService guestService;

	    @InjectMocks
	    private GuestController guestController;
	    @Autowired
	    private GuestServiceImpl guestServiceImpl;

	    @Test
	    public void testGetAllGuests_ReturnsListOfGuests() {
	        // Arrange
	        List<Guest> expectedGuestList = new ArrayList<>();
	        expectedGuestList.add(new Guest("1",8712349829L,"rahul","rahul@gmail.com","Female","hyd"));
	        expectedGuestList.add(new Guest("2", 8712349829L, "amruta", "amrutayanakanchi@gmail.com", "Female", "tuni"));
	        when(guestService.getAllGuests()).thenReturn(expectedGuestList);

	        // Act
	        List<Guest> result = guestController.getAllGuests();

	        // Assert
	        assertEquals(expectedGuestList, result);


}
	    @Test
	    public void testAddGuest_ValidGuest_ReturnsGuestId() throws GuestNotFoundException {
	        // Arrange
	        Guest guest = new Guest("1",8712349829L,"rahul","rahul@gmail.com","Female","hyd");

	        // Act
	        String result = guestController.addGuest(guest);

	        // Assert
	        assertEquals("1", result);
	        verify(guestService, times(1)).addGuest(guest);
	    }

	    @Test
	    public void testModifyGuest_ValidGuestIdAndGuest_ReturnsSuccessMessage() throws GuestNotFoundException {
	        // Arrange
	        String guestId = "1";
	        Guest guest = new Guest("1",8712349829L,"rahul","rahul@gmail.com","Female","hyd");

	        // Act
	        String result = guestController.modifyGuest(guestId, guest);

	        // Assert
	        assertEquals("Guest Updated with the guestId " + guestId, result);
	        verify(guestService, times(1)).modifyGuest(guestId, guest);
	    }

	    @Test
	    public void testDeleteGuest_ValidGuestId_ReturnsSuccessMessage() throws GuestNotFoundException {
	        // Arrange
	        String guestId = "1";

	        // Act
	        String result = guestController.deleteGuest(guestId);

	        // Assert
	        assertEquals("Guest Deleted with guestId " + guestId, result);
	        verify(guestService, times(1)).deleteGuest(guestId);
	    }

	    @Test
	    public void testGetGuestById_ValidGuestId_ReturnsGuestObject() throws GuestNotFoundException {
	        // Arrange
	        String guestId = "1";
	        Guest expectedGuest = new Guest("1",8712349829L,"rahul","rahul@gmail.com","Female","hyd");
	        when(guestService.getGuestById(guestId)).thenReturn(Optional.of(expectedGuest));

	        // Act
	        Optional<Guest> result = guestController.getGuestById(guestId);

	        // Assert
	        assertTrue(result.isPresent());
	        assertEquals(expectedGuest, result.get());
	    }

	    @Test
	    public void testGetGuestById_InvalidGuestId_ReturnsEmptyOptional() throws GuestNotFoundException {
	        // Arrange
	        String guestId = "1";
	        when(guestService.getGuestById(guestId)).thenReturn(Optional.empty());

	        // Act
	        Optional<Guest> result = guestController.getGuestById(guestId);

	        // Assert
	        assertFalse(result.isPresent());
	    }
	    @Test
	    void testSetAndGetGuestId() {
	        // Arrange
	        Guest guest = new Guest();
	        
	        // Act
	        guest.setGuestId("123");
	        
	        // Assert
	        assertEquals("123", guest.getGuestId());
	    }

	    @Test
	    void testSetAndGetGuestContact() {
	        // Arrange
	        Guest guest = new Guest();
	        
	        // Act
	        guest.setGuestContact(1234567890);
	        
	        // Assert
	        assertEquals(1234567890, guest.getGuestContact());
	    }

	    @Test
	    void testSetAndGetGuestName() {
	        // Arrange
	        Guest guest = new Guest();
	        
	        // Act
	        guest.setGuestName("John Doe");
	        
	        // Assert
	        assertEquals("John Doe", guest.getGuestName());
	    }

	    @Test
	    void testSetAndGetGuestEmail() {
	        // Arrange
	        Guest guest = new Guest();
	        
	        // Act
	        guest.setGuestEmail("john@example.com");
	        
	        // Assert
	        assertEquals("john@example.com", guest.getGuestEmail());
	    }

	    @Test
	    void testSetAndGetGuestGender() {
	        // Arrange
	        Guest guest = new Guest();
	        
	        // Act
	        guest.setGuestGender("Male");
	        
	        // Assert
	        assertEquals("Male", guest.getGuestGender());
	    }

	    @Test
	    void testSetAndGetGuestAddress() {
	        // Arrange
	        Guest guest = new Guest();
	        
	        // Act
	        guest.setGuestAddress("hyd");
	        
	        // Assert
	        assertEquals("hyd", guest.getGuestAddress());
	    }
//	    @Test
//	    void testAddGuest_ExceptionCaught() {
//	        // Arrange
//	        GuestServiceImpl guestService = mock(GuestServiceImpl.class); // Mock the GuestServiceImpl
//	        //GuestController guestController = new GuestController(guestService); // Create controller with the mocked service instance
//	        Guest guest = new Guest(); // Create a guest object
//
//	        // Mock the behavior of guestServiceImpl.addGuest to throw an exception
//	        doThrow(new Exception()).when(guestService).addGuest(guest);
//
//	        // Act
//	        String response = guestController.addGuest(guest);
//
//	        // Assert
//	        assertEquals("Use Proper Input", response.getBody()); // Check if the response body matches the expected value
//	    }
	    @Test
	    void testAddGuest_ExceptionCaught() throws GuestNotFoundException {
	        // Arrange
	        GuestServiceImpl guestService = mock(GuestServiceImpl.class); // Mock the GuestServiceImpl
	        // GuestController guestController = new GuestController(guestService); // Create controller with the mocked service instance
	        Guest guest = new Guest(); // Create a guest object

	        // Mock the behavior of guestServiceImpl.addGuest to throw an exception
	        doThrow(new Exception()).when(guestService).addGuest(guest);

	        // Act
	        String response = guestController.addGuest(guest);

	        // Assert
	        assertEquals("Use Proper Input", response); // Check if the response matches the expected value
	    }
	    @Test
	    void testGetGuestById_GuestNotFoundExceptionCaught() throws GuestNotFoundException {
	        // Arrange
	        String guestId = "invalidGuestId";
	        when(guestServiceImpl.getGuestById(guestId)).thenThrow(GuestNotFoundException.class);

	        // Act
	        Optional<Guest> result = guestController.getGuestById(guestId);

	        // Assert
	        assertTrue(result.isEmpty()); // Check if the result is an empty
	    }
//	    @Test
//	    public void testAddGuest() throws GuestNotFoundException {
//	        // Mock Guest object
//	        Guest guest = new Guest();
//	        guest.setGuestId("1");
//	        guest.setGuestName("John Doe");
//	        guest.setGuestEmail("john@example.com");
//	        guest.setGuestGender("Male");
//	        guest.setGuestAddress("123 Main Street");
//	        guest.setGuestContact(1234567890);
//
//	        // Mock behavior of guestRepository
//	        when(guestRepo.findById("1")).thenReturn(Optional.empty()); // Assume guest with ID '1' doesn't exist yet
//	        when(guestRepo.save(guest)).thenReturn(guest);
//
//	        // Perform the method to be tested
//	        String result = guestService.addGuest(guest);
//
//	        // Verify that the guest was added successfully
//	        assertEquals("Guest added with the guestID 1", result);
//	    }
	    @Test
	    public void testAddGuest() throws GuestNotFoundException {
	        // Mock Guest object
	        Guest guest = new Guest();
	        guest.setGuestId("10");
	        guest.setGuestContact(1234567890);
	        guest.setGuestName("swarna");
	        guest.setGuestEmail("swarna@gmailcom");
	        guest.setGuestGender("Male");
	        guest.setGuestAddress("biharr");
	        

	        // Mock behavior of guestRepository
	        when(guestRepo.findById("10")).thenReturn(Optional.empty()); // Assume guest with ID '1' doesn't exist yet
	        when(guestRepo.save(guest)).thenReturn(guest);

	        // Verify that the guestRepository.save method is called with the correct guest object
	        guestService.addGuest(guest);
	        verify(guestRepo, times(1)).save(guest);
	    }
	    @Test
	    public void testModifyGuest_GuestNotFoundException() {
	        // Mock Guest object
	        Guest guest = new Guest();
	        guest.setGuestId("10");
	        guest.setGuestContact(1234567890);// Assuming guest with ID '10' does not exist
	        guest.setGuestName("swarna");
	        guest.setGuestEmail("swarna@gmailcom");
	        guest.setGuestGender("Male");
	        guest.setGuestAddress("biharr");
	        

	        // Mock behavior of guestRepo.findById to return an empty Optional, indicating guest doesn't exist
	        when(guestRepo.findById("10")).thenReturn(Optional.empty());

	        // Perform the method to be tested and catch the exception
	        assertThrows(GuestNotFoundException.class, () -> {
	            guestController.modifyGuest("10", guest);
	        });
	        
	    }
	    @Test
	    public void testAddGuest_GuestNotFoundException() {
	        // Mock Guest object
	        Guest guest = new Guest();
	        guest.setGuestId("20");
	        guest.setGuestContact(97124578908L);// Assuming guest with ID '1' already exists
	        guest.setGuestName("wdwdc");
	        guest.setGuestEmail("john@example.com");
	        guest.setGuestGender("Male");
	        guest.setGuestAddress("thuyyh");
	       

	        // Mock behavior of guestRepo.findById to return an Optional containing a guest with the same ID
	        when(guestRepo.findById("20")).thenReturn(Optional.of(guest));

	        // Perform the method to be tested and catch the exception
	        try {
	            guestController.addGuest(guest);
	            fail("Expected GuestNotFoundException was not thrown");
	        } catch (GuestNotFoundException e) {
	            // Verify that the exception message is correct
	            assertEquals("Guest with this Id already exists", e.getMessage());
	        }

	        // Verify that guestServiceImpl.addGuest method is not called
	        verify(guestServiceImpl, never()).addGuest(any(Guest.class));
	    }
//	    @Test
//	    public void testModifyGuest_GuestNotFoundException() {
//	        // Mock guest data
//	        String guestId = "1";
//	        Guest guest = new Guest();
//	        guest.setGuestId(guestId);
//
//	        // Mock behavior of guestRepo.findById to return an empty optional
//	        when(guestRepo.findById(guestId)).thenReturn(Optional.empty());
//
//	        // Perform the method to be tested and catch the exception
//	        try {
//	            guestController.modifyGuest(guestId, guest);
//	            fail("Expected GuestNotFoundException was not thrown");
//	        } catch (GuestNotFoundException e) {
//	            // Verify that the exception message is correct
//	            assertEquals("Guest with this Id is not present", e.getMessage());
//	        }
//
//	        // Verify that guestServiceImpl.modifyGuest is not invoked
//	        //verify(guestServiceImpl, never()).modifyGuest(anyString(), any(Guest.class));
//	    }

	    @Test
	    public void testModifyGuest_Success() throws GuestNotFoundException {
	        // Mock guest data
	        String guestId = "1";
	        Guest guest = new Guest();
	        guest.setGuestId(guestId);

	        // Mock behavior of guestRepo.findById to return a non-empty optional
	        when(guestRepo.findById(guestId)).thenReturn(Optional.of(guest));

	        // Perform the method to be tested
	        String result = guestController.modifyGuest(guestId, guest);

	        // Verify that guestServiceImpl.modifyGuest is invoked with the correct arguments
	        verify(guestServiceImpl).modifyGuest(guestId, guest);

	        // Verify that the correct message is returned
	        assertEquals("Guest Updated with the guestId " + guestId, result);
	    }
	}

