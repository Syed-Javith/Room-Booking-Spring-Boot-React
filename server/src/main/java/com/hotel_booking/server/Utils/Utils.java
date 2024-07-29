package com.hotel_booking.server.Utils;

import com.hotel_booking.server.Models.Booking;
import com.hotel_booking.server.Models.Review;
import com.hotel_booking.server.Models.Types.BookingDto;
import com.hotel_booking.server.Models.Types.ReviewDto;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static String replaceUserName(String body,String userName) {
        return body.replace("[Guest Name]",userName);
    }

    public static List<BookingDto> getBookingDtoList(List<Booking> bookings) {
        return bookings.stream()
                .map(booking -> new BookingDto(booking.getId(), booking.getBookingDate(), booking.getVacateDate(), booking.getAmount()))
                .collect(Collectors.toList());
    }

    public static List<ReviewDto> getReviewDtoList(List<Review> reviews) {
        return reviews.stream().map(
                r -> new ReviewDto(r.getId(),r.getReview(),r.getRating())
        ).collect(Collectors.toList());
    }
}
