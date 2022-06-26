
<<<<<<< HEAD
<<<<<<< HEAD
import com.revature.overflowingStacks.profile.ProfileServices;
=======
=======
import org.springframework.web.bind.annotation.*;


//package com.revature.overflowingStacks.util.web.servlets;
//
//import com.revature.overflowingStacks.user.UserServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//
//
//// @WebServlet("/auth") // this requires a default No-Args constructor, but we make our own constructor in line 24
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class AuthServlet {
//
//    private final UserServices userServices;
//
//    @Autowired
//    public AuthServlet(UserServices userServices){
//        this.userServices = userServices;
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void authorizeUser(@RequestBody LoginCreds loginCreds, HttpSession httpSession){
//        User authUser = userServices.authenticateUser(loginCreds.getUsername(), loginCreds.getPassword());
//        httpSession.setAttribute("authUser", authUser);
//    }
//
//    @DeleteMapping
//    public void logout(HttpSession session){
//        session.invalidate();
//    }
//

package com.revature.overflowingStacks.util.web.servlets;
>>>>>>> 3ca83474ecd76976122f1d21271967304d6492d7
import com.fasterxml.jackson.databind.ObjectMapper;
>>>>>>> DevOps
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import javax.servlet.http.HttpSession;

>>>>>>> 3ca83474ecd76976122f1d21271967304d6492d7


// @WebServlet("/auth") // this requires a default No-Args constructor, but we make our own constructor in line 24


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthServlet {

<<<<<<< HEAD
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

=======
>>>>>>> 3ca83474ecd76976122f1d21271967304d6492d7
}

