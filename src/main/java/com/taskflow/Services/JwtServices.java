package com.taskflow.Services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.taskflow.Utils.EnvConfig;

import java.time.Instant;
import java.util.Date;


public class JwtServices {


    private static final String SECRET_KEY = EnvConfig.get("SECRET_KEY");
    private static final String ISSUER = "TaskFlow";
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(String userId){
        Instant now = Instant.now();
        Instant expireAt = now.plusSeconds(3600); // 1 hora de validade
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(userId)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expireAt))
                .sign(algorithm);
    }

    public String validateToken(String token){
        try {
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }



}
