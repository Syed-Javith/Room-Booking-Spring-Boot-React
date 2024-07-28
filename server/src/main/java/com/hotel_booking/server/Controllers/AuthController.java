package com.hotel_booking.server.Controllers;


import com.hotel_booking.server.Models.Types.AuthResponse;
import com.hotel_booking.server.Models.Types.ChangePassword;
import com.hotel_booking.server.Models.Types.Login;
import com.hotel_booking.server.Models.Types.Register;
import com.hotel_booking.server.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final AuthService authService;

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
}