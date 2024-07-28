package com.hotel_booking.server.Models.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EmailContent {
    PASSWORD_RESET(
            "Password Reset",
            """
            Dear [Guest Name],
    
            We received a request to reset your password. 
            Please use the following link to set a new password. 
            If you did not request this, please ignore this email.
    
            Best Regards,
            Hotel Booking Team
            """
    ),
    BOOKING_CONFIRMATION(
            "Booking Confirmation",
            """
            Dear [Guest Name],
    
            Your booking has been successfully confirmed!
            We look forward to welcoming you.
    
            Best Regards,
            Hotel Booking Team
            """
    ),
    BOOKING_CANCEL(
            "Booking Cancel",
            """
            Dear [Guest Name],
    
            We are sorry to inform you that your booking has been canceled as per your request. 
            If you have any questions or need further assistance, please contact us.
    
            Best Regards,
            Hotel Booking Team
            """
    ),
    BOOKING_VACATED(
            "Booking Vacated",
            """
            Dear [Guest Name],
    
            We hope you had a pleasant stay at [Hotel Name]. 
            This is to confirm that you have successfully checked out. 
            If you have any feedback or need assistance with anything, please feel free to reach out.
    
            Thank you for choosing our services.
    
            Best Regards,
            Hotel Booking Team
            """
    ),
    REGISTER_CONFIRMATION(
            "Register Confirmation",
            """
            Dear [Guest Name],
    
            Thank you for registering with our hotel booking platform. 
            Your account has been successfully created. 
            You can now log in and start booking your favorite hotels.
    
            Best Regards,
            Hotel Booking Team
            """
    ),
    USER_DELETED_BY_ADMIN(
        "Account Deletion Notification",
                """
        Dear [Guest Name],

        We regret to inform you that your account has been deleted by an administrator. 
        If you believe this is a mistake or have any questions, please contact our support team.

        Best Regards,
        Hotel Booking Team
        """
    ),
    USER_DATA_UPDATED_BY_ADMIN(
        "Account Information Updated",
                """
        Dear [Guest Name],

        Your account information has been updated by an administrator. 
        If you have any questions or did not request this change, please contact our support team.

        Best Regards,
        Hotel Booking Team
        """
    ),
    USER_DATA_UPDATED_BY_SELF(
        "Account Information Updated",
                """
        Dear [Guest Name],

        Your account information has been successfully updated. 
        If you did not make this change, please contact our support team immediately.

        Best Regards,
        Hotel Booking Team
        """
    ),
    USER_ADDED_BY_ADMIN(
        "Welcome to Our Hotel Booking Platform",
                """
        Dear [Guest Name],

        An account has been created for you by an administrator. 
        You can now log in using your email and the temporary password provided.

        Best Regards,
        Hotel Booking Team
        """
    )

    ;

    private String subject;
    private String body;
}
