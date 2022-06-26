package com.revature.overflowingStacks.profile;

import com.revature.overflowingStacks.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data // this handles toString, hashCode, equals() and your getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "profile_table")
public class Profile {
    private int balance;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_number")
    private int accountNumber;
    @ManyToOne(optional = false)
    @JoinColumn(name = "profile_email", referencedColumnName = "email")
    private User email;
}
