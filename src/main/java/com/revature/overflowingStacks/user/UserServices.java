package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Serviceable;

import java.util.List;

public class UserServices implements Serviceable<User> {
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
}
