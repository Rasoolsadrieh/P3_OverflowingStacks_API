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
@Table(name= "profile")

public class Profile {
    @Id
    @Column(name = "profile_name")
    private String profileName;
    private String fname;
    private String lname;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users", referencedColumnName = "email")
    private User profileEmail;
    private int balance;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_number")
    private int accountNumber;

}
