package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Authable;
import com.revature.overflowingStacks.util.web.dto.ResetPasswordCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserServlet implements Authable {
    private final UserServices userServices;

    @Autowired
    public UserServlet(UserServices userServices){
        this.userServices=userServices;
    }


    @PutMapping("/resetPassword")
    public String resetPassword(@RequestBody ResetPasswordCreds rpc){

//        User oldPassword = userServices.readById(rpc.getEmail());
//
//        oldPassword.setPassword(rpc.getNewpassword());
//
//        User newPassword = userServices.update(oldPassword);

        String message = "";
        if (userServices.update(rpc))
            message = "Your password has been reset";
        else
            message = "Please check your email and previous password to update your password";
        return message;
    }


}
