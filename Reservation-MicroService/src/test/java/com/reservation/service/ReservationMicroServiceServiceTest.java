package com.reservation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;

//import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.razorpay.RazorpayClient;
import com.reservation.service.client.GuestClient;
import com.reservation.service.client.RoomClient;
import com.reservation.service.exception.InvalidReservationIdException;
import com.reservation.service.models.Guest;
import com.reservation.service.models.Reservation;
import com.reservation.service.models.Room;
import com.reservation.service.models.TransactionDetails;
import com.reservation.service.repository.ReservationRepository;
import com.reservation.service.services.ReservationServiceImpl;

@SpringBootTest
public class ReservationMicroServiceServiceTest {

	@Mock
    private RoomClient roomClient;
 
    @Mock
    private GuestClient guestClient;
 
    @Mock
    private ReservationRepository reservationRepo;
 
    @InjectMocks
    private ReservationServiceImpl reservationService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    void testAddReservation_RoomAndGuestAvailable() {
        // Mock room
        Room room = new Room();
        room.setRoomId("2");
        room.setRoomPrice(7000);
        room.setRoomAvail(true);
 
        // Mock guest
        Guest guest = new Guest();
        guest.setGuestId("3");
 
        when(roomClient.getRoomById("2")).thenReturn(Optional.of(room));
        when(guestClient.getGuestById("3")).thenReturn(Optional.of(guest));
 
        Reservation reservation = new Reservation();
        reservation.setRoomId("2");
        reservation.setGuestId("3");
        reservation.setCheckInDate("2022-03-01");
        reservation.setCheckOutDate("2022-03-02");
 
        String result = reservationService.addReservation(reservation);
 
        assertEquals("Room Id 2 reserved for the Guest Id Number 3", result);
 
        verify(roomClient, times(1)).getRoomById("2");
        verify(guestClient, times(1)).getGuestById("3");
        verify(reservationRepo, times(1)).save(reservation);
        verify(roomClient, times(1)).modifyRoomById(room, "2");
    }
//    }eservation1.setRoomId("101");         reservation1.setGuestId("201");         Reservation reservation2 = new Reservation();         reservation2.setReservationId("2");         reservation2.setRoomId("102");         reservation2.setGuestId("202");         Guest guest1 = new Guest();         guest1.setGuestId("201");         guest1.setGuestName("John Doe");         Guest guest2 = new Guest();         guest2.setGuestId("202");         guest2.setGuestName("Jane Smith");         when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));         when(guestClient.getGuestById("201")).thenReturn(Optional.of(guest1));         when(guestClient.getGuestById("202")).thenReturn(Optional.of(guest2));         // Test        List<Reservation> result = reservationService.getAllReservations();         // Verify        assertEquals(2, result.size());         assertEquals("John Doe", result.get(0).getGuest().getGuestName());         assertEquals("Jane Smith", result.get(1).getGuest().getGuestName());             // Mock DataReservation reservation1 = new Reservation();         reservation1.setReservationId("1");         reservation1.setRoomId("101");         reservation1.setGuestId("201");         Reservation reservation2 = new Reservation();         reservation2.setReservationId("2");         reservation2.setRoomId("102");         reservation2.setGuestId("202");         Guest guest1 = new Guest();         guest1.setGuestId("201");         guest1.setGuestName("John Doe");         Guest guest2 = new Guest();         guest2.setGuestId("202");         guest2.setGuestName("Jane Smith");         when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));         when(guestClient.getGuestById("201")).thenReturn(Optional.of(guest1));         when(guestClient.getGuestById("202")).thenReturn(Optional.of(guest2));         // Test        List<Reservation> result = reservationService.getAllReservations();         // Verify        assertEquals(2, result.size());         assertEquals("John Doe", result.get(0).getGuest().getGuestName());         assertEquals("Jane Smith", result.get(1).getGuest().getGuestName());     }
	@Test
    void testGetAllReservations() {
        Reservation reservation1 = new Reservation();
        reservation1.setReservationId("1");
        reservation1.setRoomId("101");
        reservation1.setGuestId("201");
 
        Reservation reservation2 = new Reservation();
        reservation2.setReservationId("2");
        reservation2.setRoomId("102");
        reservation2.setGuestId("202");
 
        Guest guest1 = new Guest();
        guest1.setGuestId("201");
        guest1.setGuestName("John Doe");
 
        Guest guest2 = new Guest();
        guest2.setGuestId("202");
        guest2.setGuestName("Jane Smith");
 
        when(reservationRepo.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));
        when(guestClient.getGuestById("201")).thenReturn(Optional.of(guest1));
        when(guestClient.getGuestById("202")).thenReturn(Optional.of(guest2));
 
