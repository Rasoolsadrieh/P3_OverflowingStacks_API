package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Authable;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
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

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userServices.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
