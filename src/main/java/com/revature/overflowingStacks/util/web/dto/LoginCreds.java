package com.revature.overflowingStacks.util.web.dto;

public class LoginCreds {

        private String username;
        private String password;

        // JACKSON REQUIRES A NO ARG CONSTRUCTOR

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
