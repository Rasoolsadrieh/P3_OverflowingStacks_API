package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServices implements Serviceable<User> {
    private final UserDao userDao;
    @Autowired
    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public User create(User newObject) {
        return null;
    }
    @Override
    public List<User> readAll() {
        List<User> users = (List<User>) userDao.findAll();
        return users;
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
