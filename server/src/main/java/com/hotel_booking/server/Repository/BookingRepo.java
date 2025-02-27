package com.hotel_booking.server.Repository;

import com.hotel_booking.server.Models.Booking;
import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Book;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking AS b WHERE b.user.id=:userId AND b.room.roomNumber=:roomId ORDER BY b.bookingDate DESC LIMIT 1")
    Booking getBookingOfUserForTheRoom(Integer userId, Integer roomId);

    @Query("SELECT b FROM Booking AS b WHERE EXTRACT(MONTH FROM b.bookingDate) =:month")
    List<Booking> getAllBookingsOfTheMonth(Integer month);

    @Query("SELECT b,r FROM Booking AS b INNER JOIN Room AS r ON r.roomNumber=:bookigId")
    Booking getBookingDetails();
}
