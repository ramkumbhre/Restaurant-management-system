package com.Restaurant.management.system.services.jwt;


import com.Restaurant.management.system.entities.User;
import com.Restaurant.management.system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getUserRole().name())
                .build();
    }

    // ✅ Avoid recursion
    @Override
    public UserDetailsService userDetailService() {
        return this::loadUserByUsername;
    }
}
//    @Override
//    public UserDetailsService userDetailService() {
//        return new UserDetailsService(){
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findFirstByEmail(username)
//                .orElseThrow(()->new UsernameNotFoundException("User not found"));
//    }
//    };
//    }

//    public UserDetailsService userDetailService() {
//
//        return username -> userRepository.findFirstByEmail(username)
//                .orElseThrow(()->new UsernameNotFoundException("User not found"));
//    }



