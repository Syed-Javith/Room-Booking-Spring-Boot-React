package com.hotel_booking.server.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonProperty("roomId")
    private Room room;

    private LocalDateTime bookingDate;
    private LocalDateTime vacateDate;
    private int amount;

    @JsonProperty("roomId")
    private int getRoomId(){
        return room.getRoomNumber();
    }

    @JsonProperty("userId")
    private int getUserId(){
        return user.getId();
    }
}
