package com.hotel_booking.server.Services;

import com.hotel_booking.server.Models.Booking;
import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Repository.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepo bookingRepo;

    public void makeBooking(User user, Room room) {
        Booking booking = new Booking(
                UUID.randomUUID(),
                user,
                room,
                LocalDateTime.now(),
                null,
                room.getPrice()
        );
        bookingRepo.save(booking);
    }

    public void cancelBooking(User user, Room room) {
        Booking booking = bookingRepo.getBookingOfUserForTheRoom(user,room);
        booking.setVacateDate(LocalDateTime.now());
        bookingRepo.save(booking);
    }
}
