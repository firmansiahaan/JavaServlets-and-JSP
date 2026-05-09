package murach.util;

import java.util.Date;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class MailUtilGmail {

    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML)
            throws MessagingException {
        
        // 1 - get a mail session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            	return new PasswordAuthentication(EmailUtil.getUser(), EmailUtil.getPassword());
            }
          };
          
        Session session = Session.getInstance(props, authenticator);
        session.setDebug(true);

        // 2 - create a message
        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        MimeMultipart mp = new MimeMultipart();
        MimeBodyPart mbp = new MimeBodyPart();
        if (bodyIsHTML) {
        	mbp.setContent(body, "text/html");
        } else {
        	mbp.setText(body, "us-ascii");
        }
        mp.addBodyPart(mbp);
        message.setContent(mp);

        // 3 - address the message
        InternetAddress fromAddress = new InternetAddress(from);
        InternetAddress toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        message.setSentDate(new Date());

        // 4 - send the message
//        Transport transport = session.getTransport();
//        transport.connect(EmailUtil.getUser(), EmailUtil.getPassword());
//        transport.sendMessage(message, message.getAllRecipients());
//        transport.close();
        
         Transport.send(message);
    }
}