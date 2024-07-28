package com.hotel_booking.server.Models.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EmailSubject {
    PASSWORD_RESET("Password Reset"),
    BOOKING_CONFIRMATION("Booking Confirmation"),
    BOOKING_CANCEL("Booking Cancel"),
    BOOKING_VACATED("Booking Vacated"),
    REGISTER_CONFIRMATION("Register Confirmation");
    private String message;
}