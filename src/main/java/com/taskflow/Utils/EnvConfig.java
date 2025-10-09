package com.taskflow.Utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing() // ignora se .env não existir
            .load();

    public static String get(String key) {
        String value = dotenv.get(key);
        if (value != null) {
            return value;
        }
        return System.getenv(key); // fallback para variáveis de ambiente do sistema
    }
}