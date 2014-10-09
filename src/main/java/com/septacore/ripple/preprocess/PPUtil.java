package com.septacore.ripple.preprocess;


public class PPUtil {
    public static String hexToChar(String i, String j, String k, String l)
    {
         return Character.toString((char)Integer.parseInt("" + i + j + k + l, 16));
    }
}