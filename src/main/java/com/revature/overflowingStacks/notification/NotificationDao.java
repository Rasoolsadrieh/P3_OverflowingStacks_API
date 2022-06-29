package com.revature.overflowingStacks.notification;

import com.revature.overflowingStacks.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao extends CrudRepository <Notification, String> {
    @Query(value = "FROM Notification WHERE email= :email")
    List<Notification> notificationHistory(User email);
}
