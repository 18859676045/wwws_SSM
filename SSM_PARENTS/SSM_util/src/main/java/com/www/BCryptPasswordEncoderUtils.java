package com.www;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public static String encodePassWord(String password){

        return bCryptPasswordEncoder.encode(password);
    }


    public static void main(String[] args) {
        String s = encodePassWord("123");

        System.out.println(s);
    }

}
