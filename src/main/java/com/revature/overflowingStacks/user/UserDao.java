package com.revature.overflowingStacks.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao extends CrudRepository<User,String> {

    @Query(value = "UPDATE User set password = :newPassword WHERE email = :email")
    void updatePassword(String email, String newPassword);
}
