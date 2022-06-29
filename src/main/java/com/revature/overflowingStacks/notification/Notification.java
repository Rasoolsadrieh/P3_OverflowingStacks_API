package com.revature.overflowingStacks.notification;


import com.revature.overflowingStacks.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification{
    @Id
    @Column(name = "notification_id")
    private String notificationId;
    @Column(name = "notification_contents")
    private String contents;
    @Column(name = "notification_date")
    private String date;
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_email", referencedColumnName = "email")
    private User email;
}
