package com.taskflow.Routes;

import com.taskflow.Controller.TaskController;
import com.taskflow.Middleware.JwtMiddleware;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;

public class TaskRoutes {
    public static void Configure(Javalin app, EntityManager em){
        TaskController controller = new TaskController(em);
        JwtMiddleware middleware = new JwtMiddleware();

        // Proteção de rota
        app.before("/tasks", middleware);
        app.before("/tasks/*", middleware);

        app.post("/tasks", controller::createTask);
        app.get("/tasks", controller::readAll);
        app.get("/tasks/{taskId}", controller::readTask);
        app.put("/tasks/{taskId}", controller::updateTask);
        app.delete("/tasks/{taskId}", controller::deleteTask);
    }
}
