package com.hotel_booking.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel_booking.server.Models.Enums.RoomType;
import com.hotel_booking.server.Models.Types.BookingDto;
import com.hotel_booking.server.Models.Types.ReviewDto;
import com.hotel_booking.server.Utils.Utils;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @JsonProperty("userId")
    private User user;

    @OneToMany(mappedBy = "room")
    @JsonProperty("bookings")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "room")
    @JsonProperty("reviews")
    @ToString.Exclude
    private List<Review> reviews;

    @JsonProperty("bookings")
    private List<BookingDto> bookingBookings() {
        return Utils.getBookingDtoList(bookings);
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        return user != null ? user.getId() : null;
    }

    @JsonProperty("reviews")
    public List<ReviewDto> getReviews() {
        return Utils.getReviewDtoList(reviews);
    }
}