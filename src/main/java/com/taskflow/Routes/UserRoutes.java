package com.taskflow.Routes;

import com.taskflow.Controller.UserController;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;

public class UserRoutes {
    public static void Configure(Javalin app, EntityManager em){
        UserController userController = new UserController(em);

        app.post("/users", userController::createUser);         //criar
        app.put("/users/:id", userController::updateUser);      //atualizar
        app.delete("users/:id", userController::deleteUser);    //deletar
    }
}
