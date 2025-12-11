package com.taskflow.Middleware;

import com.taskflow.Services.JwtServices;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;

public class JwtMiddleware implements Handler {

    private final JwtServices jwtServices = new JwtServices();

    @Override
    public void handle(Context ctx) {

        if ("OPTIONS".equalsIgnoreCase(ctx.method().toString())) {
            return;
        }

        String authHeader = ctx.header("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedResponse("Token ausente ou inválido");
        }

        String token = authHeader.replace("Bearer ", "");
        String userId = jwtServices.validateToken(token);

        if (userId == null) {
            throw new UnauthorizedResponse("Token inválido ou expirado");
        }

        ctx.attribute("userId", userId);
    }
}