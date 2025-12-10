package com.taskflow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.taskflow.Routes.AuthRoutes;
import com.taskflow.Routes.TaskRoutes;
import com.taskflow.Routes.UserRoutes;
import com.taskflow.Utils.JpaUtil;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.plugin.bundled.CorsPluginConfig;
import jakarta.persistence.EntityManager;


public class Main {
    public static void main(String[] args) {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "7000"));

        Javalin app = Javalin.create(config -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            config.jsonMapper(new JavalinJackson());

            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(CorsPluginConfig.CorsRule::anyHost);
            });
        }).start(port);

        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Authorization, Content-Type");
        });

        app.options("/*", ctx -> {
            ctx.status(204);
        });

        EntityManager em = JpaUtil.getEntityManager();

        AuthRoutes.Configure(app, em);
        UserRoutes.Configure(app, em);
        TaskRoutes.Configure(app, em);
    }
}