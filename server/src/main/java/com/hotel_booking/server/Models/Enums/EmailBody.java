package com.hotel_booking.server.Models.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EmailBody {
    PASSWORD_RESET("""
        Dear [Guest Name],

        We received a request to reset your password. 
        Please use the following link to set a new password. 
        If you did not request this, please ignore this email.

        Best Regards,
        Hotel Booking Team
        """),

    BOOKING_CONFIRMATION("""
        Dear [Guest Name],

        Your booking has been successfully confirmed!
        We look forward to welcoming you.

        Best Regards,
        Hotel Booking Team
        """),

    BOOKING_CANCEL("""
        Dear [Guest Name],

        We are sorry to inform you that your booking has been canceled as per your request. 
        If you have any questions or need further assistance, please contact us.

        Best Regards,
        Hotel Booking Team
        """),

    BOOKING_VACATED("""
        Dear [Guest Name],

        We hope you had a pleasant stay at [Hotel Name]. 
        This is to confirm that you have successfully checked out. 
        If you have any feedback or need assistance with anything, please feel free to reach out.

        Thank you for choosing our services.

        Best Regards,
        Hotel Booking Team
        """),

    REGISTER_CONFIRMATION("""
        Dear [Guest Name],

        Thank you for registering with our hotel booking platform. 
        Your account has been successfully created. 
        You can now log in and start booking your favorite hotels.

        Best Regards,
        Hotel Booking Team
        """);

    private String message;
}