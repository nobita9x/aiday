package com.aiday.network.body;

import java.util.ArrayList;

public class UserListBody {
    private String token;
    private String email;
    private ArrayList<String> phones;

    public UserListBody(String token, String email, ArrayList<String> phones) {
        this.token = token;
        this.email = email;
        this.phones = phones;
    }
}
