package com.revature.overflowingStacks.profile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.overflowingStacks.user.User;
import com.revature.overflowingStacks.util.exceptions.InvalidRequestException;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProfileServices.class})
@ExtendWith(SpringExtension.class)
class ProfileServicesTest {
    @MockBean
    private ProfileDao profileDao;

    @Autowired
    private ProfileServices profileServices;

    /**
     * Method under test: {@link ProfileServices#validateProfilenameNotUsed(String)}
     */
    @Test
    void testValidateProfilenameNotUsed() {
        when(profileDao.existsById((String) any())).thenReturn(true);
        assertTrue(profileServices.validateProfilenameNotUsed("foo.txt"));
        verify(profileDao).existsById((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateProfilenameNotUsed(String)}
     */
    @Test
    void testValidateProfilenameNotUsed2() {
        when(profileDao.existsById((String) any())).thenReturn(false);
        assertFalse(profileServices.validateProfilenameNotUsed("foo.txt"));
        verify(profileDao).existsById((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateProfilenameNotUsed(String)}
     */
    @Test
    void testValidateProfilenameNotUsed3() {
        when(profileDao.existsById((String) any())).thenThrow(new InvalidRequestException("An error occurred"));
        assertThrows(InvalidRequestException.class, () -> profileServices.validateProfilenameNotUsed("foo.txt"));
        verify(profileDao).existsById((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#create(Profile)}
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

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        when(profileDao.existsById((String) any())).thenReturn(true);
        when(profileDao.save((Profile) any())).thenReturn(profile);

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
        assertThrows(InvalidRequestException.class, () -> profileServices.create(profile1));
        verify(profileDao).existsById((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#create(Profile)}
     */
    @Test
    void testCreate2() {
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
        when(profileDao.existsById((String) any())).thenReturn(false);
        when(profileDao.save((Profile) any())).thenReturn(profile);

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
        assertSame(profile, profileServices.create(profile1));
        verify(profileDao).existsById((String) any());
        verify(profileDao).save((Profile) any());
    }

    /**
     * Method under test: {@link ProfileServices#create(Profile)}
     */
    @Test
    void testCreate3() {
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
        when(profileDao.existsById((String) any())).thenReturn(true);
        when(profileDao.save((Profile) any())).thenReturn(profile);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");

        User user2 = new User();
        user2.setDob("Dob");
        user2.setEmail("jane.doe@example.org");
        user2.setFname("Fname");
        user2.setLname("Lname");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setSecret("Secret");
        user2.setUsername("janedoe");
        Profile profile1 = mock(Profile.class);
        when(profile1.getAccountNumber()).thenThrow(new InvalidRequestException("An error occurred"));
        when(profile1.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile1.getBalance()).thenReturn(10.0d);
        when(profile1.getEmail()).thenReturn(user2);
        when(profile1.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile1).setAccountName((String) any());
        doNothing().when(profile1).setAccountNumber((String) any());
        doNothing().when(profile1).setBalance(anyDouble());
        doNothing().when(profile1).setEmail((User) any());
        doNothing().when(profile1).setProfileName((String) any());
        profile1.setAccountName("Dr Jane Doe");
        profile1.setAccountNumber("42");
        profile1.setBalance(10.0d);
        profile1.setEmail(user1);
        profile1.setProfileName("foo.txt");
        assertThrows(InvalidRequestException.class, () -> profileServices.create(profile1));
        verify(profile1, atLeast(1)).getEmail();
        verify(profile1).getBalance();
        verify(profile1, atLeast(1)).getAccountName();
        verify(profile1).getAccountNumber();
        verify(profile1, atLeast(1)).getProfileName();
        verify(profile1).setAccountName((String) any());
        verify(profile1).setAccountNumber((String) any());
        verify(profile1).setBalance(anyDouble());
        verify(profile1).setEmail((User) any());
        verify(profile1).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#create(Profile)}
     */
    @Test
    void testCreate4() {
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
        when(profileDao.existsById((String) any())).thenReturn(true);
        when(profileDao.save((Profile) any())).thenReturn(profile);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");

        User user2 = new User();
        user2.setDob("Dob");
        user2.setEmail("jane.doe@example.org");
        user2.setFname("Fname");
        user2.setLname("Lname");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setSecret("Secret");
        user2.setUsername("janedoe");
        Profile profile1 = mock(Profile.class);
        when(profile1.getAccountNumber()).thenReturn("42");
        when(profile1.getAccountName()).thenReturn("");
        when(profile1.getBalance()).thenReturn(10.0d);
        when(profile1.getEmail()).thenReturn(user2);
        when(profile1.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile1).setAccountName((String) any());
        doNothing().when(profile1).setAccountNumber((String) any());
        doNothing().when(profile1).setBalance(anyDouble());
        doNothing().when(profile1).setEmail((User) any());
        doNothing().when(profile1).setProfileName((String) any());
        profile1.setAccountName("Dr Jane Doe");
        profile1.setAccountNumber("42");
        profile1.setBalance(10.0d);
        profile1.setEmail(user1);
        profile1.setProfileName("foo.txt");
        assertThrows(InvalidRequestException.class, () -> profileServices.create(profile1));
        verify(profile1, atLeast(1)).getEmail();
        verify(profile1).getBalance();
        verify(profile1, atLeast(1)).getAccountName();
        verify(profile1, atLeast(1)).getProfileName();
        verify(profile1).setAccountName((String) any());
        verify(profile1).setAccountNumber((String) any());
        verify(profile1).setBalance(anyDouble());
        verify(profile1).setEmail((User) any());
        verify(profile1).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#create(Profile)}
     */
    @Test
    void testCreate5() {
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
        when(profileDao.existsById((String) any())).thenReturn(true);
        when(profileDao.save((Profile) any())).thenReturn(profile);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");

        User user2 = new User();
        user2.setDob("Dob");
        user2.setEmail("jane.doe@example.org");
        user2.setFname("Fname");
        user2.setLname("Lname");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setSecret("Secret");
        user2.setUsername("janedoe");
        Profile profile1 = mock(Profile.class);
        when(profile1.getAccountNumber()).thenReturn("42");
        when(profile1.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile1.getBalance()).thenReturn(-0.5d);
        when(profile1.getEmail()).thenReturn(user2);
        when(profile1.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile1).setAccountName((String) any());
        doNothing().when(profile1).setAccountNumber((String) any());
        doNothing().when(profile1).setBalance(anyDouble());
        doNothing().when(profile1).setEmail((User) any());
        doNothing().when(profile1).setProfileName((String) any());
        profile1.setAccountName("Dr Jane Doe");
        profile1.setAccountNumber("42");
        profile1.setBalance(10.0d);
        profile1.setEmail(user1);
        profile1.setProfileName("foo.txt");
        assertThrows(InvalidRequestException.class, () -> profileServices.create(profile1));
        verify(profile1, atLeast(1)).getEmail();
        verify(profile1).getBalance();
        verify(profile1, atLeast(1)).getProfileName();
        verify(profile1).setAccountName((String) any());
        verify(profile1).setAccountNumber((String) any());
        verify(profile1).setBalance(anyDouble());
        verify(profile1).setEmail((User) any());
        verify(profile1).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#create(Profile)}
     */
    @Test
    void testCreate6() {
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
        when(profileDao.existsById((String) any())).thenReturn(true);
        when(profileDao.save((Profile) any())).thenReturn(profile);

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        User user2 = mock(User.class);
        doNothing().when(user2).setDob((String) any());
        doNothing().when(user2).setEmail((String) any());
        doNothing().when(user2).setFname((String) any());
        doNothing().when(user2).setLname((String) any());
        doNothing().when(user2).setPassword((String) any());
        doNothing().when(user2).setPhoneNumber((String) any());
        doNothing().when(user2).setSecret((String) any());
        doNothing().when(user2).setUsername((String) any());
        user2.setDob("Dob");
        user2.setEmail("jane.doe@example.org");
        user2.setFname("Fname");
        user2.setLname("Lname");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setSecret("Secret");
        user2.setUsername("janedoe");
        Profile profile1 = mock(Profile.class);
        when(profile1.getAccountNumber()).thenReturn("42");
        when(profile1.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile1.getBalance()).thenReturn(10.0d);
        when(profile1.getEmail()).thenReturn(user2);
        when(profile1.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile1).setAccountName((String) any());
        doNothing().when(profile1).setAccountNumber((String) any());
        doNothing().when(profile1).setBalance(anyDouble());
        doNothing().when(profile1).setEmail((User) any());
        doNothing().when(profile1).setProfileName((String) any());
        profile1.setAccountName("Dr Jane Doe");
        profile1.setAccountNumber("42");
        profile1.setBalance(10.0d);
        profile1.setEmail(user1);
        profile1.setProfileName("foo.txt");
        assertThrows(InvalidRequestException.class, () -> profileServices.create(profile1));
        verify(profileDao).existsById((String) any());
        verify(profile1, atLeast(1)).getEmail();
        verify(profile1).getBalance();
        verify(profile1, atLeast(1)).getAccountName();
        verify(profile1).getAccountNumber();
        verify(profile1, atLeast(1)).getProfileName();
        verify(profile1).setAccountName((String) any());
        verify(profile1).setAccountNumber((String) any());
        verify(profile1).setBalance(anyDouble());
        verify(profile1).setEmail((User) any());
        verify(profile1).setProfileName((String) any());
        verify(user2).setDob((String) any());
        verify(user2).setEmail((String) any());
        verify(user2).setFname((String) any());
        verify(user2).setLname((String) any());
        verify(user2).setPassword((String) any());
        verify(user2).setPhoneNumber((String) any());
        verify(user2).setSecret((String) any());
        verify(user2).setUsername((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#readAll()}
     */
    @Test
    void testReadAll() {
        when(profileDao.findAll()).thenThrow(new InvalidRequestException("An error occurred"));
        assertThrows(InvalidRequestException.class, () -> profileServices.readAll());
        verify(profileDao).findAll();
    }

    /**
     * Method under test: {@link ProfileServices#readById(String)}
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

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        Optional<Profile> ofResult = Optional.of(profile);
        when(profileDao.findById((String) any())).thenReturn(ofResult);
        assertSame(profile, profileServices.readById("42"));
        verify(profileDao).findById((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#readById(String)}
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
        //       at com.revature.overflowingStacks.profile.ProfileServices.readById(ProfileServices.java:54)
        //   In order to prevent readById(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   readById(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(profileDao.findById((String) any())).thenReturn(Optional.empty());
        profileServices.readById("42");
    }

    /**
     * Method under test: {@link ProfileServices#readById(String)}
     */
    @Test
    void testReadById3() {
        when(profileDao.findById((String) any())).thenThrow(new InvalidRequestException("An error occurred"));
        assertThrows(InvalidRequestException.class, () -> profileServices.readById("42"));
        verify(profileDao).findById((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#delete(String)}
     */
    @Test
    void testDelete() {
        doNothing().when(profileDao).deleteById((String) any());
        assertTrue(profileServices.delete("foo.txt"));
        verify(profileDao).deleteById((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#delete(String)}
     */
    @Test
    void testDelete2() {
        doThrow(new InvalidRequestException("An error occurred")).when(profileDao).deleteById((String) any());
        assertThrows(InvalidRequestException.class, () -> profileServices.delete("foo.txt"));
        verify(profileDao).deleteById((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#update(Profile)}
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

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        when(profileDao.save((Profile) any())).thenReturn(profile);

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
        assertSame(profile1, profileServices.update(profile1));
        verify(profileDao).save((Profile) any());
    }

    /**
     * Method under test: {@link ProfileServices#update(Profile)}
     */
    @Test
    void testUpdate2() {
        when(profileDao.save((Profile) any())).thenThrow(new InvalidRequestException("An error occurred"));

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
        assertThrows(InvalidRequestException.class, () -> profileServices.update(profile));
        verify(profileDao).save((Profile) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
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

        Profile profile = new Profile();
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertTrue(profileServices.validateInput(profile));
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
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

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenReturn("42");
        when(profile.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile.getBalance()).thenReturn(10.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertTrue(profileServices.validateInput(profile));
        verify(profile, atLeast(1)).getEmail();
        verify(profile).getBalance();
        verify(profile, atLeast(1)).getAccountName();
        verify(profile).getAccountNumber();
        verify(profile, atLeast(1)).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
     */
    @Test
    void testValidateInput3() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenThrow(new InvalidRequestException("An error occurred"));
        when(profile.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile.getBalance()).thenReturn(10.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertThrows(InvalidRequestException.class, () -> profileServices.validateInput(profile));
        verify(profile, atLeast(1)).getEmail();
        verify(profile).getBalance();
        verify(profile, atLeast(1)).getAccountName();
        verify(profile).getAccountNumber();
        verify(profile, atLeast(1)).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
     */
    @Test
    void testValidateInput4() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenReturn(null);
        when(profile.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile.getBalance()).thenReturn(10.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertFalse(profileServices.validateInput(profile));
        verify(profile, atLeast(1)).getEmail();
        verify(profile).getBalance();
        verify(profile, atLeast(1)).getAccountName();
        verify(profile).getAccountNumber();
        verify(profile, atLeast(1)).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
     */
    @Test
    void testValidateInput5() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenReturn("42");
        when(profile.getAccountName()).thenReturn("");
        when(profile.getBalance()).thenReturn(10.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertFalse(profileServices.validateInput(profile));
        verify(profile, atLeast(1)).getEmail();
        verify(profile).getBalance();
        verify(profile, atLeast(1)).getAccountName();
        verify(profile, atLeast(1)).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
     */
    @Test
    void testValidateInput6() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenReturn("42");
        when(profile.getAccountName()).thenReturn(null);
        when(profile.getBalance()).thenReturn(10.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertFalse(profileServices.validateInput(profile));
        verify(profile, atLeast(1)).getEmail();
        verify(profile).getBalance();
        verify(profile).getAccountName();
        verify(profile, atLeast(1)).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
     */
    @Test
    void testValidateInput7() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenReturn("42");
        when(profile.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile.getBalance()).thenReturn(0.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertFalse(profileServices.validateInput(profile));
        verify(profile, atLeast(1)).getEmail();
        verify(profile).getBalance();
        verify(profile, atLeast(1)).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
     */
    @Test
    void testValidateInput8() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        User user1 = mock(User.class);
        doNothing().when(user1).setDob((String) any());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setFname((String) any());
        doNothing().when(user1).setLname((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setPhoneNumber((String) any());
        doNothing().when(user1).setSecret((String) any());
        doNothing().when(user1).setUsername((String) any());
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenReturn("42");
        when(profile.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile.getBalance()).thenReturn(10.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn("foo.txt");
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertTrue(profileServices.validateInput(profile));
        verify(profile, atLeast(1)).getEmail();
        verify(profile).getBalance();
        verify(profile, atLeast(1)).getAccountName();
        verify(profile).getAccountNumber();
        verify(profile, atLeast(1)).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
        verify(user1).setDob((String) any());
        verify(user1).setEmail((String) any());
        verify(user1).setFname((String) any());
        verify(user1).setLname((String) any());
        verify(user1).setPassword((String) any());
        verify(user1).setPhoneNumber((String) any());
        verify(user1).setSecret((String) any());
        verify(user1).setUsername((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
     */
    @Test
    void testValidateInput9() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        User user1 = mock(User.class);
        doNothing().when(user1).setDob((String) any());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setFname((String) any());
        doNothing().when(user1).setLname((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setPhoneNumber((String) any());
        doNothing().when(user1).setSecret((String) any());
        doNothing().when(user1).setUsername((String) any());
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenReturn("42");
        when(profile.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile.getBalance()).thenReturn(10.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn("");
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertFalse(profileServices.validateInput(profile));
        verify(profile, atLeast(1)).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
        verify(user1).setDob((String) any());
        verify(user1).setEmail((String) any());
        verify(user1).setFname((String) any());
        verify(user1).setLname((String) any());
        verify(user1).setPassword((String) any());
        verify(user1).setPhoneNumber((String) any());
        verify(user1).setSecret((String) any());
        verify(user1).setUsername((String) any());
    }

    /**
     * Method under test: {@link ProfileServices#validateInput(Profile)}
     */
    @Test
    void testValidateInput10() {
        User user = new User();
        user.setDob("Dob");
        user.setEmail("jane.doe@example.org");
        user.setFname("Fname");
        user.setLname("Lname");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setSecret("Secret");
        user.setUsername("janedoe");
        User user1 = mock(User.class);
        doNothing().when(user1).setDob((String) any());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setFname((String) any());
        doNothing().when(user1).setLname((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setPhoneNumber((String) any());
        doNothing().when(user1).setSecret((String) any());
        doNothing().when(user1).setUsername((String) any());
        user1.setDob("Dob");
        user1.setEmail("jane.doe@example.org");
        user1.setFname("Fname");
        user1.setLname("Lname");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setSecret("Secret");
        user1.setUsername("janedoe");
        Profile profile = mock(Profile.class);
        when(profile.getAccountNumber()).thenReturn("42");
        when(profile.getAccountName()).thenReturn("Dr Jane Doe");
        when(profile.getBalance()).thenReturn(10.0d);
        when(profile.getEmail()).thenReturn(user1);
        when(profile.getProfileName()).thenReturn(null);
        doNothing().when(profile).setAccountName((String) any());
        doNothing().when(profile).setAccountNumber((String) any());
        doNothing().when(profile).setBalance(anyDouble());
        doNothing().when(profile).setEmail((User) any());
        doNothing().when(profile).setProfileName((String) any());
        profile.setAccountName("Dr Jane Doe");
        profile.setAccountNumber("42");
        profile.setBalance(10.0d);
        profile.setEmail(user);
        profile.setProfileName("foo.txt");
        assertFalse(profileServices.validateInput(profile));
        verify(profile).getProfileName();
        verify(profile).setAccountName((String) any());
        verify(profile).setAccountNumber((String) any());
        verify(profile).setBalance(anyDouble());
        verify(profile).setEmail((User) any());
        verify(profile).setProfileName((String) any());
        verify(user1).setDob((String) any());
        verify(user1).setEmail((String) any());
        verify(user1).setFname((String) any());
        verify(user1).setLname((String) any());
        verify(user1).setPassword((String) any());
        verify(user1).setPhoneNumber((String) any());
        verify(user1).setSecret((String) any());
        verify(user1).setUsername((String) any());
    }
}

