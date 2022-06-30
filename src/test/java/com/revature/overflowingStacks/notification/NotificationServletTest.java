package com.revature.overflowingStacks.notification;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.overflowingStacks.user.User;
import com.revature.overflowingStacks.user.UserServices;
import com.revature.overflowingStacks.util.web.dto.NotificationInitializer;

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

@ContextConfiguration(classes = {NotificationServlet.class})
@ExtendWith(SpringExtension.class)
class NotificationServletTest {
    @MockBean
    private NotificationServices notificationServices;

    @Autowired
    private NotificationServlet notificationServlet;

    @MockBean
    private UserServices userServices;

    /**
     * Method under test: {@link NotificationServlet#createNotification(NotificationInitializer)}
     */
    @Test
    void testCreateNotification() throws Exception {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        Notification notification = new Notification();
        notification.setContents("Not all who wander are lost");
        notification.setDate("2020-03-01");
        notification.setEmail(user);
        notification.setNotificationId("42");
        when(notificationServices.create((Notification) any())).thenReturn(notification);

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

        NotificationInitializer notificationInitializer = new NotificationInitializer();
        notificationInitializer.setContents("Not all who wander are lost");
        notificationInitializer.setDate("2020-03-01");
        notificationInitializer.setEmail("jane.doe@example.org");
        notificationInitializer.setNotificationId("42");
        String content = (new ObjectMapper()).writeValueAsString(notificationInitializer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/notification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(notificationServlet)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"notificationId\":\"42\",\"contents\":\"Not all who wander are lost\",\"date\":\"2020-03-01\",\"email\":{\"email\""
                                        + ":\"jane.doe@example.org\",\"fname\":\"Fname\",\"lname\":\"Lname\",\"phoneNumber\":\"4105551212\",\"username\":\"janedoe"
                                        + "\",\"dob\":\"Dob\",\"secret\":\"Secret\"}}"));
    }

    /**
     * Method under test: {@link NotificationServlet#deleteNotification(String)}
     */
    @Test
    void testDeleteNotification() throws Exception {
        when(notificationServices.delete((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deletenoti/{notiId}", "42");
        MockMvcBuilders.standaloneSetup(notificationServlet)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NotificationServlet#deleteNotification(String)}
     */
    @Test
    void testDeleteNotification2() throws Exception {
        when(notificationServices.delete((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/deletenoti/{notiId}", "42");
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(notificationServlet)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NotificationServlet#getNotification(String)}
     */
    @Test
    void testGetNotification() throws Exception {
        when(notificationServices.readByEmail((User) any())).thenReturn(new ArrayList<>());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notification/{email}",
                "jane.doe@example.org");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(notificationServlet)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

