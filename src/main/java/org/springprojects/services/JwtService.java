package org.springprojects.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService
{
    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    public UserDetails auth(String token)
    {
        byte[] decodedKey = Base64.getDecoder().decode(env.getProperty("api.key"));

        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");

        var user = Jwts.parser().verifyWith(originalKey).build().parseSignedClaims(token).getPayload().getSubject();

        return userService.loadUserByUsername(user);
    }

    public String generateToken(UserDetails userDetails)
    {

        long currentDate = System.currentTimeMillis();
        long expiration  = currentDate + 60 * 60 * 72 * 1000;

        byte[] decodedKey = Base64.getDecoder().decode(env.getProperty("api.key"));

        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");

        return Jwts.builder().
                signWith(originalKey).
                claims().
                subject(userDetails.getUsername()).
                issuer("Community-Based-Api").
                issuedAt(new Date(currentDate)).
                expiration(new Date(expiration)).and().compact();
    }


}
