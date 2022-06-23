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
}
