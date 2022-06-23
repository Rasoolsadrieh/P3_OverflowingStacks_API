package com.revature.overflowingStacks.profile;

import com.revature.overflowingStacks.util.interfaces.Authable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProfileServlet implements Authable {

    private final ProfileServices profileServices;

    @Autowired
    public ProfileServlet(ProfileServices profileServices){
        this.profileServices = profileServices;
    }

    @GetMapping("/findProfile")
    public ResponseEntity<Profile> FindWhomProfile(@RequestParam String id){
        try {
            Profile profile = profileServices.readById(id);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

    }

    @PutMapping("/updateProfile")
    public ResponseEntity<Profile> updateTheProfile(@RequestBody Profile profile) {
        Profile updateProfile= profileServices.update(profile);
        return new ResponseEntity<>(updateProfile, HttpStatus.OK);
    }

}
