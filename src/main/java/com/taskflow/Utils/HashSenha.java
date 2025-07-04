package com.taskflow.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashSenha {
    public static String gerarHashDaSenha(String senha ){
        return BCrypt.hashpw(senha,BCrypt.gensalt(4));
    }

    public static boolean checarSenha(String senha, String hashedSenha){
        return BCrypt.checkpw(senha, hashedSenha);
    }
}
