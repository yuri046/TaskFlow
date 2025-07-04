package com.taskflow.Controller;

import com.taskflow.DTO.TaskDTO;
import com.taskflow.Entity.TaskEntity;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.ResourceNotFoundException;
import com.taskflow.Repository.TaskRepository;
import com.taskflow.Repository.UserRepository;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

public class TaskController {
    private EntityManager em;

    public TaskController(EntityManager em){
        this.em = em;
    }

    public void CreateTask(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        TaskDTO dto = ctx.bodyAsClass(TaskDTO.class);

        UserEntity user;
        UserRepository userRepo = new UserRepository(em);

        try{
            user = userRepo.findById(id);
        } catch(ResourceNotFoundException e){
            ctx.status(404).result("Usuario nao encontrado");
            return;
        }

        TaskEntity newTask = new TaskEntity(
                dto.getTitulo(),
                dto.getDescricao()
        );

        newTask.setUsuario(user);

        TaskRepository taskRepo = new TaskRepository(em);
        taskRepo.save(newTask);





    }


}
