package com.revature.overflowingStacks.profile;

import com.revature.overflowingStacks.util.interfaces.Authable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProfileServlet implements Authable {

    private final ProfileServices profileServices;

    @Autowired
    public ProfileServlet(ProfileServices profileServices){
        this.profileServices = profileServices;
    }

}
