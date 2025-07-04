package com.Restaurant.management.system.controllers;

import com.Restaurant.management.system.dtos.AuthenticationRequest;
import com.Restaurant.management.system.dtos.AuthenticationResponse;
import com.Restaurant.management.system.dtos.SignUpRequest;
import com.Restaurant.management.system.dtos.UserDto;
import com.Restaurant.management.system.entities.User;
import com.Restaurant.management.system.repositories.UserRepository;
import com.Restaurant.management.system.services.auth.AuthService;

import com.Restaurant.management.system.services.jwt.UserService;
import com.Restaurant.management.system.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    private  final AuthenticationManager authenticationManager;


    private final UserService userService;


    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;



    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest){

        UserDto createdUserDto = authService.createUser(signUpRequest);
        if(createdUserDto == null){
            return new ResponseEntity<>("User not created, come again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));

        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Username Or Password");
        }catch(DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User not active");
            return null;
        }
        final UserDetails userDetails =  userService.userDetailService().loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return authenticationResponse;
    }
}
