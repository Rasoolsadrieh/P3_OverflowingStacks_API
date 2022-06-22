package com.revature.overflowingStacks.profile;

import com.revature.overflowingStacks.util.interfaces.Serviceable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfileServices implements Serviceable<Profile> {
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
        return null;
    }

    @Override
    public Profile update(Profile updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Profile object) {
        return false;
    }
}
