package com.hotel_booking.server.Models.Types;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookingDto {
    private UUID id;
    private LocalDateTime bookingDate;
    private LocalDateTime vacateDate;
    private int amount;
}
