package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Authable;

import com.revature.overflowingStacks.util.web.dto.CodeCheck;
import dev.turingcomplete.kotlinonetimepassword.GoogleAuthenticator;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.overflowingStacks.util.web.dto.ResetPasswordCreds;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserServlet implements Authable {
    private final UserServices userServices;

    @Autowired
    public UserServlet(UserServices userServices){
        this.userServices = userServices;
    }
    @GetMapping("/findAllUsers")
    public ResponseEntity<List> findAllUsers(){
        return new ResponseEntity<>(userServices.readAll(), HttpStatus.FOUND);
    }

    @GetMapping("finduser/{email}")
    public ResponseEntity<User> findUserById(@PathVariable String email){
        User foundUser = userServices.readById(email);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    public String resetPassword(@RequestBody ResetPasswordCreds rpc) {
        String message = "";
        if (userServices.update(rpc))
            message = "Your password has been reset";
        else
            message = "Please check your email and previous password to update your password";
        return message;
    }


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){

        val secret = GoogleAuthenticator.Companion.createRandomSecret();

        System.out.println(secret);
        user.setSecret(secret);
        User newUser = userServices.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/authCheck")
    public ResponseEntity<User> checkAuth(@RequestBody CodeCheck codeCheck){

        User user = userServices.readById(codeCheck.getEmail());
        System.out.println(user + "0");
        String usersSecret = user.getSecret();
        System.out.println(usersSecret + "1");
        String sixCode = userServices.getTOTPCode(usersSecret);
        System.out.println(sixCode);

        System.out.println("---------------------");
        System.out.println(codeCheck.getCode());

        if(codeCheck.getCode().equals(sixCode)) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else if(!codeCheck.getCode().equals(sixCode)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
