package com.Restaurant.management.system.dtos;


import com.Restaurant.management.system.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SignUpRequest {

    private String name;

    private  String email;

    private String password;

}
