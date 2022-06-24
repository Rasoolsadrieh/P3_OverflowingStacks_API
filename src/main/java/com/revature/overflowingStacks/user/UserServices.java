package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        userDao.save(updatedObject);
        return null;
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

    public boolean resetPassword(String email, String newPassword) {

        boolean isSuccess = false;
        userDao.updatePassword(email, newPassword);
        isSuccess = true;
        return isSuccess;
    }
}
