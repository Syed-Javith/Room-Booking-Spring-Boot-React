package com.hotel_booking.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel_booking.server.Models.Enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private int roomNumber;
    private int capacity;
    private int price;
    private String description;
    private int floor;
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @OneToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonProperty("userId")
    private User user;

    @JsonProperty("userId")
    public Integer getUserId() {
        return user != null ? user.getId() : null;
    }
}
