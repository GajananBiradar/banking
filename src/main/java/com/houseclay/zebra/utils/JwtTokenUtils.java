package com.houseclay.zebra.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtTokenUtils {

    @Autowired private final ApplicationConfig appConfig;

    public JwtTokenUtils(ApplicationConfig appConfig) {
        this.appConfig = appConfig;
    }


    public String extractUsernamefromToken(String authtoken) {

        if (authtoken != null && authtoken.startsWith("Bearer ")) {
            try {
                String token = authtoken.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                return username;
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return "No Token Found";
    }

}
