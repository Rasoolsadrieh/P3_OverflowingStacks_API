package com.revature.overflowingStacks.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao extends CrudRepository<User,String> {

    @Modifying
    @Query(value = "UPDATE User set password = :newPassword WHERE email = :email AND password = :password")
    int resetPassword(String email, String password, String newPassword);
}
