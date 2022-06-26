package com.revature.overflowingStacks.util.web.servlets;

<<<<<<< HEAD
import com.revature.overflowingStacks.profile.ProfileServices;
=======
import com.fasterxml.jackson.databind.ObjectMapper;
>>>>>>> DevOps
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


// @WebServlet("/auth") // this requires a default No-Args constructor, but we make our own constructor in line 24
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthServlet {

<<<<<<< HEAD
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
=======
>>>>>>> DevOps

}
