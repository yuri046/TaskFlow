package com.taskflow.Services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;


public class JwtServices {


    private static final String SECRET_KEY = System.getenv("SECRET_KEY");
    private static final String ISSUER = "TaskFlow";
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(String userId){
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(userId)
                .sign(algorithm);
    }

    public String validateToken(String token){
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

}
