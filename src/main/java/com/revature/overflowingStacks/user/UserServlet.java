package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Authable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserServlet implements Authable {
    private final UserServices userServices;

    @Autowired
    public UserServlet(UserServices userServices){
        this.userServices=userServices;
    }


}
