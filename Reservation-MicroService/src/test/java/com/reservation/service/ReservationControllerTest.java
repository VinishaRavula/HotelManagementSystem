package com.reservation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reservation.service.controller.ReservationController;
import com.reservation.service.exception.InvalidReservationIdException;
import com.reservation.service.models.Guest;
import com.reservation.service.models.Reservation;
import com.reservation.service.models.Room;
import com.reservation.service.models.TransactionDetails;
import com.reservation.service.services.ReservationService;

@SpringBootTest
public class ReservationControllerTest {
	@Autowired
    private ReservationController reservationController;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void testAddReservation_Success() {
        // Arrange
        Reservation reservation = new Reservation();
        reservation.setRoomId("2");
        reservation.setGuestId("3");
        reservation.setCheckInDate("2024-03-01");
        reservation.setCheckOutDate("2024-03-02");
        reservation.setNumOfGuest(3);
        reservation.setTotalPrice(7000);

        when(reservationService.addReservation(reservation)).thenReturn("Room Id 2 reserved for the Guest Id Number 3");

        // Act
        String result = reservationController.addReservation(reservation);

        // Assert
        assertEquals("Room Id 2 reserved for the Guest Id Number 3", result);
    }
    @Test
    public void testGetAllReservations_ReturnsReservations() {
        // Arrange
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        when(reservationService.gettAllReservations()).thenReturn(reservations);

        // Act
        ResponseEntity<List<Reservation>> responseEntity = reservationController.getAllReservations();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reservations, responseEntity.getBody());
    }

    @Test
    public void testGetAllReservations_NoReservations() {
        // Arrange
        when(reservationService.gettAllReservations()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<Reservation>> responseEntity = reservationController.getAllReservations();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }
    @Test
    public void testGetReservationById_Exists() throws InvalidReservationIdException {
        // Arrange
        String reservationId = "123";
        Reservation reservation = new Reservation();
        when(reservationService.getReservationById(reservationId)).thenReturn(Optional.of(reservation));

        // Act
        ResponseEntity<Optional<Reservation>> responseEntity = reservationController.getReservationById(reservationId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reservation, responseEntity.getBody().get());
    }

    @Test
    public void testGetReservationById_NotExists() throws InvalidReservationIdException {
        // Arrange
        String reservationId = "123";
        when(reservationService.getReservationById(reservationId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Optional<Reservation>> responseEntity = reservationController.getReservationById(reservationId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(Optional.empty(), responseEntity.getBody());
    }

    @Test
    public void testGetReservationById_InvalidId() throws InvalidReservationIdException {
        // Arrange
        String reservationId = "invalidId";
        when(reservationService.getReservationById(reservationId)).thenThrow(new InvalidReservationIdException());

        // Act
        ResponseEntity<Optional<Reservation>> responseEntity = reservationController.getReservationById(reservationId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(Optional.empty(), responseEntity.getBody());
    }
    @Test
    public void testModifyReservationById_Success() throws InvalidReservationIdException {
        // Arrange
        String reservationId = "123";
        Reservation reservation = new Reservation();
        doNothing().when(reservationService).modifyReservationById(reservation, reservationId);

        // Act
        String result = reservationController.modifyReservationById(reservationId, reservation);

        // Assert
        assertEquals("Reservation modified for the Reservation Id " + reservationId, result);
    }

    @Test
    public void testModifyReservationById_InvalidId() throws InvalidReservationIdException {
        // Arrange
        String reservationId = "invalidId";
        Reservation reservation = new Reservation();
        doThrow(new InvalidReservationIdException()).when(reservationService).modifyReservationById(reservation, reservationId);

        // Act
        String result = reservationController.modifyReservationById(reservationId, reservation);

        // Assert
        assertEquals("Invaild Reservation Id", result);
    }
    @Test
    public void testDeleteReservationById_Success() throws InvalidReservationIdException {
        // Arrange
        String reservationId = "123";
        String expectedMessage = "Reservation deleted for the Reservation Id " + reservationId;
        doReturn(expectedMessage).when(reservationService).deleteReservationById(reservationId);

        // Act
        ResponseEntity<String> responseEntity = reservationController.deleteReservationById(reservationId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedMessage, responseEntity.getBody());
    }

    @Test
    public void testDeleteReservationById_InvalidId() throws InvalidReservationIdException {
        // Arrange
        String reservationId = "invalidId";
        doThrow(new InvalidReservationIdException()).when(reservationService).deleteReservationById(reservationId);

        // Act
        ResponseEntity<String> responseEntity = reservationController.deleteReservationById(reservationId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    public void testCreateTransaction_Success() {
        // Arrange
        double amount = 100.0;
        TransactionDetails expectedTransaction = new TransactionDetails("orderId", "USD", 100, "Success");
        doReturn(expectedTransaction).when(reservationService).createTransaction(amount);

        // Act
        TransactionDetails result = reservationController.createTransaction(amount);

        // Assert
        assertEquals(expectedTransaction, result);
    }
    @Test
    public void testConstructorAndGetters4() {
        // Arrange
        String guestId = "123";
        long guestContact = 1234567890L;
        String guestName = "John Doe";
        String guestEmail = "john.doe@example.com";
        String guestGender = "Male";
        String guestAddress = "123 Main St";

        // Act
        Guest guest = new Guest(guestId, guestContact, guestName, guestEmail, guestGender, guestAddress);

        // Assert
        assertEquals(guestId, guest.getGuestId());
        assertEquals(guestContact, guest.getGuestContact());
        assertEquals(guestName, guest.getGuestName());
        assertEquals(guestEmail, guest.getGuestEmail());
        assertEquals(guestGender, guest.getGuestGender());
        assertEquals(guestAddress, guest.getGuestAddress());
    }

    @Test
    public void testSetters() {
        // Arrange
        Guest guest = new Guest();

        // Act
        String guestId = "123";
        long guestContact = 1234567890L;
        String guestName = "John Doe";
        String guestEmail = "john.doe@example.com";
        String guestGender = "Male";
        String guestAddress = "123 Main St";

        guest.setGuestId(guestId);
        guest.setGuestContact(guestContact);
        guest.setGuestName(guestName);
        guest.setGuestEmail(guestEmail);
        guest.setGuestGender(guestGender);
        guest.setGuestAddress(guestAddress);

        // Assert
        assertEquals(guestId, guest.getGuestId());
        assertEquals(guestContact, guest.getGuestContact());
        assertEquals(guestName, guest.getGuestName());
        assertEquals(guestEmail, guest.getGuestEmail());
        assertEquals(guestGender, guest.getGuestGender());
        assertEquals(guestAddress, guest.getGuestAddress());
    }
    @Test
    public void testConstructorAndGetters1() {
        // Arrange
        String reservationId = "123";
        String roomId = "456";
        String guestId = "789";
        String checkInDate = "2024-04-01";
        String checkOutDate = "2024-04-05";
        int numOfGuest = 2;
        double totalPrice = 500.00;
        Guest guest = new Guest("guestId", 1234567890L, "John Doe", "john.doe@example.com", "Male", "123 Main St");

        // Act
        Reservation reservation = new Reservation(reservationId, roomId, guestId, checkInDate, checkOutDate, numOfGuest, totalPrice, guest);

        // Assert
        assertEquals(reservationId, reservation.getReservationId());
        assertEquals(roomId, reservation.getRoomId());
        assertEquals(guestId, reservation.getGuestId());
        assertEquals(checkInDate, reservation.getCheckInDate());
        assertEquals(checkOutDate, reservation.getCheckOutDate());
        assertEquals(numOfGuest, reservation.getNumOfGuest());
        assertEquals(totalPrice, reservation.getTotalPrice());
        assertEquals(guest, reservation.getGuest());
    }
    @Test
    public void testConstructorAndGetters2() {
        // Arrange
        String roomId = "101";
        String roomType = "Standard";
        boolean roomAvail = true;
        double roomPrice = 100.00;

        // Act
        Room room = new Room(roomId, roomType, roomAvail, roomPrice);

        // Assert
        assertEquals(roomId, room.getRoomId());
        assertEquals(roomType, room.getRoomType());
        assertEquals(roomAvail, room.isRoomAvail());
        assertEquals(roomPrice, room.getRoomPrice());
    }

    @Test
    public void testSetters1() {
        // Arrange
        Room room = new Room();

        // Act
        String roomId = "102";
        String roomType = "Deluxe";
        boolean roomAvail = false;
        double roomPrice = 150.00;

        room.setRoomId(roomId);
        room.setRoomType(roomType);
        room.setRoomAvail(roomAvail);
        room.setRoomPrice(roomPrice);

        // Assert
        assertEquals(roomId, room.getRoomId());
        assertEquals(roomType, room.getRoomType());
        assertEquals(roomAvail, room.isRoomAvail());
        assertEquals(roomPrice, room.getRoomPrice());
    }
    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String orderId = "12345";
        String currency = "USD";
        Integer amount = 100;
        String status = "Success";

        // Act
        TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, status);

        // Assert
        assertEquals(orderId, transactionDetails.getOrderId());
        assertEquals(currency, transactionDetails.getCurrency());
        assertEquals(amount, transactionDetails.getAmount());
        assertEquals(status, transactionDetails.getStatus());
    }

    @Test
    public void testSetters3() {
        // Arrange
        TransactionDetails transactionDetails = new TransactionDetails();

        // Act
        String orderId = "54321";
        String currency = "EUR";
        Integer amount = 200;
        String status = "Failed";

        transactionDetails.setOrderId(orderId);
        transactionDetails.setCurrency(currency);
        transactionDetails.setAmount(amount);
        transactionDetails.setStatus(status);

        // Assert
        assertEquals(orderId, transactionDetails.getOrderId());
        assertEquals(currency, transactionDetails.getCurrency());
        assertEquals(amount, transactionDetails.getAmount());
        assertEquals(status, transactionDetails.getStatus());
    }
    
}
