package com.hotel_booking.server.Controllers;

import com.hotel_booking.server.Exceptions.UnauthorizedAccessException;
import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.Types.CommonResponse;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Repository.UserRepo;
import com.hotel_booking.server.Services.RoomService;
import com.hotel_booking.server.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AdminController {

    private final RoomService roomService;
    private final UserRepo userRepo;
    private final UserService userService;

    public AdminController(RoomService roomService, UserRepo userRepo, UserService userService) {
        this.roomService = roomService;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String index(){
        return "Hello admin";
    }

    @GetMapping("/admin/room/{roomId}")
    public Room getRoom(@PathVariable int roomId){
        return roomService.getRoomById(roomId);
    }

    @PostMapping("/admin/room")
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @PutMapping("/admin/room")
    public Room updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/admin/room/{roomId}")
    public void deleteRoom(@PathVariable Integer roomId) {
        roomService.deleteRoomById(roomId);
    }

    @PutMapping("/admin/room/{roomId}/suspend")
    public void suspendRoom(@PathVariable Integer roomId) {
        roomService.suspendRoom(roomId);
    }

    @PutMapping("/admin/user")
    public CommonResponse updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UnauthorizedAccessException("User not found")
        );
    }
}