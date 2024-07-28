package com.hotel_booking.server.Exceptions;

public class MaxBookingReachedException extends RuntimeException{
    public MaxBookingReachedException(String errorMessage){
        super(errorMessage);
    }
}
