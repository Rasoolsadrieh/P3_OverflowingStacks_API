package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.exceptions.InvalidRequestException;
import com.revature.overflowingStacks.util.interfaces.Serviceable;

import java.util.List;
import java.util.Optional;

public class UserServices implements Serviceable<User> {

    private final UserDao userDao;

    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User newObject) {
        return null;
    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User readById(String id) {
        return null;
    }

    @Override
    public User update(User updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(User object) {
        return false;
    }


    public boolean resetPassword(String email, String newPassword) {

        boolean isSuccess = false;
        userDao.updatePassword(email, newPassword);
        isSuccess = true;
        return isSuccess;
    }
}
