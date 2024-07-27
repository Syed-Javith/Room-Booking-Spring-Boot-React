package com.hotel_booking.server.Models.Types;

import com.hotel_booking.server.Models.Room;
import com.hotel_booking.server.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    User user;
    Room room;
}
