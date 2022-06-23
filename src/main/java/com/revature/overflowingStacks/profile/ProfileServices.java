package com.revature.overflowingStacks.profile;

import com.revature.overflowingStacks.util.interfaces.Serviceable;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfileServices implements Serviceable<Profile> {
    private ProfileDao profileDao;
    @Override
    public Profile create(Profile newObject) {
        return null;
    }

    @Override
    public List<Profile> readAll() {
        return null;
    }

    @Override
    public Profile readById(String id) {
        Profile profile = profileDao.findById(id).get();
        return profile;
    }
    @Override
    public Profile update(Profile updateProfile){
        profileDao.save(updateProfile);
        return updateProfile;
    }




    @Override
    public boolean validateInput(Profile newProfile) {
        if(newProfile == null ) return false;
        if(newProfile.getProfileName() == null || newProfile.getProfileName().trim().equals("")) return false;
        if(newProfile.getFname() == null||newProfile.getFname().trim().equals("")) return false;
        if(newProfile.getLname() == null||newProfile.getLname().trim().equals("")) return false;
        if(newProfile.getEmail() == null||newProfile.getEmail().trim().equals("")) return false;
        if(newProfile.getBalance() <= 0) return false;
        if(newProfile.getAccountName() == null || newProfile.getAccountName().trim().equals("")) return false;
        return (newProfile.getAccountNumber() <= 0)  ;
    }
}
