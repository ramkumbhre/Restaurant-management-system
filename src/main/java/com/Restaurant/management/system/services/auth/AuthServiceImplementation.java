package com.Restaurant.management.system.services.auth;

import com.Restaurant.management.system.dtos.SignUpRequest;
import com.Restaurant.management.system.dtos.UserDto;
import com.Restaurant.management.system.entities.User;
import com.Restaurant.management.system.enums.UserRole;
import com.Restaurant.management.system.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;



    @PostConstruct
    public void createAdminAccount(){
        List<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN); // ✅ bean usage

        if(adminAccount.isEmpty()){
//        if(!userRepository.findFirstByEmail("admin@gmail.com").isPresent()){
            User user =new User();
            user.setName("admin");
            user.setEmail("admin@gmail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin@13"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);

        }
    }

    @Override
    public UserDto createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword( new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);

        User createdUser = userRepository.save(user);
        UserDto createdUserDto = new UserDto();
        createdUserDto.setId(createdUser.getId());
        createdUserDto.setName(createdUser.getName());
        createdUserDto.setEmail(createdUser.getEmail());
        createdUserDto.setUserRole(createdUser.getUserRole());
        return createdUserDto;
    }
}
