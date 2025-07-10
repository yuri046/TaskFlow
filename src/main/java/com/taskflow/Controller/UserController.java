package com.taskflow.Controller;

import com.taskflow.DTO.UserDTO;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.ResourceNotFoundException;
import com.taskflow.Repository.UserRepository;
import com.taskflow.Services.UserServices;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

public class UserController {
    private EntityManager em;

    public UserController(EntityManager em){
        this.em = em;
    }

    public void createUser(Context ctx){
        UserDTO dto = ctx.bodyAsClass(UserDTO.class);
        UserEntity newUser = new UserEntity();
        UserServices userServices = new UserServices();

        userServices.validateCreation(dto);
        userServices.convertToEntity(dto, newUser);

        UserRepository repo = new UserRepository(em);
        repo.save(newUser);

        ctx.status(201).result("Usuario criado com sucesso!");
    }

    public void updateUser(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        UserDTO dto = ctx.bodyAsClass(UserDTO.class);

        UserRepository repo = new UserRepository(em);
        UserEntity user = new UserEntity();
        UserServices userServices = new UserServices();

        userServices.findUserById(user, repo, id, ctx);

        userServices.validateUpdate(dto);
        userServices.convertToEntity(dto, user);

        repo.update(user);

        ctx.status(200).result("Usuario atualizado com sucesso!");

    }

    public void deleteUser(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));

        UserRepository repo = new UserRepository(em);
        UserEntity user = new UserEntity();
        UserServices userServices = new UserServices();

        userServices.findUserById(user, repo, id, ctx);


        repo.delete(user);
        ctx.status(204).result("Usuario excluido com sucesso");
    }
}











