package com.hotel_booking.server.Services;


import com.hotel_booking.server.Exceptions.UnauthorizedAccessException;
import com.hotel_booking.server.Models.Enums.Role;
import com.hotel_booking.server.Models.Types.AuthResponse;
import com.hotel_booking.server.Models.Types.Login;
import com.hotel_booking.server.Models.Types.Register;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(Register register){
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setRole(Role.USER);
        repo.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(Login login) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()
                )
        );
        User user = repo.findByUsername(login.getUsername()).orElseThrow(
                () -> new UnauthorizedAccessException("User not found")
        );
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        String token = jwtService.generateToken(user);
        System.out.println("Authorities " + user.getAuthorities().toString());
        return new AuthResponse(token);

    }
}