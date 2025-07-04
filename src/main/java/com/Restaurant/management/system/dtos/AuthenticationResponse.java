package com.Restaurant.management.system.dtos;

import com.Restaurant.management.system.enums.UserRole;
import lombok.Data;


@Data

public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private long userId;

}
