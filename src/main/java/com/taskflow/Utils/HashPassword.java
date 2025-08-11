package com.taskflow.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {
    public static String generateHash(String senha ){
        return BCrypt.hashpw(senha, BCrypt.gensalt(4));
    }

    public static boolean checkPassword(String senha, String hashedSenha){
        return BCrypt.checkpw(senha, hashedSenha);
    }
}
