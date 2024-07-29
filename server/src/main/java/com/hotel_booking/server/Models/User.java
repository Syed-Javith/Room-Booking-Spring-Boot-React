package com.hotel_booking.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel_booking.server.Models.Enums.Role;
import com.hotel_booking.server.Models.Types.BookingDto;
import com.hotel_booking.server.Utils.Utils;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    @JsonProperty("roomId")
    private Room room;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonProperty("bookings")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @JsonProperty("bookings")
    private List<BookingDto> getBookingDtos() {
        return Utils.getBookingDtoList(bookings);
    }

    @JsonProperty("roomId")
    public Integer getRoomId() {
        return room != null ? room.getRoomNumber() : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', firstName='" + firstName + "', lastName='" + lastName + "', role=" + role + '}';
    }
}