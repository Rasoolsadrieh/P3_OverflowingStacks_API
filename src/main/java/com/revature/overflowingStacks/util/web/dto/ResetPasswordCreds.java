package com.revature.overflowingStacks.util.web.dto;

public class ResetPasswordCreds {

    private String email;
    private String password;
    private String newpassword;

    // JACKSON REQUIRES A NO ARG CONSTRUCTOR


    public ResetPasswordCreds() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}

