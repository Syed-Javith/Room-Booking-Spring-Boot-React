package com.hotel_booking.server.Controllers;

import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.Types.CommonResponse;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Repository.UserRepo;
import com.hotel_booking.server.Services.RoomService;
import com.hotel_booking.server.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AdminController {

    private final RoomService roomService;
    private final UserService userService;

    public AdminController(RoomService roomService, UserRepo userRepo, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String index(){
        return "Hello admin";
    }

    @GetMapping("/admin/room")
    public List<Room> getRooms(){
        return roomService.getAllRooms();
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


    @GetMapping("/admin/user")
    public List<User> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/user/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @PostMapping("admin/user/")
    public CommonResponse addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/admin/user")
    public CommonResponse updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/admin/user/{userId}")
    public CommonResponse deleteUser(@PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }
}