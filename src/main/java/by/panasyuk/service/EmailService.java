package by.panasyuk.service;

import by.panasyuk.service.exception.ServiceException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EmailService {
    private final String username = "pharma.task007@gmail.com";
    private final String password = "27.02.pharma.Task007";
    private Properties props;
    private static EmailService instance;
    private static Lock lockForSingleTone = new ReentrantLock();

    public static EmailService getInstance() {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                instance = new EmailService();
            }

        } finally {
            lockForSingleTone.unlock();
        }

        return instance;
    }


    private EmailService() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
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
