package com.hotel_booking.server.Utils;

public class Utils {
    public static String replaceUserName(String body,String userName) {
        return body.replace("[Guest Name]",userName);
    }
}
