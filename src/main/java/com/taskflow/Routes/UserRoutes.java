package com.taskflow.Routes;

import com.taskflow.Controller.UserController;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;

public class UserRoutes {
    public void Configure(Javalin app, EntityManager em){
        UserController userController = new UserController(em);

        // mudar o metodo post para AuthRoute depois de cria-la
        app.post("/register", userController::createUser);     //criar usuario
        app.put("/users/me", userController::updateUser);      //atualizar usuario
        app.delete("users/me", userController::deleteUser);    //excluir usuario
    }
}
