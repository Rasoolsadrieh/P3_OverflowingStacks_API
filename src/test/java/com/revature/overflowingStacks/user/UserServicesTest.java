package com.revature.overflowingStacks.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.overflowingStacks.util.exceptions.AuthenticationException;
import com.revature.overflowingStacks.util.exceptions.InvalidRequestException;
import com.revature.overflowingStacks.util.exceptions.ResourcePersistanceException;
import com.revature.overflowingStacks.util.web.dto.ResetPasswordCreds;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServices.class})
@ExtendWith(SpringExtension.class)
class UserServicesTest {
    @MockBean
    private UserDao userDao;

    @Autowired
    private UserServices userServices;

    /**
     * Method under test: {@link UserServices#create(User)}
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
        when(userDao.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        assertSame(user, userServices.create(user1));
        verify(userDao).save((User) any());
    }

    /**
     * Method under test: {@link UserServices#create(User)}
     */
    @Test
    void testCreate2() {
        when(userDao.save((User) any())).thenThrow(new ResourcePersistanceException("An error occurred"));

        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertThrows(ResourcePersistanceException.class, () -> userServices.create(user));
        verify(userDao).save((User) any());
    }

    /**
     * Method under test: {@link UserServices#readAll()}
     */
    @Test
    void testReadAll() {
        when(userDao.findAll()).thenThrow(new ResourcePersistanceException("An error occurred"));
        assertThrows(ResourcePersistanceException.class, () -> userServices.readAll());
        verify(userDao).findAll();
    }

