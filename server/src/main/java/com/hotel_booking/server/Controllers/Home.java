package com.hotel_booking.server.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Home {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
