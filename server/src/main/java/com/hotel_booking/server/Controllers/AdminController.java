package com.hotel_booking.server.Controllers;

import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Services.RoomService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AdminController {

    private final RoomService roomService;

    public AdminController(RoomService roomService) {
        this.roomService = roomService;
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
}