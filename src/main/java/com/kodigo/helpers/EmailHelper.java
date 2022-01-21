package com.kodigo.helpers;

import com.kodigo.repository.CustomerManagement;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailHelper {
    public void sendEmail(CustomerManagement cm, String filename){
        // For sending emails through gmail smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Recipient's email ID needs to be mentioned.
        String to = cm.getCustomer().getEmail();

        // Sender's email ID needs to be mentioned
        String from = "citigersystem@gmail.com";

        // Get the Session object.// and pass1

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("citigersystem@gmail.com", "citiger123");
            }
        });

        try {
            // create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // set Subject: header field
            message.setSubject("Your bill is here!");
            // adds the content from the return of prepareMessage()
            message.setContent(prepareMessage(filename, cm));
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public Multipart prepareMessage(String filename, CustomerManagement cm){
        // creates a Multipart object
        Multipart multipart = new MimeMultipart();

        try {
            // creates a MimeBodyPart object
            MimeBodyPart attachmentPart = new MimeBodyPart();
            // creates a MimeBodyPart object
            MimeBodyPart textPart = new MimeBodyPart();
            // creates a File object with the provided filename
            File f = new File(filename);
            // attaches the File object to the MimeBodyPart object
            attachmentPart.attachFile(f);
            // adds text to MimeBodyPart object
            textPart.setText("Hi "+cm.getCustomer().getName()+"! You can find your bill in the attached file");
            // adds both MimeBodyPart objects to Multipart main object
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

        return multipart;
    }
}
