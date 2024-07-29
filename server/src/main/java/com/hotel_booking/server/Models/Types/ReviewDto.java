package com.hotel_booking.server.Models.Types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDto {
    private int id;
    private String review;
    private int rating;
}