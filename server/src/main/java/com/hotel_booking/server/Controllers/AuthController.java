package com.hotel_booking.server.Controllers;


import com.hotel_booking.server.Models.Types.*;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Services.AuthService;
import com.hotel_booking.server.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth/register")
    public AuthResponse register(@RequestBody Register register) {
        return authService.register(register);
    }

    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody Login login) throws Exception {
        return authService.login(login);
    }

    @PostMapping("/auth/change-password")
    public AuthResponse changePassword(@RequestBody ChangePassword changePassword, @RequestHeader("Authorization") String token) throws Exception {
        return authService.changePassword(
                changePassword.getUsername(),
                changePassword.getNewPassword(),
                token.substring(7)
        );
    }

    @PutMapping("/auth/update")
    public CommonResponse updateMyProfile(@RequestHeader("Authorization") String token,@RequestBody User user) throws Exception {
        return userService.updateUser(user,token);
    }
}