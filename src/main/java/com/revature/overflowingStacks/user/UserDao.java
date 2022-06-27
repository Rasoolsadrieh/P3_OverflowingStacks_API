package com.revature.overflowingStacks.user;

import org.springframework.data.jpa.repository.Modifying;

import com.revature.overflowingStacks.profile.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface UserDao extends CrudRepository<User,String> {


    @Modifying
    @Query(value = "UPDATE User set password = :newPassword WHERE email = :email AND password = :password")
    int resetPassword(String email, String password, String newPassword);

    @Query(value = "Select User WHERE email= :email and password= : password")
    Optional<User> authenticateUser(String email, String password);

}
