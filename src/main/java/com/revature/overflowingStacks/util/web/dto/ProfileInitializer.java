package com.revature.overflowingStacks.util.web.dto;

import com.revature.overflowingStacks.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInitializer {

    private String profileName;
    private String fname;
    private String lname;
    private String email;
    private double balance;
    private String accountName;
    private String accountNumber;

}
