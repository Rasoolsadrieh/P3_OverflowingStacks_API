package com.revature.overflowingStacks.util.web.servlets;

import com.revature.overflowingStacks.profile.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// @WebServlet("/auth") // this requires a default No-Args constructor, but we make our own constructor in line 24
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthServlet {

    private final ProfileServices profileServices;

    @Autowired
    public AuthServlet(ProfileServices profileServices){
        this.profileServices = profileServices;
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void authorizeUser(@RequestBody LoginCreds loginCreds, HttpSession httpSession){
//        Users authUser = usersServices.authenticateUser(loginCreds.getUsername(), loginCreds.getPassword());
//        httpSession.setAttribute("authUser", authUser);
//    }
//
//    @DeleteMapping
//    public void logout(HttpSession session){
//        session.invalidate();
//    }

}
