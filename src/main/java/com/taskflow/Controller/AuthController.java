package com.taskflow.Controller;

import com.taskflow.DTO.LoginRequestDTO;
import com.taskflow.DTO.RegisterDTO;
import com.taskflow.DTO.TaskListDTO;
import com.taskflow.DTO.UserDTO;
import com.taskflow.Services.JwtServices;
import com.taskflow.Services.UserServices;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

import java.util.Map;
import java.util.Objects;

public class AuthController {
    private final EntityManager em;

    public AuthController(EntityManager em){this.em = em;}

    public void login(Context ctx){
        LoginRequestDTO req = ctx.bodyAsClass(LoginRequestDTO.class);
        UserServices services = new UserServices(em);
        UserDTO user = services.getUser(req);
        JwtServices jwt = new JwtServices();
        String token = jwt.generateToken(String.valueOf(user.getId()));
        System.out.println(user);
        ctx.json(Map.of(
                "user", user,
                "token", token
        ));
    }

    public void register(Context ctx){
        RegisterDTO dto = ctx.bodyAsClass(RegisterDTO.class);
        UserServices services = new UserServices(em);
        services.createUser(dto);

        ctx.status(201).result("Usuario cadastrado com sucesso!");
    }
}
