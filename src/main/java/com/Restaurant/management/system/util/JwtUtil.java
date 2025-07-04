package com.Restaurant.management.system.util;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.security.Key;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {






    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){

        return Jwts.builder()
                .setClaims(extraClaims)                          // Replaces setClaims()
                .setSubject(userDetails.getUsername())            // Replaces setSubject()
                .setIssuedAt(new Date(System.currentTimeMillis())) // Replaces setIssuedAt()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Replaces setExpiration()
                .signWith(getSigningKey())                    // No need for SignatureAlgorithm in latest versions
                .compact();
                
                
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode("QR3BRb7UPu1m8xOiQ0XYzsDhUjb04DeXCJT3Jur5t74=");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extraUserName(String token){
        return extraClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extraUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extraClaims(token, Claims::getExpiration);
    }

    private <T> T extraClaims(String token, Function<Claims , T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    public String generateToken(UserDetails  userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }


}
