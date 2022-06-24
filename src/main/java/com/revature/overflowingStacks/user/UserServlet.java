package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Authable;
import com.revature.overflowingStacks.util.web.dto.CodeCheck;
import dev.turingcomplete.kotlinonetimepassword.GoogleAuthenticator;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes

@RestController
@CrossOrigin
@EnableScheduling
public class UserServlet implements Authable {
    private final UserServices userServices;

    @Autowired
    public UserServlet(UserServices userServices){
        this.userServices=userServices;
    }

<<<<<<< Updated upstream
=======

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){

        val secret = GoogleAuthenticator.Companion.createRandomSecret();

        System.out.println(secret);
        user.setSecret(secret);
        User newUser = userServices.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/authCheck")
    public ResponseEntity<User> checkAuth(@RequestBody CodeCheck codeCheck){

        User user = userServices.readById(codeCheck.getEmail());
        System.out.println(user + "0");
        String usersSecret = user.getSecret();
        System.out.println(usersSecret + "1");
        String sixCode = userServices.getTOTPCode(usersSecret);
        System.out.println(sixCode + "2");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

>>>>>>> Stashed changes

}
