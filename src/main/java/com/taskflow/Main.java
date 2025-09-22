package com.taskflow;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.taskflow.Routes.AuthRoutes;
import com.taskflow.Routes.TaskRoutes;
import com.taskflow.Routes.UserRoutes;
import com.taskflow.Utils.JpaUtil;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import jakarta.persistence.EntityManager;


public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());   // <- suporte para LocalDate/LocalDateTime
            config.jsonMapper(new JavalinJackson());
        }).start(7000);
        EntityManager em = JpaUtil.getEntityManager();

        AuthRoutes.Configure(app, em);
        UserRoutes.Configure(app, em);
        TaskRoutes.Configure(app, em);
    }
}