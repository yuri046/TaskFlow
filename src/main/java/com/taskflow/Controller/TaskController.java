package com.taskflow.Controller;

import com.taskflow.DTO.TaskDTO;
import com.taskflow.Entity.TaskEntity;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Repository.TaskRepository;
import com.taskflow.Repository.UserRepository;
import com.taskflow.Services.TaskServices;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

public class TaskController {
    private EntityManager em;

    public TaskController(EntityManager em){
        this.em = em;
    }

    public void createTask(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        TaskDTO dto = ctx.bodyAsClass(TaskDTO.class);
        TaskEntity newTask = new TaskEntity();
        UserRepository userRepo = new UserRepository(em);
        UserEntity user = userRepo.findById(id);

        TaskRepository taskRepo = new TaskRepository(em);
        TaskServices taskServices = new TaskServices();


        taskServices.validateCreation(dto);
        taskServices.convertToEntity(dto, newTask, user);

        taskRepo.save(newTask);

        ctx.status(201).result("Tarefa criada com sucesso!");

    }

    public void updateTask(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        TaskDTO dto = ctx.bodyAsClass(TaskDTO.class);


    }


}
