package com.Restaurant.management.system.services.auth;

import com.Restaurant.management.system.dtos.SignUpRequest;
import com.Restaurant.management.system.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignUpRequest signUpRequest);
}
