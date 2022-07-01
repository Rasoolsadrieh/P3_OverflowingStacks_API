package com.revature.overflowingStacks.notification;

import com.revature.overflowingStacks.user.User;
import com.revature.overflowingStacks.user.UserServices;
import com.revature.overflowingStacks.util.web.dto.NotificationInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class NotificationServlet {

    private final NotificationServices notificationServices;
    private final UserServices userServices;

    @Autowired
    public NotificationServlet(NotificationServices notificationServices,UserServices customerServices ){
        this.notificationServices = notificationServices;
        this.userServices = customerServices;
    }

    @PostMapping("/notification")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationInitializer initNote){

        Notification newNotification = new Notification();
        User email = userServices.readById(initNote.getEmail());

        newNotification.setNotificationId(initNote.getNotificationId());
        newNotification.setEmail(email);

        Notification persistedNotification = notificationServices.create(newNotification);
        return new ResponseEntity<>(persistedNotification, HttpStatus.CREATED);
    }

    @GetMapping("/notification/{email}")
    public ResponseEntity<List<Notification>> getNotification(@PathVariable String email){
        User user = userServices.readById(email);
        List<Notification> notifications = notificationServices.readByEmail(user);
        return new ResponseEntity<>(notifications, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletenoti/{notiId}")// in case there is a situation that we need to delete the notification
    public ResponseEntity<Notification> deleteNotification(@PathVariable String notiId) {
        notificationServices.delete(notiId);
        return new ResponseEntity<Notification>(HttpStatus.OK);
    }

}
