package com.example.tallking.entity;

public class UserContext {
    private static final ThreadLocal<UserDto> cruuser=new ThreadLocal<>();

    public static UserDto getCruuser() {
        return cruuser.get();
    }

    public static void setCruuser(UserDto user) {
        cruuser.set(user);
    }
}
