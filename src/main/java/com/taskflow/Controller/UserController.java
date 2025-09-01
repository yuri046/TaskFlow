package com.taskflow.Controller;

import com.taskflow.DTO.UserDTO;
import com.taskflow.Services.UserServices;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;


public class UserController {
    private final EntityManager em;

    public UserController(EntityManager em){
        this.em = em;
    }

    /*
    *  Futuro metodo de login
    *  implementar depois de configurar o toke JWT
    * */
    public void getUser(Context ctx){

    }

    public void createUser(Context ctx){
        UserDTO dto = ctx.bodyAsClass(UserDTO.class);
        UserServices services = new UserServices(em);
        services.createUser(dto);
        ctx.status(201).result("Usuario criado com sucesso!");
    }

    public void updateUser(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        UserDTO dto = ctx.bodyAsClass(UserDTO.class);
        UserServices services = new UserServices(em);
        services.updateUser(dto, id);
        ctx.status(200).result("Usuario atualizado com sucesso!");

    }

    public void deleteUser(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        UserServices services = new UserServices(em);
        services.deleteUser(id);
        ctx.status(204).result("Usuario excluido com sucesso");
    }
}











