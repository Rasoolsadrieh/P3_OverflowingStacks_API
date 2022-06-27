package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.exceptions.ResourcePersistanceException;
import com.revature.overflowingStacks.util.interfaces.Serviceable;
import com.revature.overflowingStacks.util.web.dto.ResetPasswordCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServices implements Serviceable<User> {

    private UserDao userDao;
    @Autowired
    public UserServices(UserDao userDao) {this.userDao = userDao;}

    @Override
    public User create(User newUser) {
        return userDao.save(newUser);
    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User readById(String id) {
        User user = userDao.findById(id).get();
        return user;
    }


    @Override
    public User update(User updatedObject) {
        return userDao.save(updatedObject);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
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

<<<<<<< HEAD
    public boolean update(ResetPasswordCreds resetPasswordCreds) {
        if( userDao.resetPassword(resetPasswordCreds.getEmail(), resetPasswordCreds.getPassword(), resetPasswordCreds.getNewpassword()) == 1 )
            return true;
        return false;
    }
=======
>>>>>>> 80febda0b271fa6aebba40a7e55a22b9669ac475
}
