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

    @Id
    @Column(name = "profile_name")
    private String profileName;
    @OneToOne(optional = false)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User email;
    private double balance;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_number")
    private String accountNumber;
//    private String profilePicture;
}
