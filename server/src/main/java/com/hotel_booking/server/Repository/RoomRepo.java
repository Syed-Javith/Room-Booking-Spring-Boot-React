package com.hotel_booking.server.Repository;

import com.hotel_booking.server.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE r.isAvailable=true")
    List<Room> getAllRooms();
}
