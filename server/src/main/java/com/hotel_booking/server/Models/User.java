package com.hotel_booking.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.hotel_booking.server.Models.Enums.Role;
import com.hotel_booking.server.Models.Types.BookingDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
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

    @OneToMany( fetch = FetchType.EAGER , mappedBy = "user")
    @JsonProperty("bookings")
    private List<Booking> bookings;

    @JsonProperty("bookings")
    private List<BookingDto> getBookingDtos() {
        return bookings.stream()
                .map(booking -> new BookingDto(booking.getId(), booking.getBookingDate(), booking.getVacateDate(), booking.getAmount()))
                .collect(Collectors.toList());
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
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
