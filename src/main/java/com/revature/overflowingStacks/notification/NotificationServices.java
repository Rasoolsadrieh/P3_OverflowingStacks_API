package com.revature.overflowingStacks.notification;
import com.revature.overflowingStacks.user.User;
import com.revature.overflowingStacks.user.UserDao;
import com.revature.overflowingStacks.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@Service
@Transactional
public class NotificationServices implements Serviceable<Notification> {

    private final NotificationDao notificationDao;
    private final UserDao userDao;

    @Autowired
    public NotificationServices(NotificationDao notificationDao, UserDao userDao) {
        this.notificationDao = notificationDao;
        this.userDao = userDao;
    }

    public void sendEmailTest() {
        String from = "overflowing-stacks@outlook.com";
        String password = "SyntaxError-JARS";
        String to = "maxwellmoord@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp-mail.outlook.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp-mail.outlook.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            message.addHeader("This is the Header", "This is where it is located");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


    @Override
    public Notification create(Notification newNotification){ return notificationDao.save(newNotification);}

    @Override
    public Notification update(Notification updatedNotification) { return null;}

    @Override
    public Notification readById(String movieId) {return null;}

    @Override
    public List<Notification> readAll() {return null;}

    public List<Notification> readByEmail(User email) {return notificationDao.notificationHistory(email);}

    @Override
    public boolean delete(String notiId) {
        notificationDao.deleteById(notiId);
        return true;
    }

    @Override
    public boolean validateInput(Notification notification){return false;}




}
