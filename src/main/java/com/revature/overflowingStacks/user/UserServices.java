package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.exceptions.ResourcePersistanceException;
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
