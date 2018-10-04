package com.aiday.network.body;

public class UserBody {
    private String token;
    private String email;
    private String phone;

    public UserBody(String token, String email, String phone) {
        this.token = token;
        this.email = email;
        this.phone = phone;
    }
}
