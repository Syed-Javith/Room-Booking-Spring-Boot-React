package com.hotel_booking.server.Services;

import com.hotel_booking.server.Exceptions.MaxBookingReachedException;
import com.hotel_booking.server.Exceptions.RoomAlreadyBookedException;
import com.hotel_booking.server.Exceptions.RoomNotFoundException;
import com.hotel_booking.server.Exceptions.UnauthorizedAccessException;
import com.hotel_booking.server.Models.Enums.EmailBody;
import com.hotel_booking.server.Models.Enums.EmailSubject;
import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.Types.EmailDetails;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepo roomRepo;
    private final BookingService bookingService;
    private final EmailService emailService;

    public List<Room> getAllUnBookedRooms() {
        return roomRepo.getAllRooms();
    }

    public Room getRoomById(int id) {
        return roomRepo.findById(id).orElseThrow(
                () -> new RoomNotFoundException("Room not found")
        );
    }

    public Room addRoom(Room room) {
        System.out.println(room);
        roomRepo.save(room);
        return room;
    }

    public Room updateRoom(Room room) {
        return roomRepo.save(room);
    }

    public void deleteRoomById(int id) {
        roomRepo.deleteById(id);
    }

    public void bookRoom(int roomId, User user) {
        Room room = roomRepo.findById(roomId).orElseThrow(
                () -> new RoomNotFoundException("Room not found")
        );
        if(user.getRoomId()!=null) throw new MaxBookingReachedException("You have booked maximum number of bookings");
        if(!room.isAvailable()) throw new RoomAlreadyBookedException("Room Already Booked");
        bookingService.makeBooking(user, room);
        room.setUser(user);
        room.setAvailable(false);
        roomRepo.save(room);
        emailService.sendSimpleMail(
                new EmailDetails(
                        user.getUsername(),
                        EmailBody.BOOKING_CONFIRMATION
                                .getMessage()
                                .replace("[Guest Name]",user.getUsername()),
                        EmailSubject.BOOKING_CONFIRMATION.getMessage()
                        ,null
                )
        );
    }

    public void cancelRoom(int roomId, User user) {
        cancelUtil(roomId, user);
        emailService.sendSimpleMail(
                new EmailDetails(
                        user.getUsername(),
                        EmailBody.BOOKING_CANCEL
                                .getMessage()
                                .replace("[Guest Name]",user.getUsername()),
                        EmailSubject.BOOKING_CANCEL.getMessage()
                        ,null
                )
        );
    }

    public void vacateRoom(int roomId, User user) {
        cancelUtil(roomId, user);
        emailService.sendSimpleMail(
                new EmailDetails(
                        user.getUsername(),
                        EmailBody.BOOKING_VACATED
                                .getMessage()
                                .replace("[Guest Name]",user.getUsername()),
                        EmailSubject.BOOKING_VACATED.getMessage()
                        ,null
                )
        );
    }

    private void cancelUtil(int roomId, User user) {
        Room room = roomRepo.findById(roomId).orElseThrow(
                () -> new RoomNotFoundException("Room not found")
        );
        if (room.getUser() == null || room.getUser().getId() != user.getId()) {
            throw new UnauthorizedAccessException("You are not authorized to cancel this room booking");
        }
        bookingService.cancelBooking(user, room);
        room.setAvailable(true);
        room.setUser(null);
        roomRepo.save(room);
    }
}
