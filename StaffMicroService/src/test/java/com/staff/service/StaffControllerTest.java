package com.staff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.staff.service.controller.StaffController;
import com.staff.service.exception.StaffNotFoundException;
import com.staff.service.model.Staff;
import com.staff.service.service.StaffService;
import com.staff.service.service.StaffServiceImpl;
@SpringBootTest
public class StaffControllerTest {
	@Autowired
StaffController staffController;
	@Mock
    private StaffServiceImpl staffService;
 

//	@Test
//    void testGetAllStaffs_Success() {
//        // Arrange
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//        List<Staff> expectedStaffList = Arrays.asList(
//            new Staff("1","vandana","siddipet","cleaner",3000,20,"vandanaa@gmail.com"),
//            new Staff("2","qwer","kolkata","cleaner",3000,20,"qwer@gmail.com"),
//            new Staff("3","akhila","maharshtra","sweaper",4000,19,"akhilaa@gmail.com")
//            //new Staff("2", "Jane Doe", "Address 2", "Role 2", 2000.0, 35, "jane@example.com")
//           // new Staff("1", "John Doe", "Address 1", "Role 1", 1000.0, 30, "john@example.com"),
//            //new Staff("2", "Jane Doe", "Address 2", "Role 2", 2000.0, 35, "jane@example.com")
//        );
//        when(staffService.getAllStaffs()).thenReturn(expectedStaffList); // Mock behavior of staffService.getAllStaffs
//
//       // StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//
//        // Act
//        List<Staff> actualStaffList = staffController.getAllStaffs();
//
//        // Assert
//        assertEquals(expectedStaffList.size(), actualStaffList.size()); // Check if the size of the returned list matches the expected size
//        for (int i = 0; i < expectedStaffList.size(); i++) {
//            Staff expectedStaff = expectedStaffList.get(i);
//            Staff actualStaff = actualStaffList.get(i);
//            assertEquals(expectedStaff.getStaffId(), actualStaff.getStaffId()); // Check if the staff IDs match
//            assertEquals(expectedStaff.getStaffName(), actualStaff.getStaffName()); // Check if the staff names match
//            // Add more assertions for other staff attributes if needed
//        }
//	}
//    
//
//	@Test
//    void testGetStaffById_ExistingId() throws StaffNotFoundException {
//        // Mock behavior
//        String existingStaffId = "1";
//        Staff expectedStaff = new Staff("1","vandana","siddipet","cleaner",3000,20,"vandanaa@gmail.com");
//        Staff expectedStaff1 = new Staff("2","qwer","kolkata","cleaner",3000,20,"qwer@gmail.com");
//        Staff expecyedStaff3 = new Staff("3","akhila","maharshtra","sweaper",4000,19,"akhilaa@gmail.com");
//        when(staffService.getStaffById(existingStaffId)).thenReturn(Optional.of(expectedStaff1));
// 
//        // Call the controller method
//        Optional<Staff> result = staffController.getStaffById(existingStaffId);
// 
//        // Assertions
//        assertEquals(expectedStaff1, result.orElseThrow(), "Returned staff should match the expected staff");
//    }
// 
//    @Test
//    void testGetStaffById_NonExistingId() throws StaffNotFoundException {
//        // Mock behavior for non-existing ID
//        String nonExistingStaffId = "20";
//        when(staffService.getStaffById(nonExistingStaffId)).thenThrow(new StaffNotFoundException());
// 
//        // Call the controller method
//        Optional<Staff> result = staffController.getStaffById(nonExistingStaffId);
// 
//        // Assertions
//        assertEquals(Optional.empty(), result, "Optional should be empty for non-existing staff ID");
//    }
// 
//    @Test
//    void testGetStaffByName_Success() throws StaffNotFoundException {
//        // Arrange
//        String staffName = "akhila";
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//        List<Staff> expectedStaffList = Arrays.asList(
//            new Staff("1","vandana","siddipet","cleaner",3000,20,"vandanaa@gmail.com"),
//            new Staff("2","qwer","kolkata","cleaner",3000,20,"qwer@gmail.com"),
//            new Staff("3","akhila","maharshtra","sweaper",4000,19,"akhilaa@gmail.com")
//        );
//        //StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//        
//        // Mock behavior of staffService.getStaffByName to return staff list
//        when(staffService.getStaffByName(staffName)).thenReturn(expectedStaffList);
//
//        // Act
//        ResponseEntity<List<Staff>> responseEntity = staffController.getStaffByName(staffName);
//
//        // Assert
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); // Check if status code is OK
//        assertEquals(expectedStaffList, responseEntity.getBody()); // Check if returned staff list matches the expected list
//    }
//
//    @Test
//    void testGetStaffByName_StaffNotFoundException() throws StaffNotFoundException {
//        // Arrange
//        String staffName = "iopl";
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//       // StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//        
//        // Mock behavior of staffService.getStaffByName to throw StaffNotFoundException
//        when(staffService.getStaffByName(staffName)).thenThrow(new StaffNotFoundException("Staff not found"));
//
//        // Act
//        ResponseEntity<List<Staff>> responseEntity = staffController.getStaffByName(staffName);
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode()); // Check if status code is NOT_FOUND
//        assertNull(responseEntity.getBody()); // Check if body is null, indicating staff not found
//    }
//    @Test
//    void testDeleteStaffById_Success() throws StaffNotFoundException {
//        // Arrange
//        String staffId = "1";
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//        //StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//        
//        // Act
//        String result = staffController.deleteStaffById(staffId);
//
//        // Assert
//        assertEquals("Staff Deleted with StaffId " + staffId, result); // Check if deletion message is correct
//        verify(staffService, times(1)).deleteStaffById(staffId); // Verify that staffService.deleteStaffById was called once with the correct staffId
//    }
//
//    @Test
//    void testDeleteStaffById_StaffNotFoundException() throws StaffNotFoundException {
//        // Arrange
//        String staffId = "30";
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//       // StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//        
//        // Mock behavior of staffService.deleteStaffById to throw StaffNotFoundException
//        doThrow(new StaffNotFoundException("Invalid Staff Id")).when(staffService).deleteStaffById(staffId);
//
//        // Act
//        String result = staffController.deleteStaffById(staffId);
//
//        // Assert
//        assertEquals("Invalid Staff Id", result); // Check if error message is correct
//        verify(staffService, times(1)).deleteStaffById(staffId); // Verify that staffService.deleteStaffById was called once with the correct staffId
//    }
//    @Test
//    void testAddStaff_Success() {
//        // Arrange
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//        //StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//        Staff staff = new Staff(); // Create a staff object
//
//        // Act
//        String result = staffController.addStaff(staff);
//
//        // Assert
//        assertEquals("Added Staff", result); // Check if the result is as expected
//    }
//
//    @Test
//    void testAddStaff_Exception() {
//        // Arrange
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//       // StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//        Staff staff = new Staff(); // Create a staff object
//        doThrow(new RuntimeException("Failed to add staff")).when(staffService).addStaff(staff); // Mock the service to throw an exception
//
//        // Act
//        String result = staffController.addStaff(staff);
//
//        // Assert
//        assertEquals("Failed to add staff", result); // Check if the result is as expected
//    }
//    @Test
//    void testModifyStaffById_Success() throws StaffNotFoundException {
//        // Arrange
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//       // StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//        String staffId = "123";
//        Staff staff = new Staff(); // Create a staff object
//
//        // Act
//        String result = staffController.modifyStaffById(staff, staffId);
//
//        // Assert
//        assertEquals("Staff Modified", result); // Check if the result is as expected
//        verify(staffService, times(1)).modifyStaffById(staffId, staff); // Verify that the service method is called once with the correct arguments
//    }
//
//    @Test
//    void testModifyStaffById_InvalidStaffId() throws StaffNotFoundException {
//        // Arrange
//        StaffService staffService = mock(StaffService.class); // Mock the StaffService
//        //StaffController staffController = new StaffController(staffService); // Create controller with the mocked service
//        String staffId = "invalidId";
//        Staff staff = new Staff(); // Create a staff object
//        doThrow(new StaffNotFoundException()).when(staffService).modifyStaffById(staffId, staff); // Mock the service to throw a StaffNotFoundException
//
//        // Act
//        String result = staffController.modifyStaffById(staff, staffId);
//
//        // Assert
//        assertEquals("Invalid Staff Id", result); // Check if the result is as expected
//    }
//    @Test
//    public void testGetStaffById_ReturnsExistingStaff() throws StaffNotFoundException {
//        // Arrange
//        String existingStaffId = "2";
//        Staff existingStaff = new Staff("2", "qwer","kolkata","cleaner",3000,20,"qwer@gmail.com");
//
//        // Mock behavior to return an existing staff when getStaffById is called with existing ID
//        when(staffService.getStaffById(existingStaffId)).thenReturn(Optional.of(existingStaff));
//
//        // Act
//        Optional<Staff> result = staffController.getStaffById(existingStaffId);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(existingStaff, result.get());
//    }
//    @Test
//    public void testDeleteStaffById_Success() throws StaffNotFoundException {
//        // Arrange
//        String staffId = "existingId";
//
//        // Act
//        String result = staffController.deleteStaffById(staffId);
//
//        // Assert
//        assertEquals("Staff Deleted with StaffId " + staffId, result);
//
//        // Verify that deleteStaffById method of the service is invoked with correct staffId
//        verify(staffService).deleteStaffById(staffId);
//    }
//}
//	@Test
//    public void testGetAllStaffs_ReturnsNonEmptyList() {
//        // Arrange
//        List<Staff> staffList = Arrays.asList(
//            //new Staff("1","vandana","siddipet","cleaner",3000,20,"vandanaa@gmail.com"),
//            new Staff("2","qwer","kolkata","cleaner",3000,20,"qwer@gmail.com"),
//            new Staff("3","akhila","maharshtra","sweaper",4000,19,"akhilaa@gmail.com")
//            // Add more Staff objects as needed
//        );
//        
//        // Mock behavior to return the staffList when getAllStaffs is called
//        when(staffService.getAllStaffs()).thenReturn(staffList);
//
//        // Act
//        List<Staff> result = staffController.getAllStaffs();
//
//        // Assert
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(staffList.size(), result.size());
//        assertTrue(result.containsAll(staffList));
//        verify(staffService).getAllStaffs();
//    } 
	@Test
    public void testGetAllStaffs_ReturnsNonEmptyList() {
        // Arrange
        List<Staff> expectedStaffList = Arrays.asList(
          //  new Staff("1", "vandana", "siddipet", "cleaner", 3000, 20, "vandanaa@gmail.com"),
            new Staff("2", "qwer", "kolkata", "cleaner", 3000, 20, "qwer@gmail.com"),
            new Staff("3", "akhila", "maharshtra", "sweaper", 4000, 19, "akhilaa@gmail.com")
            // Add more Staff objects as needed
        );
        
        // Mock behavior to return the expectedStaffList when getAllStaffs is called
        when(staffService.getAllStaffs()).thenReturn(expectedStaffList);

        // Act
        List<Staff> result = staffController.getAllStaffs();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(expectedStaffList.size(), result.size());
        assertTrue(result.containsAll(expectedStaffList));
        
        // Verify that getAllStaffs method of the service is invoked
        verify(staffService).getAllStaffs();
    }
	@Test
	public void testGetStaffById_ReturnsOptionalOfStaff() throws StaffNotFoundException {
	    // Arrange
	    String staffId = "2";
	    Staff expectedStaff = new Staff(staffId, "qwer", "kolkata", "cleaner", 3000, 20, "qwer@gmail.com");

	    // Mock behavior to return the expectedStaff when getStaffById is called with staffId
	    when(staffService.getStaffById(staffId)).thenReturn(Optional.of(expectedStaff));

	    // Act
	    Optional<Staff> result = staffController.getStaffById(staffId);

	    // Assert
	    assertNotNull(result);
	    assertTrue(result.isPresent());
	    assertEquals(expectedStaff, result.get()); // This line requires equals() to be properly overridden in Staff class

	   
	}
	@Test
    public void testGetStaffByName_ReturnsListOfStaff() throws StaffNotFoundException {
        // Arrange
        String staffName = "akhila";
        List<Staff> expectedStaffList = Arrays.asList(
            new Staff("2", "qwer", "kolkata", "cleaner", 3000, 20, "qwer@gmail.com"),
            new Staff("3", "akhila", "maharshtra", "sweaper", 4000, 19, "akhilaa@gmail.com")
            // Add more staff objects as needed
        );

        // Mock behavior to return the expectedStaffList when getStaffByName is called with staffName
        when(staffService.getStaffByName(staffName)).thenReturn(expectedStaffList);

        // Act
        ResponseEntity<List<Staff>> responseEntity = staffController.getStaffByName(staffName);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedStaffList, responseEntity.getBody());

        // Verify that getStaffByName method of the service is invoked with the correct staffName
        verify(staffService).getStaffByName(staffName);
    }

	@Test
    public void testModifyStaffById_ReturnsStaffModified() throws StaffNotFoundException {
        // Arrange
        String staffId = "2";
        Staff staff = new Staff("2", "qwer", "kolkata", "cleaner", 3000, 20, "qwer@gmail.com");

        // Mock behavior to do nothing when modifyStaffById is called
        doNothing().when(staffService).modifyStaffById(staffId, staff);

        // Act
        String result = staffController.modifyStaffById(staff, staffId);

        // Assert
        assertEquals("Staff Modified", result);

        // Verify that modifyStaffById method of the service is invoked with the correct staffId and staff object
        verify(staffService).modifyStaffById(staffId, staff);
    }
	@Test
    public void testAddStaff_ReturnsAddedStaff() throws StaffNotFoundException {
        // Arrange
        Staff staff = new Staff("3", "vani", "blr", "cleaner", 5000, 30, "vaniii@gmailcom");

        // Mock behavior to do nothing when addStaff is called
        doNothing().when(staffService).addStaff(staff);

        // Act
        String result = staffController.addStaff(staff);

        // Assert
        assertEquals("Added Staff", result);

        // Verify that addStaff method of the service is invoked with the correct staff object
        verify(staffService).addStaff(staff);
    }
	@Test
    public void testAddStaff_ExceptionCaught_ReturnsExceptionMessage() throws StaffNotFoundException {
        // Arrange
        Staff staff = new Staff("1", "John Doe", "New York", "Manager", 5000, 30, "john.doe@example.com");
        String errorMessage = "An error occurred while adding staff";

        // Mock behavior to throw an exception when addStaff is called
        doThrow(new RuntimeException(errorMessage)).when(staffService).addStaff(staff);

        // Act
        String result = staffController.addStaff(staff);

        // Assert
        assertEquals(errorMessage, result);
    }
	@Test
    public void testDeleteStaffById_StaffNotFoundExceptionThrown_ReturnsErrorMessage() throws StaffNotFoundException {
        // Arrange
        String staffId = "90000";
        String errorMessage = "Staff not found with ID: " + staffId;

        // Mock behavior to throw StaffNotFoundException when deleteStaffById is called
        doThrow(new StaffNotFoundException(errorMessage)).when(staffService).deleteStaffById(staffId);

        // Act
        String result = staffController.deleteStaffById(staffId);

        // Assert
        assertEquals(errorMessage, result);
    }
}
	


    

