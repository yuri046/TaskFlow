package com.taskflow.Controller;

import com.taskflow.DTO.TaskDTO;
import com.taskflow.Services.TaskServices;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

import java.util.Objects;

public class TaskController {
    private final EntityManager em;

    public TaskController(EntityManager em){
        this.em = em;
    }

    public void createTask(Context ctx){
        long userId = Long.parseLong(Objects.requireNonNull(ctx.attribute("userId")));
        TaskDTO dto = ctx.bodyAsClass(TaskDTO.class);
        TaskServices services = new TaskServices(em);

        services.createTask(dto, userId);
        ctx.status(201).result("Tarefa criada com sucesso");

    }

    public void readTask(Context ctx){
        long id = Long.parseLong(ctx.pathParam("taskId"));
        TaskServices services = new TaskServices(em);
        TaskDTO dto = services.getTask(id);
        ctx.json(dto).status(200);
    }

    public void readAll(Context ctx){
        long id = Long.parseLong(Objects.requireNonNull(ctx.attribute("userId")));
        TaskServices services = new TaskServices(em);
        ctx.json(services.getAllTasks(id)).status(200);
    }

    public void updateTask(Context ctx){
        long userId = Long.parseLong(Objects.requireNonNull(ctx.attribute("userId")));
        long taskId = Long.parseLong(ctx.pathParam("taskId"));
        TaskDTO dto = ctx.bodyAsClass(TaskDTO.class);

        TaskServices services = new TaskServices(em);
        TaskDTO newDto = services.updateTask(dto, userId, taskId);
        ctx.json(newDto).status(200);
    }

    public void deleteTask(Context ctx){
        long id = Long.parseLong(ctx.pathParam("taskId"));
        TaskServices services = new TaskServices(em);

        services.deleteTask(id);
        ctx.status(204).result("Tarefa excluida com sucesso");
    }


}
