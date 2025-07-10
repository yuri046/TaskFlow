package com.taskflow.Utils;

public class StringUtils {
    public static boolean isNotBlank(String str){
        return str != null && !str.isBlank();
    }

    public static boolean validatePasswordFormat(String password){
        return password.matches("^\\S{10}$");
    }
}
