package com.Restaurant.management.system.dtos;

import com.Restaurant.management.system.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private long id;

    private String name;

    private String email;

    private  String password;

    private UserRole userRole;

}
