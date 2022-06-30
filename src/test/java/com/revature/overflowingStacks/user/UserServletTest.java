package com.revature.overflowingStacks.user;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.overflowingStacks.util.web.dto.CodeCheck;
import com.revature.overflowingStacks.util.web.dto.ResetPasswordCreds;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserServlet.class})
@ExtendWith(SpringExtension.class)
class UserServletTest {
    @MockBean
    private UserServices userServices;

    @Autowired
    private UserServlet userServlet;

    /**
     * Method under test: {@link UserServlet#checkAuth(CodeCheck)}
     */
    @Test
    void testCheckAuth() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        when(userServices.getTOTPCode((String) any())).thenReturn("Totp Code");
        when(userServices.readById((String) any())).thenReturn(user);

        CodeCheck codeCheck = new CodeCheck();
        codeCheck.setCode("Code");
        codeCheck.setEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(codeCheck);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/authCheck")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userServlet).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link UserServlet#checkAuth(CodeCheck)}
     */
    @Test
    void testCheckAuth2() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        when(userServices.getTOTPCode((String) any())).thenReturn("Code");
        when(userServices.readById((String) any())).thenReturn(user);

        CodeCheck codeCheck = new CodeCheck();
        codeCheck.setCode("Code");
        codeCheck.setEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(codeCheck);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/authCheck")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\",\"phoneNumber\":\"4105551212\",\"username"
                                        + "\":\"janedoe\",\"dob\":\"Dob\",\"secret\":\"Secret\"}"));
    }

    /**
     * Method under test: {@link UserServlet#createUser(User)}
     */
    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        when(userServices.create((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userServlet).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\",\"phoneNumber\":\"4105551212\",\"username"
                                        + "\":\"janedoe\",\"dob\":\"Dob\",\"secret\":\"Secret\"}"));
    }

    /**
     * Method under test: {@link UserServlet#findAllUsers()}
     */
    @Test
    void testFindAllUsers() throws Exception {
        when(userServices.readAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/findAllUsers");
        MockMvcBuilders.standaloneSetup(userServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserServlet#findUserById(String)}
     */
    @Test
    void testFindUserById() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        when(userServices.readById((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/finduser/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(userServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\",\"phoneNumber\":\"4105551212\",\"username"
                                        + "\":\"janedoe\",\"dob\":\"Dob\",\"secret\":\"Secret\"}"));
    }

    /**
     * Method under test: {@link UserServlet#resetPassword(ResetPasswordCreds)}
     */
    @Test
    void testResetPassword() throws Exception {
        when(userServices.update((ResetPasswordCreds) any())).thenReturn(true);

        ResetPasswordCreds resetPasswordCreds = new ResetPasswordCreds();
        resetPasswordCreds.setEmail("jane.doe@example.org");
        resetPasswordCreds.setNewpassword("iloveyou");
        resetPasswordCreds.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(resetPasswordCreds);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/resetPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Your password has been reset"));
    }

    /**
     * Method under test: {@link UserServlet#resetPassword(ResetPasswordCreds)}
     */
    @Test
    void testResetPassword2() throws Exception {
        when(userServices.update((ResetPasswordCreds) any())).thenReturn(false);

        ResetPasswordCreds resetPasswordCreds = new ResetPasswordCreds();
        resetPasswordCreds.setEmail("jane.doe@example.org");
        resetPasswordCreds.setNewpassword("iloveyou");
        resetPasswordCreds.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(resetPasswordCreds);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/resetPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Please check your email and previous password to update your password"));
    }
}

