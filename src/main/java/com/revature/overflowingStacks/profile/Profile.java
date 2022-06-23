package com.revature.overflowingStacks.profile;

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
    @OneToOne(optional = false)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private String email;
    private double balance;
    @Column(name = "account-name")
    private String accountName;
    @Column(name = "account_number")
    private String accountNumber;
    private String profilephoto;

}
