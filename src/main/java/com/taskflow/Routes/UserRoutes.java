package com.taskflow.Routes;

import com.taskflow.Controller.UserController;
import com.taskflow.Middleware.JwtMiddleware;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;

public class UserRoutes {
    public static void Configure(Javalin app, EntityManager em){
        UserController userController = new UserController(em);
        JwtMiddleware middleware = new JwtMiddleware();

        app.before("/users", middleware);
        app.before("/users/*", middleware);

        app.get("/users/me", userController::getUser);         // exibe usuario
        app.put("/users/me", userController::updateUser);      //atualizar usuario
        app.delete("/users/me", userController::deleteUser);    //excluir usuario
    }
}
