package com.revature.overflowingStacks.profile;

import com.revature.overflowingStacks.util.exceptions.InvalidRequestException;
import com.revature.overflowingStacks.util.exceptions.ResourcePersistanceException;
import com.revature.overflowingStacks.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public ProfileServices(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public boolean validateProfilenameNotUsed(String profileName){
        return profileDao.existsById(profileName);
    }

    @Override
    public Profile create(Profile newProfile){
        if(!validateInput(newProfile)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        if(validateProfilenameNotUsed(newProfile.getProfileName())){
            throw new InvalidRequestException("Profile is already in use. Please try again with another email or login into previous made account.");
        }

        Profile persistedProfile = profileDao.save(newProfile);

        if(persistedProfile == null){
            throw new ResourcePersistanceException("Users was not persisted to the database upon registration");
        }
        return persistedProfile;
    }

    @Override
    public List<Profile> readAll() {
        List<Profile> profile = (List<Profile>) profileDao.findAll();
        return profile;
    }
    @Override
    public Profile readById(String id) {
        Profile profile = profileDao.findById(id).get();
        return profile;
    }

    @Override
    public boolean delete(String profileName) {
        profileDao.deleteById(profileName);
        return true;
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
        if(newProfile.getEmail() == null||newProfile.getEmail().equals("")) return false;
        if(newProfile.getBalance() <= 0) return false;
        if(newProfile.getAccountName() == null || newProfile.getAccountName().trim().equals("")) return false;
        return (newProfile.getAccountNumber() != null)  ;
    }
}
