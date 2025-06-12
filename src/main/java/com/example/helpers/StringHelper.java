package com.example.helpers;

public class StringHelper {
    
    public static String capitalizeString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
