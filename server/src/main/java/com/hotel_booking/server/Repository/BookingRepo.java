package com.hotel_booking.server.Repository;

import com.hotel_booking.server.Models.Booking;
import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking AS b WHERE b.user=:user AND b.room=:room")
    Booking getBookingOfUserForTheRoom(User user, Room room);

    @Query("SELECT b FROM Booking AS b WHERE EXTRACT(MONTH FROM b.bookingDate) =:month")
    List<Booking> getAllBookingsOfTheMonth(Integer month);
}
