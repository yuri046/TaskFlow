package com.taskflow.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JpaUtil {
    public static EntityManager getEntityManager() {
        Map<String, String> props = new HashMap<>();

        props.put("jakarta.persistence.jdbc.url",
                "jdbc:postgresql://" + EnvConfig.get("DB_HOST") + ":" +
                        EnvConfig.get("DB_PORT") + "/" +
                        EnvConfig.get("DB_NAME") + "?sslmode=require");
        props.put("jakarta.persistence.jdbc.user", EnvConfig.get("DB_USER"));
        props.put("jakarta.persistence.jdbc.password", EnvConfig.get("DB_PASSWORD"));
        props.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("taskflowPU", props);
        return emf.createEntityManager();
    }
}