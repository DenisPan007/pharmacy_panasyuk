package by.panasyuk.service;

import by.panasyuk.service.exception.ServiceException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailService {
    private final String username;
    private final String password;
    private Properties props;

    public EmailService() throws ServiceException{
        InputStream inputStream = EmailService.class.getResourceAsStream("/email_server.properties");
        props = new Properties();
        try {
            props.load(inputStream);
            username = props.getProperty("username");
            password = props.getProperty("password");
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    public void send(String subject, String text, String toEmail) throws ServiceException {
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new ServiceException(e);
        }
    }
}
