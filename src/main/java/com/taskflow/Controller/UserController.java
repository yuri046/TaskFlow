package com.taskflow.Controller;

import com.taskflow.DTO.UserDTO;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.ResourceNotFoundException;
import com.taskflow.Repository.UserRepository;
import com.taskflow.Utils.HashSenha;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

public class UserController {
    private EntityManager em;

    public UserController(EntityManager em){
        this.em = em;
    }

    public void createUser(Context ctx){
        UserDTO dto = ctx.bodyAsClass(UserDTO.class);
        UserEntity newUser = new UserEntity(
                dto.getNome(),
                dto.getEmail(),
                HashSenha.generateHash(dto.getSenha()));

        UserRepository repo = new UserRepository(em);
        repo.save(newUser);

        ctx.status(200).result("Usuario criado com sucesso!");
    }

    public void updateUser(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        UserDTO dto = ctx.bodyAsClass(UserDTO.class);

        UserRepository repo = new UserRepository(em);
        UserEntity user;

        try{
            user = repo.findById(id);
        } catch (ResourceNotFoundException e){
            ctx.status(404).result(e.getMessage());
            return;
        }


        if(dto.getNome() != null && !dto.getNome().isBlank()){
            user.setNome(dto.getNome());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()){
            user.setEmail(dto.getEmail());
        }

        if(dto.getSenha() != null && !dto.getSenha().isBlank()){
            user.setSenha(HashSenha.generateHash(dto.getSenha()));
        }

        repo.update(user);

        ctx.status(200).result("Usuario atualizado com sucesso!");

    }

    public void deleteUser(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));

        UserRepository repo = new UserRepository(em);
        UserEntity user;

        try{
            user = repo.findById(id);
        } catch (ResourceNotFoundException e){
            ctx.status(404).result(e.getMessage());
            return;
        }

        repo.delete(user);
        ctx.status(204).result("Usuario excluido com sucesso");
    }
}
