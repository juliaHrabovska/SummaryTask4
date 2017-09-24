package ua.nure.hrabovska.SummaryTask4.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * Send mail service
 *
 * @author Y. Hrabovska
 *
 */
public class MailSender {

    private static final Logger LOG = Logger.getLogger(MailSender.class);

    private static final String EMAIL = "julia070796@gmail.com";
    private static final String PASSWORD = "x0ADrp2R";

    /**
     * Sends mail to the toEmail address
     * @param toEmail address of user
     * @return true if message was sent, false otherwise
     */

    public static boolean sendMessage(String toEmail) {
        boolean isSent = false;
        LOG.debug("Sending message starts");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });
        session.setDebug(true);
        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(EMAIL));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            mimeMessage.setSubject("Order of admission");
            mimeMessage.setContent("Congratulations! You have been credited. Wait for further information.",
                    "text/html; charset=utf-8");

            Transport.send(mimeMessage);
            isSent = true;
        } catch (MessagingException e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.debug("Sending message ends");
        return isSent;
    }
}
