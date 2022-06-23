package com.revature.overflowingStacks.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileServlet {
    private final ProfileServices profileServices;
    @Autowired
    public ProfileServlet(ProfileServices profileServices){
        this.profileServices = profileServices;
    }
    @GetMapping("/findAllProfile")
    public ResponseEntity<List> findAllProfile(){
        return new ResponseEntity<>(profileServices.readAll(), HttpStatus.FOUND);
    }
    @GetMapping("/findProfile")
    public ResponseEntity<Profile> findProfile(@RequestParam String profileName){
        Profile profile = profileServices.readById(profileName);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Profile> saveCustomer(@RequestBody Profile profile) {
        Profile newProfile = profileServices.create(profile);
        return new ResponseEntity<>(newProfile, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
        Profile newProfile = profileServices.update(profile);
        return new ResponseEntity<>(newProfile, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public void deleteProfile(@RequestParam String profileName) {
        boolean newProfile = profileServices.delete(profileName);
    }

}
