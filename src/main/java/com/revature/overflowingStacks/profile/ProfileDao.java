package com.revature.overflowingStacks.profile;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileDao extends CrudRepository <Profile, String> {


}
