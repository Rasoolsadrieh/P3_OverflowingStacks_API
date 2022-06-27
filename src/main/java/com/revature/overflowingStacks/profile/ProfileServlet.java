package com.revature.overflowingStacks.profile;

import com.revature.overflowingStacks.user.User;
import com.revature.overflowingStacks.user.UserServices;
import com.revature.overflowingStacks.util.web.dto.ProfileInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileServlet {
    private final ProfileServices profileServices;
    private final UserServices userServices;
    @Autowired
    public ProfileServlet(ProfileServices profileServices, UserServices userServices){
        this.profileServices = profileServices;
        this.userServices = userServices;
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
    public ResponseEntity<Profile> CreateProfile(@RequestBody ProfileInitializer newProfilei, HttpSession req){

        Profile newProfile = new Profile();
        User authProfile= (User) req.getAttribute("authUser");

        newProfile.setProfileName(newProfilei.getProfileName());
        newProfile.setEmail(userServices.readById(newProfilei.getEmail()));
        newProfile.setBalance(newProfilei.getBalance());
        newProfile.setAccountName(newProfilei.getAccountName());
        newProfile.setAccountNumber(newProfilei.getAccountNumber());


        Profile profile = profileServices.create(newProfile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Profile> updateProfile(@RequestBody ProfileInitializer newProfilei, HttpSession req){

        Profile newProfile = new Profile();
        User authProfile= (User) req.getAttribute("authUser");

        newProfile.setProfileName(newProfilei.getProfileName());
        newProfile.setEmail(userServices.readById(newProfilei.getEmail()));
        newProfile.setBalance(newProfilei.getBalance());
        newProfile.setAccountName(newProfilei.getAccountName());
        newProfile.setAccountNumber(newProfilei.getAccountNumber());

        Profile profile = profileServices.update(newProfile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public void deleteProfile(@RequestParam String profileName) {
        boolean newProfile = profileServices.delete(profileName);
    }

//     @GetMapping("/findProfile")
//     public ResponseEntity<Profile> FindWhomProfile(@RequestParam String id){
//         try {
//             Profile profile = profileServices.readById(id);
//             return new ResponseEntity<>(profile, HttpStatus.OK);
//         }catch(Exception e){
//             return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
//         }

//     }

    @PutMapping("/updateProfile")
    public ResponseEntity<Profile> updateTheProfile(@RequestBody Profile profile) {
        Profile updateProfile= profileServices.update(profile);
        return new ResponseEntity<>(updateProfile, HttpStatus.OK);
    }

}
