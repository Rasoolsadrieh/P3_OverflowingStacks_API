package com.revature.overflowingStacks.user;

import com.revature.overflowingStacks.util.interfaces.Serviceable;
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

@Service
@Transactional
public class UserServices implements Serviceable<User> {

    private  UserDao userDao;

    @Autowired
    public UserServices(UserDao userDao) {this.userDao = userDao;}


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
    }


