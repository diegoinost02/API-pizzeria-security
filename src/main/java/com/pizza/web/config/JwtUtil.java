package com.pizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final static String SECRET_KEY = "s3cr7t_k3y";
    private final static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    public String create(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("pizza-app")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM).build().verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUserName(String jwt) {
        return JWT.require(ALGORITHM).build().verify(jwt).getSubject();
    }
}