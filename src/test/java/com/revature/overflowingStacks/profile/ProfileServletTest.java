package com.revature.overflowingStacks.profile;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.overflowingStacks.user.User;
import com.revature.overflowingStacks.user.UserServices;
import com.revature.overflowingStacks.util.web.dto.ProfileInitializer;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

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

@ContextConfiguration(classes = {ProfileServlet.class})
@ExtendWith(SpringExtension.class)
class ProfileServletTest {
    @MockBean
    private ProfileServices profileServices;

    @Autowired
    private ProfileServlet profileServlet;

    @MockBean
    private UserServices userServices;

    /**
     * Method under test: {@link ProfileServlet#CreateProfile(ProfileInitializer, HttpSession)}
     */
    @Test
    void testCreateProfile() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        when(profileServices.create((Profile) any())).thenReturn(profile);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        when(userServices.readById((String) any())).thenReturn(user1);

        ProfileInitializer profileInitializer = new ProfileInitializer();
        profileInitializer.setAccountName("Dr Jane Doe");
        profileInitializer.setAccountNumber("42");
        profileInitializer.setBalance(10.0d);
        profileInitializer.setEmail("jane.doe@example.org");
        profileInitializer.setFname("Fname");
        profileInitializer.setLname("Lname");
        profileInitializer.setProfileName("foo.txt");
        String content = (new ObjectMapper()).writeValueAsString(profileInitializer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(profileServlet).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"profileName\":\"foo.txt\",\"email\":{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\","
                                        + "\"phoneNumber\":\"4105551212\",\"username\":\"janedoe\",\"dob\":\"Dob\",\"secret\":\"Secret\"},\"balance\":10.0,\"accountName"
                                        + "\":\"Dr Jane Doe\",\"accountNumber\":\"42\"}"));
    }

    /**
     * Method under test: {@link ProfileServlet#deleteProfile(String)}
     */
    @Test
    void testDeleteProfile() throws Exception {
        when(profileServices.delete((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/profile/delete")
                .param("profileName", "foo");
        MockMvcBuilders.standaloneSetup(profileServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ProfileServlet#findAllProfile()}
     */
    @Test
    void testFindAllProfile() throws Exception {
        when(profileServices.readAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/findAllProfile");
        MockMvcBuilders.standaloneSetup(profileServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProfileServlet#findAllProfile()}
     */
    @Test
    void testFindAllProfile2() throws Exception {
        User user = new User();
        user.setDob("?");
        user.setEmail("jane.doe@example.org");
        user.setFname("?");
        user.setLname("?");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("?");
        user.setUsername("janedoe");

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");

        ArrayList<Profile> profileList = new ArrayList<>();
        profileList.add(profile);
        when(profileServices.readAll()).thenReturn(profileList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/findAllProfile");
        MockMvcBuilders.standaloneSetup(profileServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"profileName\":\"foo.txt\",\"email\":{\"email\":\"jane.doe@example.org\",\"fname\":\"?\",\"lname\":\"?\",\"phoneNumber"
                                        + "\":\"4105551212\",\"username\":\"janedoe\",\"dob\":\"?\",\"secret\":\"?\"},\"balance\":10.0,\"accountName\":\"Dr Jane"
                                        + " Doe\",\"accountNumber\":\"42\"}]"));
    }

    /**
     * Method under test: {@link ProfileServlet#findProfile(String)}
     */
    @Test
    void testFindProfile() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        when(profileServices.readById((String) any())).thenReturn(profile);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/findProfile")
                .param("profileName", "foo");
        MockMvcBuilders.standaloneSetup(profileServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"profileName\":\"foo.txt\",\"email\":{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\","
                                        + "\"phoneNumber\":\"4105551212\",\"username\":\"janedoe\",\"dob\":\"Dob\",\"secret\":\"Secret\"},\"balance\":10.0,\"accountName"
                                        + "\":\"Dr Jane Doe\",\"accountNumber\":\"42\"}"));
    }

    /**
     * Method under test: {@link ProfileServlet#updateProfile(ProfileInitializer, HttpSession)}
     */
    @Test
    void testUpdateProfile() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        when(profileServices.update((Profile) any())).thenReturn(profile);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        when(userServices.readById((String) any())).thenReturn(user1);

        ProfileInitializer profileInitializer = new ProfileInitializer();
        profileInitializer.setAccountName("Dr Jane Doe");
        profileInitializer.setAccountNumber("42");
        profileInitializer.setBalance(10.0d);
        profileInitializer.setEmail("jane.doe@example.org");
        profileInitializer.setFname("Fname");
        profileInitializer.setLname("Lname");
        profileInitializer.setProfileName("foo.txt");
        String content = (new ObjectMapper()).writeValueAsString(profileInitializer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/profile/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(profileServlet).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"profileName\":\"foo.txt\",\"email\":{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\","
                                        + "\"phoneNumber\":\"4105551212\",\"username\":\"janedoe\",\"dob\":\"Dob\",\"secret\":\"Secret\"},\"balance\":10.0,\"accountName"
                                        + "\":\"Dr Jane Doe\",\"accountNumber\":\"42\"}"));
    }

    /**
     * Method under test: {@link ProfileServlet#updateTheProfile(Profile)}
     */
    @Test
    void testUpdateTheProfile() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        when(profileServices.update((Profile) any())).thenReturn(profile);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");

        Profile profile1 = new Profile();
        profile1.setAccountName("Dr Jane Doe");
        profile1.setAccountNumber("42");
        profile1.setBalance(10.0d);
        profile1.setEmail(user1);
        profile1.setProfileName("foo.txt");
        String content = (new ObjectMapper()).writeValueAsString(profile1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/profile/updateProfile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(profileServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"profileName\":\"foo.txt\",\"email\":{\"email\":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\","
                                        + "\"phoneNumber\":\"4105551212\",\"username\":\"janedoe\",\"dob\":\"Dob\",\"secret\":\"Secret\"},\"balance\":10.0,\"accountName"
                                        + "\":\"Dr Jane Doe\",\"accountNumber\":\"42\"}"));
    }
}

