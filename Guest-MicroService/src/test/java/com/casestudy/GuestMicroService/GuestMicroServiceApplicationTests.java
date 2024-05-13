package com.casestudy.GuestMicroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import com.casestudy.GuestMicroService.exception.GuestNotFoundException;
import com.casestudy.GuestMicroService.models.Guest;
import com.casestudy.GuestMicroService.repository.GuestRepo;
import com.casestudy.GuestMicroService.service.GuestServiceImpl;


//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.casestudy.GuestMicroService.exception.GuestNotFoundException;
//import com.casestudy.GuestMicroService.models.Guest;
//import com.casestudy.GuestMicroService.repository.GuestRepo;
//import com.casestudy.GuestMicroService.service.GuestService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class GuestMicroserviceApplicationTests {
//
//	@MockBean
//	private GuestRepo guestRepo;
//
//	@Autowired
//	private GuestService guestSer;
//
//	@Test
//	public void getAllGuestsTest() {
//		when(guestRepo.findAll()).thenReturn(Stream
//				.of(new Guest("123456789",1111111111,"Deepak","sumit@gmail.com","Male","Kalayat"),
//					new Guest("123456788",1111111112,"Aman","dgarg@gmail.com","Male","Kaithal"))
//				.collect(Collectors.toList()));
//		
//		// Call the method under test
//		List<Guest> guests = guestSer.getAllGuests();
//		
//		// Assert the result
//		assertEquals(2, guests.size());
//	}
//
//	@Test
//	public void addGuestTest() {
//		Guest guest = new Guest("123456789", 1111111111, "Deepak", "sumit@gmail.com", "Male", "Kalayat");
//		guestSer.addGuest(guest);
//		verify(guestRepo, times(1)).save(guest);
//	}
//	
//	@Test
//	public void deleteGuestTest() throws GuestNotFoundException {
//		String guestId = "123456789";
// 		guestSer.deleteGuest(guestId);
//		verify(guestRepo, times(1)).deleteById(guestId);
//	}
//	
//	@Test 
//	public void getGuestByIdTest() throws GuestNotFoundException {
//		String guestId = "123456789";
// 		guestSer.getGuestById(guestId);
//		verify(guestRepo, times(1)).findById(guestId);
//	}
//
//	@Test
//	public void modifyGuestTest() throws GuestNotFoundException {
//	    // Create a guest object with updated values
//	    Guest updatedGuest = new Guest("123456789", 1111111111, "Updated Name", "updated_email@gmail.com", "Male", "Updated Address");
//
//	    // Mock the existing guest record
//	    Guest existingGuest = new Guest("123456789", 1111111111, "Deepak", "sumit@gmail.com", "Male", "Kalayat");
//	    Optional<Guest> existingGuestOptional = Optional.of(existingGuest);
//	    when(guestRepo.findById("123456789")).thenReturn(existingGuestOptional);
//
//	    // Call the modifyGuest method
//	    guestSer.modifyGuest("123456789", updatedGuest);
//
//	    // Verify that guestRepo.save() is called with the updated guest
//	    verify(guestRepo, times(1)).save(existingGuest);
//
//	    // Assert that the fields of the existing guest have been updated
//	    assertEquals("Updated Name", existingGuest.getGuestName());
//	    assertEquals("updated_email@gmail.com", existingGuest.getGuestEmail());
//	    assertEquals("Updated Address", existingGuest.getGuestAddress());
//	}
@ExtendWith(MockitoExtension.class)
public class GuestMicroServiceApplicationTests {

    @Mock
    private GuestRepo guestRepo;