        List<Reservation> result = reservationService.gettAllReservations();
 
      
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getGuest().getGuestName());
        assertEquals("Jane Smith", result.get(1).getGuest().getGuestName());
    }

	@Test
    void testModifyReservationById_ValidReservation() throws InvalidReservationIdException {
        Reservation existingReservation = new Reservation();
        existingReservation.setReservationId("1");
        existingReservation.setRoomId("101");
        existingReservation.setCheckInDate("2024-04-01");
        existingReservation.setCheckOutDate("2024-04-03");
 
        Reservation updatedReservation = new Reservation();
        updatedReservation.setReservationId("1");
        updatedReservation.setRoomId("102");
        updatedReservation.setCheckInDate("2024-04-01");
        updatedReservation.setCheckOutDate("2024-04-04");
 
        Room existingRoom = new Room();
        existingRoom.setRoomId("101");
        existingRoom.setRoomAvail(false);
 
        Room updatedRoom = new Room();
        updatedRoom.setRoomId("102");
        updatedRoom.setRoomAvail(true);
 
        when(reservationRepo.findById("1")).thenReturn(Optional.of(existingReservation));
        when(roomClient.getRoomById("101")).thenReturn(Optional.of(existingRoom));
        when(roomClient.getRoomById("102")).thenReturn(Optional.of(updatedRoom));
 
      
        reservationService.modifyReservationById(updatedReservation, "1");
 
        
        assertEquals("102", existingReservation.getRoomId());
        verify(roomClient, times(1)).modifyRoomById(updatedRoom, "102");
        verify(roomClient, times(1)).modifyRoomById(existingRoom, "101");
        verify(reservationRepo, times(1)).save(existingReservation);
    }



@Test

    void testDeleteReservationById_ValidReservation() throws InvalidReservationIdException {

        Reservation reservation = new Reservation();

        reservation.setReservationId("1");

        reservation.setRoomId("101");
 
        Room room = new Room();

        room.setRoomId("101");

        room.setRoomAvail(false);
 
        when(reservationRepo.findById("1")).thenReturn(Optional.of(reservation));

        when(roomClient.getRoomById("101")).thenReturn(Optional.of(room));
 
        

        String result = reservationService.deleteReservationById("1");
 
       

        assertEquals("Reservation Deleted with Id 1", result);

        verify(roomClient, times(1)).modifyRoomById(room, "101");

        verify(reservationRepo, times(1)).deleteById("1");

    }
@Test
void testGetReservationById_ValidReservation() throws InvalidReservationIdException {
    Reservation reservation = new Reservation();
    reservation.setReservationId("1");
    reservation.setRoomId("101");
    reservation.setGuestId("201");

    Guest guest = new Guest();
    guest.setGuestId("201");
    guest.setGuestName("John Doe");

    when(reservationRepo.findById("1")).thenReturn(Optional.of(reservation));
    when(guestClient.getGuestById("201")).thenReturn(Optional.of(guest));

   
    Optional<Reservation> result = reservationService.getReservationById("1");

   
    assertEquals("1", result.get().getReservationId());
    assertEquals("201", result.get().getGuest().getGuestId());
    assertEquals("John Doe", result.get().getGuest().getGuestName());
}

}


        
