package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.exceptions.AuthenticationException;
import com.revature.overflowingStacks.util.exceptions.InvalidRequestException;
import com.revature.overflowingStacks.util.exceptions.ResourcePersistanceException;
import com.revature.overflowingStacks.util.interfaces.Serviceable;

import com.revature.overflowingStacks.util.web.dto.ResetPasswordCreds;
import de.taimos.totp.TOTP;
import dev.turingcomplete.kotlinonetimepassword.GoogleAuthenticator;
import lombok.val;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServices implements Serviceable<User> {
    private UserDao userDao;
    @Autowired
    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User newUser) {
        User persistedUser = userDao.save(newUser);

        if(persistedUser == null){
            throw new ResourcePersistanceException("User was not persisted to the database upon registration");
        }
        return persistedUser;
    }
    @Override
    public List<User> readAll() {
        List<User> users = (List<User>) userDao.findAll();
        return users;
    }
    @Override
    public User readById(String id) {
        User user = userDao.findById(id).get();
        return user;
//         Optional<User> optionalUser= userDao.findById(id);
//         if(!optionalUser.isPresent()) {
//             throw new ResourcePersistanceException("The user eamil " + id + "is not present.");
//         }
//         return optionalUser.get();

    }


    @Override
    public User update(User updatedObject) {
        return userDao.save(updatedObject);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }


    public String getTOTPCode(String secret) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secret);
        String hexKey = Hex.encodeHexString(bytes);
        System.out.println(hexKey);
        System.out.println("-----------");
        System.out.println(bytes);
        System.out.println(hexKey);
        return TOTP.getOTP(hexKey);

        }

    public boolean validateInput(User newUserProfile) {
        if (newUserProfile == null) return false;
        if (newUserProfile.getEmail() == null || newUserProfile.getEmail().trim().equals("")) return false;
        if (newUserProfile.getPhoneNumber() == null || newUserProfile.getPhoneNumber().trim().equals("")) return false;
        if (newUserProfile.getUsername() == null || newUserProfile.getUsername().trim().equals(""))
            return false;
        if (newUserProfile.getPassword() == null || newUserProfile.getPassword().trim().equals(""))
            return false;
        if (newUserProfile.getDob() == null || newUserProfile.getDob().trim().equals("")) return false;
        return true;
    }

    public User authenticateUser(String email, String password) {
        if(password == null || password.trim().equals("") || email == null || email.trim().equals("")) {
            throw new InvalidRequestException("Either email or password is an invalid entry. Please try logging in again");
        }
        Optional<User> authenticateUser = userDao.authenticateUser(email, password);
        if (!authenticateUser.isPresent()){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }
        return authenticateUser.get();
    }

    public boolean update(ResetPasswordCreds resetPasswordCreds) {

        if (userDao.resetPassword(resetPasswordCreds.getEmail(), resetPasswordCreds.getPassword(), resetPasswordCreds.getNewpassword()) == 1)
            return true;
        return false;
    }

}

