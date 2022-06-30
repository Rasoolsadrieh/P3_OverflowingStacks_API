package com.revature.overflowingStacks.notification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.overflowingStacks.user.User;
import com.revature.overflowingStacks.user.UserDao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotificationServices.class})
@ExtendWith(SpringExtension.class)
class NotificationServicesTest {
    @MockBean
    private NotificationDao notificationDao;

    @Autowired
    private NotificationServices notificationServices;

    @MockBean
    private UserDao userDao;

    /**
     * Method under test: {@link NotificationServices#sendEmailTest()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendEmailTest() {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        notificationServices.sendEmailTest();
    }

    /**
     * Method under test: {@link NotificationServices#create(Notification)}
     */
    @Test
    void testCreate() {
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
        when(notificationDao.save((Notification) any())).thenReturn(notification);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");

        Notification notification1 = new Notification();
        notification1.setContents("Not all who wander are lost");
        notification1.setDate("2020-03-01");
        notification1.setEmail(user1);
        notification1.setNotificationId("42");
        assertSame(notification, notificationServices.create(notification1));
        verify(notificationDao).save((Notification) any());
    }

    /**
     * Method under test: {@link NotificationServices#update(Notification)}
     */
    @Test
    void testUpdate() {
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
        assertNull(notificationServices.update(notification));
    }

    /**
     * Method under test: {@link NotificationServices#update(Notification)}
     */
    @Test
    void testUpdate2() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        Notification notification = mock(Notification.class);
        doNothing().when(notification).setContents((String) any());
        doNothing().when(notification).setDate((String) any());
        doNothing().when(notification).setEmail((User) any());
        doNothing().when(notification).setNotificationId((String) any());
        notification.setContents("Not all who wander are lost");
        notification.setDate("2020-03-01");
        notification.setEmail(user);
        notification.setNotificationId("42");
        assertNull(notificationServices.update(notification));
        verify(notification).setContents((String) any());
        verify(notification).setDate((String) any());
        verify(notification).setEmail((User) any());
        verify(notification).setNotificationId((String) any());
    }

    /**
     * Method under test: {@link NotificationServices#readById(String)}
     */
    @Test
    void testReadById() {
        assertNull(notificationServices.readById("42"));
    }

    /**
     * Method under test: {@link NotificationServices#readAll()}
     */
    @Test
    void testReadAll() {
        assertNull(notificationServices.readAll());
    }

    /**
     * Method under test: {@link NotificationServices#readByEmail(User)}
     */
    @Test
    void testReadByEmail() {
        ArrayList<Notification> notificationList = new ArrayList<>();
        when(notificationDao.notificationHistory((User) any())).thenReturn(notificationList);

        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        List<Notification> actualReadByEmailResult = notificationServices.readByEmail(user);
        assertSame(notificationList, actualReadByEmailResult);
        assertTrue(actualReadByEmailResult.isEmpty());
        verify(notificationDao).notificationHistory((User) any());
    }

    /**
     * Method under test: {@link NotificationServices#delete(String)}
     */
    @Test
    void testDelete() {
        doNothing().when(notificationDao).deleteById((String) any());
        assertTrue(notificationServices.delete("42"));
        verify(notificationDao).deleteById((String) any());
    }

    /**
     * Method under test: {@link NotificationServices#validateInput(Notification)}
     */
    @Test
    void testValidateInput() {
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
        assertFalse(notificationServices.validateInput(notification));
    }

    /**
     * Method under test: {@link NotificationServices#validateInput(Notification)}
     */
    @Test
    void testValidateInput2() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        Notification notification = mock(Notification.class);
        doNothing().when(notification).setContents((String) any());
        doNothing().when(notification).setDate((String) any());
        doNothing().when(notification).setEmail((User) any());
        doNothing().when(notification).setNotificationId((String) any());
        notification.setContents("Not all who wander are lost");
        notification.setDate("2020-03-01");
        notification.setEmail(user);
        notification.setNotificationId("42");
        assertFalse(notificationServices.validateInput(notification));
        verify(notification).setContents((String) any());
        verify(notification).setDate((String) any());
        verify(notification).setEmail((User) any());
        verify(notification).setNotificationId((String) any());
    }
}