    @InjectMocks
    private GuestServiceImpl guestService;
//    @Test
//    public void testGetAllGuests_ReturnsListOfGuests() {
//        // Arrange: Prepare a list of expected guests
//        List<Guest> expectedGuestList = new ArrayList<>();
//        expectedGuestList.add(new Guest("1",8712349829L,"rahul","rahul@gmail.com","Female","hyd"));
//        expectedGuestList.add(new Guest("2",8712349829L, "amruta", "amrutayankanchi@gmail.com", "Female", "tuni"));
//
//        // Act: Call the getAllGuests method
//        List<Guest> result = guestService.getAllGuests();
//
//        // Assert: Compare the result with the expected list
//        assertEquals(expectedGuestList, result);
//    }
    @Test
    public void testGetAllGuests_ReturnsListOfGuests() {
        // Arrange: Prepare a list of expected guests
        List<Guest> expectedGuestList = new ArrayList<>();
        expectedGuestList.add(new Guest("1", 1234567890L, "John Doe", "john@example.com", "Male", "123 Street"));
        expectedGuestList.add(new Guest("2", 9876543210L, "Jane Smith", "jane@example.com", "Female", "456 Avenue"));

        // Print the expected guest list for debugging
        System.out.println("Expected Guests: " + expectedGuestList);

        // Act: Call the getAllGuests method
        List<Guest> result = guestService.getAllGuests();

        // Print the actual result for debugging
        System.out.println("Actual Result: " + result);

        // Assert: Compare the result with the expected list of guests
        assertEquals(expectedGuestList, result);
    }
    @Test
    public void testAddGuest_ValidGuest_SavesGuest() {
        // Arrange
        Guest guest = new Guest("1", 1234567890L, "John Doe", "john@example.com", "Male", "123 Street");

        // Act
        guestService.addGuest(guest);

        // Assert
        verify(guestRepo, times(1)).save(guest);
    }

    @Test
    public void testModifyGuest_ValidGuestIdAndGuest_UpdatesGuest() throws GuestNotFoundException {
        // Arrange
        String guestId = "1";
        Guest updatedGuest = new Guest("1", 1234567890L, "John Doe", "john@example.com", "Male", "123 Street");

        // Act
        guestService.modifyGuest(guestId, updatedGuest);

        // Assert
        verify(guestRepo, times(1)).save(updatedGuest);
    }

    @Test
    public void testDeleteGuest_ValidGuestId_DeletesGuest() throws GuestNotFoundException {
        // Arrange
        String guestId = "1";

        // Act
        guestService.deleteGuest(guestId);

        // Assert
        verify(guestRepo, times(1)).deleteById(guestId);
    }

    @Test
    public void testGetGuestById_ValidGuestId_ReturnsGuestObject() throws GuestNotFoundException {
        // Arrange
        String guestId = "1";
        Guest expectedGuest = new Guest("1", 8712349829L, "rahul", "rahul@gmail.com", "Female", "hyd");
        when(guestRepo.findById(guestId)).thenReturn(Optional.of(expectedGuest));

        // Act
        Optional<Guest> result = guestService.getGuestById(guestId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedGuest, result.get());
    }

    @Test
    public void testGetGuestById_InvalidGuestId_ReturnsEmptyOptional() throws GuestNotFoundException {
        // Arrange
        String guestId = "1";
        when(guestRepo.findById(guestId)).thenReturn(Optional.empty());

        // Act
        Optional<Guest> result = guestService.getGuestById(guestId);

        // Assert
        assertFalse(result.isPresent());
    }
    @Test
    void testModifyGuest_ValidGuestId() throws GuestNotFoundException {
        Guest existingGuest = new Guest("1", 8712349829L, "rahul", "rahul@gmail.com", "Female", "hyd");
 
       
        Guest updatedGuest = new Guest("1", 8712349829L, "shiva", "rahul@gmail.com", "Female", "hyd");
 
        
        when(guestRepo.findById("1")).thenReturn(Optional.of(existingGuest));
 
      
        guestService.modifyGuest("1", updatedGuest);
 
      
        verify(guestRepo, times(1)).save(updatedGuest);
 
       
        assertEquals("rahul", existingGuest.getGuestName());
        assertEquals( 8712349829L, existingGuest.getGuestContact());
        assertEquals("rahul@gmail.com", existingGuest.getGuestEmail());
        assertEquals("Female", existingGuest.getGuestGender());
        assertEquals("hyd", existingGuest.getGuestAddress());
    }
 
    @Test
    void testModifyGuest_GuestNotFound() {
        when(guestRepo.findById("1")).thenReturn(Optional.empty());
 
        
        assertThrows(GuestNotFoundException.class, () -> {
            guestService.modifyGuest("1", new Guest());
        });
 
      
        verify(guestRepo, never()).save(any());
    }

}

