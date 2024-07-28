package com.hotel_booking.server.Controllers;


import com.hotel_booking.server.Exceptions.UnauthorizedAccessException;
import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.Types.ProfileResponse;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Repository.UserRepo;
import com.hotel_booking.server.Services.RoomService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RoomController {
    private final RoomService roomService;
    private final UserRepo userRepo;

    public RoomController(RoomService roomService, UserRepo userRepo) {
        this.roomService = roomService;
        this.userRepo = userRepo;
    }

    @GetMapping("/room")
    public List<Room> rooms() {
        return roomService.getAllUnBookedRooms();
    }

    @GetMapping("/room/{roomId}")
    public Room room(@PathVariable int roomId) {
        return roomService.getRoomById(roomId);
    }

    @GetMapping("/room/my")
    public ProfileResponse myRooms() {
        User user = getCurrentUser();
        Integer roomId = user.getRoomId();
        Room room = roomService.getRoomById(roomId);
        return new ProfileResponse(user,room);
    }

    @PostMapping("/room/{roomId}")
    public void bookRoom(@PathVariable Integer roomId) {
        User user = getCurrentUser();
        if (user != null) {
            roomService.bookRoom(roomId, user);
        }
    }

    @PutMapping("/room/{roomId}/cancel")
    public void cancelRoom(@PathVariable Integer roomId) {
        User user = getCurrentUser();
        roomService.cancelRoom(roomId, user);
    }

    @PutMapping("/room/{roomId}/vacate")
    public void vacateRoom(@PathVariable Integer roomId) {
        User user = getCurrentUser();
        roomService.vacateRoom(roomId, user);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UnauthorizedAccessException("User not found")
        );
    }
}