    /**
     * Method under test: {@link UserServices#readById(String)}
     */
    @Test
    void testReadById() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findById((String) any())).thenReturn(ofResult);
        assertSame(user, userServices.readById("42"));
        verify(userDao).findById((String) any());
    }

    /**
     * Method under test: {@link UserServices#readById(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testReadById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:135)
        //       at com.revature.overflowingStacks.user.UserServices.readById(UserServices.java:50)
        //   In order to prevent readById(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   readById(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(userDao.findById((String) any())).thenReturn(Optional.empty());
        userServices.readById("42");
    }

    /**
     * Method under test: {@link UserServices#readById(String)}
     */
    @Test
    void testReadById3() {
        when(userDao.findById((String) any())).thenThrow(new ResourcePersistanceException("An error occurred"));
        assertThrows(ResourcePersistanceException.class, () -> userServices.readById("42"));
        verify(userDao).findById((String) any());
    }

    /**
     * Method under test: {@link UserServices#update(User)}
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
        when(userDao.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        assertSame(user, userServices.update(user1));
        verify(userDao).save((User) any());
    }

    /**
     * Method under test: {@link UserServices#update(User)}
     */
    @Test
    void testUpdate2() {
        when(userDao.save((User) any())).thenThrow(new ResourcePersistanceException("An error occurred"));

        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertThrows(ResourcePersistanceException.class, () -> userServices.update(user));
        verify(userDao).save((User) any());
    }

    /**
     * Method under test: {@link UserServices#update(ResetPasswordCreds)}
     */
    @Test
    void testUpdate3() {
        when(userDao.resetPassword((String) any(), (String) any(), (String) any())).thenReturn(1);

        ResetPasswordCreds resetPasswordCreds = new ResetPasswordCreds();
        resetPasswordCreds.setEmail("jane.doe@example.org");
        resetPasswordCreds.setNewpassword("iloveyou");
        resetPasswordCreds.setPassword("iloveyou");
        assertTrue(userServices.update(resetPasswordCreds));
        verify(userDao).resetPassword((String) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link UserServices#update(ResetPasswordCreds)}
     */
    @Test
    void testUpdate4() {
        when(userDao.resetPassword((String) any(), (String) any(), (String) any())).thenReturn(0);

        ResetPasswordCreds resetPasswordCreds = new ResetPasswordCreds();
        resetPasswordCreds.setEmail("jane.doe@example.org");
        resetPasswordCreds.setNewpassword("iloveyou");
        resetPasswordCreds.setPassword("iloveyou");
        assertFalse(userServices.update(resetPasswordCreds));
        verify(userDao).resetPassword((String) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link UserServices#update(ResetPasswordCreds)}
     */
    @Test
    void testUpdate5() {
        when(userDao.resetPassword((String) any(), (String) any(), (String) any()))
                .thenThrow(new ResourcePersistanceException("An error occurred"));

        ResetPasswordCreds resetPasswordCreds = new ResetPasswordCreds();
        resetPasswordCreds.setEmail("jane.doe@example.org");
        resetPasswordCreds.setNewpassword("iloveyou");
        resetPasswordCreds.setPassword("iloveyou");
        assertThrows(ResourcePersistanceException.class, () -> userServices.update(resetPasswordCreds));
        verify(userDao).resetPassword((String) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link UserServices#delete(String)}
     */
    @Test
    void testDelete() {
        assertFalse(userServices.delete("42"));
    }

    /**
     * Method under test: {@link UserServices#getTOTPCode(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTOTPCode() {
        // TODO: Complete this test.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests which were time-sensitive.
        //   The assertions no longer passed when run at an alternate date, time and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   Please see https://diff.blue/R031

        userServices.getTOTPCode("Secret");
    }

    /**
     * Method under test: {@link UserServices#getTOTPCode(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTOTPCode2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Empty key
        //       at javax.crypto.spec.SecretKeySpec.<init>(SecretKeySpec.java:96)
        //       at de.taimos.totp.TOTP.hmac_sha1(TOTP.java:100)
        //       at de.taimos.totp.TOTP.getOTP(TOTP.java:57)
        //       at de.taimos.totp.TOTP.getOTP(TOTP.java:26)
        //       at com.revature.overflowingStacks.user.UserServices.getTOTPCode(UserServices.java:80)
        //   In order to prevent getTOTPCode(String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getTOTPCode(String).
        //   See https://diff.blue/R013 to resolve this issue.

        userServices.getTOTPCode("-----------");
    }

    /**
     * Method under test: {@link UserServices#getTOTPCode(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTOTPCode3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Empty key
        //       at javax.crypto.spec.SecretKeySpec.<init>(SecretKeySpec.java:96)
        //       at de.taimos.totp.TOTP.hmac_sha1(TOTP.java:100)
        //       at de.taimos.totp.TOTP.getOTP(TOTP.java:57)
        //       at de.taimos.totp.TOTP.getOTP(TOTP.java:26)
        //       at com.revature.overflowingStacks.user.UserServices.getTOTPCode(UserServices.java:80)
        //   In order to prevent getTOTPCode(String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getTOTPCode(String).
        //   See https://diff.blue/R013 to resolve this issue.

        userServices.getTOTPCode("");
    }

    /**
     * Method under test: {@link UserServices#getTOTPCode(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTOTPCode4() {
        // TODO: Complete this test.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests which were time-sensitive.
        //   The assertions no longer passed when run at an alternate date, time and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   Please see https://diff.blue/R031

        userServices.getTOTPCode("42");
    }

    /**
     * Method under test: {@link UserServices#getTOTPCode(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTOTPCode5() {
        // TODO: Complete this test.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests which were time-sensitive.
        //   The assertions no longer passed when run at an alternate date, time and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   Please see https://diff.blue/R031

        userServices.getTOTPCode("com.revature.overflowingStacks.user.User");
    }

    /**
     * Method under test: {@link UserServices#getTOTPCode(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTOTPCode6() {
        // TODO: Complete this test.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests which were time-sensitive.
        //   The assertions no longer passed when run at an alternate date, time and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   Please see https://diff.blue/R031

        userServices.getTOTPCode("com.revature.overflowingStacks.user.UserSecret");
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
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
        assertTrue(userServices.validateInput(user));
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput2() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertTrue(userServices.validateInput(user));
        verify(user, atLeast(1)).getDob();
        verify(user, atLeast(1)).getEmail();
        verify(user, atLeast(1)).getPassword();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user, atLeast(1)).getUsername();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput3() {
        User user = mock(User.class);
        when(user.getDob()).thenThrow(new ResourcePersistanceException("An error occurred"));
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertThrows(ResourcePersistanceException.class, () -> userServices.validateInput(user));
        verify(user).getDob();
        verify(user, atLeast(1)).getEmail();
        verify(user, atLeast(1)).getPassword();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user, atLeast(1)).getUsername();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput4() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user, atLeast(1)).getDob();
        verify(user, atLeast(1)).getEmail();
        verify(user, atLeast(1)).getPassword();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user, atLeast(1)).getUsername();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput5() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn(null);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user).getDob();
        verify(user, atLeast(1)).getEmail();
        verify(user, atLeast(1)).getPassword();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user, atLeast(1)).getUsername();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput6() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn("");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user, atLeast(1)).getEmail();
        verify(user, atLeast(1)).getPassword();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user, atLeast(1)).getUsername();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput7() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn(null);
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user, atLeast(1)).getEmail();
        verify(user).getPassword();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user, atLeast(1)).getUsername();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput8() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user, atLeast(1)).getEmail();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user, atLeast(1)).getUsername();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput9() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn(null);
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user, atLeast(1)).getEmail();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user).getUsername();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput10() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user, atLeast(1)).getEmail();
        verify(user, atLeast(1)).getPhoneNumber();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput11() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn(null);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user, atLeast(1)).getEmail();
        verify(user).getPhoneNumber();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput12() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn("");
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user, atLeast(1)).getEmail();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#validateInput(User)}
     */
    @Test
    void testValidateInput13() {
        User user = mock(User.class);
        when(user.getDob()).thenReturn("Dob");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getPhoneNumber()).thenReturn("4105551212");
        when(user.getEmail()).thenReturn(null);
        doNothing().when(user).setDob((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFname((String) any());
        doNothing().when(user).setLname((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setSecret((String) any());
        doNothing().when(user).setUsername((String) any());
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        assertFalse(userServices.validateInput(user));
        verify(user).getEmail();
        verify(user).setDob((String) any());
        verify(user).setEmail((String) any());
        verify(user).setFname((String) any());
        verify(user).setLname((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setSecret((String) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServices#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.authenticateUser((String) any(), (String) any())).thenReturn(ofResult);
        assertSame(user, userServices.authenticateUser("jane.doe@example.org", "iloveyou"));
        verify(userDao).authenticateUser((String) any(), (String) any());
    }

    /**
     * Method under test: {@link UserServices#authenticateUser(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticateUser2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.revature.overflowingStacks.user.UserServices.authenticateUser(UserServices.java:101)
        //   In order to prevent authenticateUser(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   authenticateUser(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(userDao.authenticateUser((String) any(), (String) any())).thenReturn(null);
        userServices.authenticateUser("jane.doe@example.org", "iloveyou");
    }

    /**
     * Method under test: {@link UserServices#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser3() {
        when(userDao.authenticateUser((String) any(), (String) any())).thenReturn(Optional.empty());
        assertThrows(AuthenticationException.class,
                () -> userServices.authenticateUser("jane.doe@example.org", "iloveyou"));
        verify(userDao).authenticateUser((String) any(), (String) any());
    }

    /**
     * Method under test: {@link UserServices#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser4() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.authenticateUser((String) any(), (String) any())).thenReturn(ofResult);
        assertThrows(InvalidRequestException.class, () -> userServices.authenticateUser("", "iloveyou"));
    }

    /**
     * Method under test: {@link UserServices#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser5() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.authenticateUser((String) any(), (String) any())).thenReturn(ofResult);
        assertThrows(InvalidRequestException.class, () -> userServices.authenticateUser(null, "iloveyou"));
    }

    /**
     * Method under test: {@link UserServices#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser6() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.authenticateUser((String) any(), (String) any())).thenReturn(ofResult);
        assertThrows(InvalidRequestException.class, () -> userServices.authenticateUser("jane.doe@example.org", ""));
    }

    /**
     * Method under test: {@link UserServices#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser7() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.authenticateUser((String) any(), (String) any())).thenReturn(ofResult);
        assertThrows(InvalidRequestException.class, () -> userServices.authenticateUser("jane.doe@example.org", null));
    }

    /**
     * Method under test: {@link UserServices#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser8() {
        when(userDao.authenticateUser((String) any(), (String) any()))
                .thenThrow(new ResourcePersistanceException("An error occurred"));
        assertThrows(ResourcePersistanceException.class,
                () -> userServices.authenticateUser("jane.doe@example.org", "iloveyou"));
        verify(userDao).authenticateUser((String) any(), (String) any());
    }
}

