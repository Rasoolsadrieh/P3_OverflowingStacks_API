package com.revature.overflowingStacks.util.web.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


// @WebServlet("/auth") // this requires a default No-Args constructor, but we make our own constructor in line 24


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthServlet {

    private final UsersServices usersServices;

    @Autowired
    public AuthServlet(UsersServices usersServices){
        this.usersServices = usersServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authorizeUser(@RequestBody LoginCreds loginCreds, HttpSession httpSession){
        Users authUser = usersServices.authenticateUser(loginCreds.getUsername(), loginCreds.getPassword());
        httpSession.setAttribute("authUser", authUser);
    }

    @DeleteMapping
    public void logout(HttpSession session){
        session.invalidate();
    }

}
