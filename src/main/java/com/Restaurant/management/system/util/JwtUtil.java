package com.Restaurant.management.system.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

   public static String SECRET ="";

   public JwtUtil(){
       try {
           KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
           SecretKey sk = keyGen.generateKey();
           SECRET = Base64.getEncoder().encodeToString(sk.getEncoded());
       } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e);
       }
   }

//    SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;


    public String generateToken(String email){
        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, email);
        return Jwts.builder()

                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .signWith(getSignKey())
                .compact();
    }

//    public String createToken(Map<String, Object> claims, String email){
//
//    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
