package com.taskflow.Utils;

import com.taskflow.Error.InvalidField;

public class StringUtils {
    public static boolean isNotBlank(String str){
        return str != null && !str.isBlank();
    }

    /*
    * Regex para validar se a senha possui de 6 a 12 caracteres
    * */
    public static boolean validatePasswordFormat(String password){
        if (!password.matches("^\\S{6,12}$")) {
            throw new InvalidField("A senha deve possuir de 6 a 12 caracteres!");
        }
        return true;
    }
}
