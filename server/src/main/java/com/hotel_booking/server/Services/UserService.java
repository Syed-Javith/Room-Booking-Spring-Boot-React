package com.hotel_booking.server.Services;

import com.hotel_booking.server.Exceptions.UnauthorizedAccessException;
import com.hotel_booking.server.Models.Enums.EmailContent;
import com.hotel_booking.server.Models.Types.CommonResponse;
import com.hotel_booking.server.Models.Types.EmailDetails;
import com.hotel_booking.server.Models.User;
import com.hotel_booking.server.Repository.UserRepo;
import com.hotel_booking.server.Utils.Utils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final EmailService emailService;

    public UserService(UserRepo userRepo, JwtService jwtService, EmailService emailService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public CommonResponse deleteUser(String username){
        User user = userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("No such user")
        );
        userRepo.delete(user);
        return new CommonResponse(200, "User deleted successfully");
    }

    public CommonResponse addUser(User user){
        userRepo.save(user);
        return new CommonResponse(200, "User added successfully");
    }

    public CommonResponse updateUser(User user){
        userRepo.save(user);
        return new CommonResponse(200, "User updated successfully");
    }

    public CommonResponse updateUser(User user,String token){
        String username = jwtService.extractUsername(token.substring(7));
        if(user.getUsername().equals(username)){
            userRepo.save(user);
            emailService.sendSimpleMail(
                   new EmailDetails(
                            username,
                           Utils.replaceUserName(
                                   EmailContent.USER_DATA_UPDATED_BY_SELF.getBody()
                                   ,
                                   user.getFirstName()
                                   ),
                           EmailContent.USER_DATA_UPDATED_BY_SELF.getSubject(),
                           null
                    )
            );
            return new CommonResponse(200, "User updated successfully");
        }
        else throw new UnauthorizedAccessException("You have no access to update the user details");
    }
}
