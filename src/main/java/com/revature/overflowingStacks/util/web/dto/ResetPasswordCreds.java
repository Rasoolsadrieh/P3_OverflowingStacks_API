package com.revature.overflowingStacks.util.web.dto;

public class ResetPasswordCreds {

    private String email;
    private String newpassword;

    // JACKSON REQUIRES A NO ARG CONSTRUCTOR


    public ResetPasswordCreds() {
    }

    public ResetPasswordCreds(String email, String newpassword) {
        this.email = email;
        this.newpassword = newpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
