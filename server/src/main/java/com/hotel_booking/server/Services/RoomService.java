package com.hotel_booking.server.Services;

import com.hotel_booking.server.Exceptions.RoomAlreadyBookedException;
import com.hotel_booking.server.Exceptions.RoomNotFoundException;
import com.hotel_booking.server.Exceptions.UnauthorizedAccessException;
import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepo roomRepo;

    public List<Room> getAllUnBookedRooms() {
        return roomRepo.getAllRooms();
    }

    public Room getRoomById(int id) {
        return roomRepo.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
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
        if(!room.isAvailable()) throw new RoomAlreadyBookedException("Room Already Booked");
        room.setUser(user);
        room.setAvailable(false);
        roomRepo.save(room);
    }

    public void cancelRoom(int roomId, User user) {
        Room room = roomRepo.findById(roomId).orElseThrow(
                () -> new RoomNotFoundException("Room not found")
        );
        if (room.getUser() == null || room.getUser().getId() != user.getId()) {
            throw new UnauthorizedAccessException("You are not authorized to cancel this room booking");
        }
        room.setAvailable(true);
        room.setUser(null);
        roomRepo.save(room);
    }
}
