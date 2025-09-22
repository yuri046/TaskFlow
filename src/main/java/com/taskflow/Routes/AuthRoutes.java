package com.taskflow.Routes;

import com.taskflow.Controller.AuthController;
import com.taskflow.DTO.UserDTO;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;

public class AuthRoutes {
    public static void Configure(Javalin app, EntityManager em){
        AuthController authController = new AuthController(em);

        app.post("/login", authController::login);
        app.post("/register", authController::register);

    }
}
