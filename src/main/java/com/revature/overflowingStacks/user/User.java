package com.revature.overflowingStacks.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data // this handles toString, hashCode, equals() and your getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "user_table")
public class User {
    @Id
    private String email;
    private String fname;
    private String lname;
    @Column(name="phone_number")
    private String phoneNumber;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String dob;
    private String secret;
}
