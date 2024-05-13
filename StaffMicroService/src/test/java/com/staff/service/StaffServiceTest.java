package com.staff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.staff.service.exception.StaffNotFoundException;
import com.staff.service.model.Staff;
import com.staff.service.repository.StaffRepository;
import com.staff.service.service.StaffServiceImpl;

@SpringBootTest
public class StaffServiceTest {
	@Mock
    private StaffRepository staffRepo;

    @InjectMocks
    private StaffServiceImpl staffService;

    @Test
    public void testGetAllStaffs_ReturnsListOfStaffs() {
        // Arrange
        List<Staff> expectedStaffList = new ArrayList<>();
        expectedStaffList.add(new Staff());
        expectedStaffList.add(new Staff());
        when(staffRepo.findAll()).thenReturn(expectedStaffList);

        // Act
        List<Staff> result = staffService.getAllStaffs();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetStaffById_ExistingStaff_ReturnsStaff() throws StaffNotFoundException {
        // Arrange
        String staffId = "1";
        Staff expectedStaff = new Staff();
        when(staffRepo.findById(staffId)).thenReturn(Optional.of(expectedStaff));

        // Act
        Optional<Staff> result = staffService.getStaffById(staffId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedStaff, result.get());
    }

    @Test
    public void testGetStaffById_NonExistingStaff_ReturnsEmptyOptional() throws StaffNotFoundException {
        // Arrange
        String staffId = "1";
        when(staffRepo.findById(staffId)).thenReturn(Optional.empty());

        // Act
        Optional<Staff> result = staffService.getStaffById(staffId);

        // Assert
        assertTrue(result.isEmpty());
    }
    @Test
    public void testModifyStaffById_ExistingStaff_ModifiesStaff() throws StaffNotFoundException {
        // Arrange
        String staffId = "1";
        Staff staffToUpdate = new Staff();
        staffToUpdate.setStaffName("New Name");
        staffToUpdate.setStaffAge(30);
        staffToUpdate.setStaffRole("New Role");
        staffToUpdate.setStaffAddress("New Address");
        staffToUpdate.setStaffSalary(50000);
        staffToUpdate.setStaffEmail("newemail@example.com");

        Staff existingStaff = new Staff();
        when(staffRepo.findById(staffId)).thenReturn(Optional.of(existingStaff));

        // Act
        staffService.modifyStaffById(staffId, staffToUpdate);

        // Assert
        assertEquals("New Name", existingStaff.getStaffName());
        assertEquals(30, existingStaff.getStaffAge());
        assertEquals("New Role", existingStaff.getStaffRole());
        assertEquals("New Address", existingStaff.getStaffAddress());
        assertEquals(50000, existingStaff.getStaffSalary());
        assertEquals("newemail@example.com", existingStaff.getStaffEmail());
        verify(staffRepo, times(1)).save(existingStaff);
    }

    @Test
    public void testDeleteStaffById_ExistingStaff_DeletesStaff() throws StaffNotFoundException {
        // Arrange
        String staffId = "1";

        // Act
        staffService.deleteStaffById(staffId);

        // Assert
        verify(staffRepo, times(1)).deleteById(staffId);
    }

    @Test
    public void testAddStaff_ValidStaff_AddsStaff() {
        // Arrange
        Staff staffToAdd = new Staff();
        staffToAdd.setStaffName("John Doe");
        staffToAdd.setStaffAddress("123 Main St");
        staffToAdd.setStaffEmail("john@example.com");

        // Act
        staffService.addStaff(staffToAdd);

        // Assert
        verify(staffRepo, times(1)).save(staffToAdd);
    }

    @Test
    public void testAddStaff_InvalidStaff_ThrowsIllegalArgumentException() {
        // Arrange
        Staff staffToAdd = new Staff(); // Empty staff object

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> staffService.addStaff(staffToAdd));
        verify(staffRepo, never()).save(any());
    }
    @Test
    public void testGetStaffByName_ValidName_ReturnsStaffList() throws StaffNotFoundException {
        // Arrange
        String validName = "John Doe";
        List<Staff> expectedStaffList = List.of(new Staff(), new Staff()); // Assuming there are staff objects with the given name
        when(staffRepo.findByStaffName(validName)).thenReturn(expectedStaffList);

        // Act
        List<Staff> result = staffService.getStaffByName(validName);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void testGetStaffByName_InvalidName_ThrowsStaffNotFoundException() {
        // Arrange
        String invalidName = "1234"; // Invalid name with digits
        when(staffRepo.findByStaffName(invalidName)).thenReturn(List.of()); // Assuming no staff objects with the given name

        // Act & Assert
        assertThrows(StaffNotFoundException.class, () -> staffService.getStaffByName(invalidName));
    }
    @Test
    public void testGetStaffId() {
        // Arrange
        String expectedStaffId = "S123";
        Staff staff = new Staff();
        staff.setStaffId(expectedStaffId);

        // Act
        String actualStaffId = staff.getStaffId();

        // Assert
        assertEquals(expectedStaffId, actualStaffId);
    }

    @Test
    public void testSetStaffId() {
        // Arrange
        String expectedStaffId = "S456";
        Staff staff = new Staff();

        // Act
        staff.setStaffId(expectedStaffId);
        String actualStaffId = staff.getStaffId();

        // Assert
        assertEquals(expectedStaffId, actualStaffId);
    }
    @Test
    public void testToString() {
        // Arrange
        Staff staff = new Staff();
        staff.setStaffId("S123");
        staff.setStaffName("John Doe");
        staff.setStaffAddress("123 Street");
        staff.setStaffRole("Manager");
        staff.setStaffSalary(50000.0);
        staff.setStaffAge(30);
        staff.setStaffEmail("john@example.com");

        // Act
        String expectedToStringResult = "Staff [staffId=S123, staffName=John Doe, staffAddress=123 Street, " +
                "staffRole=Manager, staffSalary=50000.0, staffAge=30, staffEmail=john@example.com]";
        String actualToStringResult = staff.toString();

        // Assert
        assertEquals(expectedToStringResult, actualToStringResult);
    }
    @Test
    public void testConstructor() {
        // Arrange
        String staffId = "S123";
        String staffName = "John Doe";
        String staffAddress = "123 Street";
        String staffRole = "Manager";
        double staffSalary = 50000.0;
        int staffAge = 30;
        String staffEmail = "john@example.com";

        // Act
        Staff staff = new Staff(staffId, staffName, staffAddress, staffRole, staffSalary, staffAge, staffEmail);

        // Assert
        assertNotNull(staff);
        assertEquals(staffId, staff.getStaffId());
        assertEquals(staffName, staff.getStaffName());
        assertEquals(staffAddress, staff.getStaffAddress());
        assertEquals(staffRole, staff.getStaffRole());
        assertEquals(staffSalary, staff.getStaffSalary());
        assertEquals(staffAge, staff.getStaffAge());
        assertEquals(staffEmail, staff.getStaffEmail());
    }
}
