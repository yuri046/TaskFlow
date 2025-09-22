package com.taskflow.Controller;

import com.taskflow.DTO.UserDTO;
import com.taskflow.Services.UserServices;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class UserController {
    private final EntityManager em;

    public UserController(EntityManager em){
        this.em = em;
    }

    public void getUser(Context ctx){
        long id = Long.parseLong(Objects.requireNonNull(ctx.attribute("userId")));
        UserServices services = new UserServices(em);
        UserDTO dto = services.getUserById(id);
        ctx.status(200).json(dto);
    }

    public void updateUser(Context ctx){
        long id = Long.parseLong(Objects.requireNonNull(ctx.attribute("userId")));
        UserDTO dto = ctx.bodyAsClass(UserDTO.class);
        UserServices services = new UserServices(em);
        UserDTO newDto = services.updateUser(dto, id);
        ctx.status(200).json(newDto);
    }

    public void deleteUser(Context ctx){
        long id = Long.parseLong(Objects.requireNonNull(ctx.attribute("userId")));
        UserServices services = new UserServices(em);
        services.deleteUser(id);
        ctx.status(204).result("Usuário excluido com sucesso");
    }
}











