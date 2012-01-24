package no.java.massmailer;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.*;
import java.util.Properties;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MailSender {

    /**
     * Sends a list of mail messages.
     * @param mailsToSend
     */
    public void send(List<MailMsg> mailsToSend) {

        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/mail.properties");
            props.load(fis);

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session session = Session.getDefaultInstance(props, new MyAuthenticator(props));
          
            for (MailMsg mailMsg : mailsToSend) {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(props.getProperty("mail.from.address") , props.getProperty("mail.from.name")));
                String copyToAddress = props.getProperty("mail.cc.address");
                if (copyToAddress != null) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(copyToAddress));
                }
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailMsg.getRecipient().getEmail()));
                message.setSubject(props.getProperty("mail.subject"));
                message.setText(mailMsg.getMessage());
                Transport.send(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * internal class used to authenticate
     */
    class MyAuthenticator extends Authenticator {

        Properties props;
        MyAuthenticator(Properties props) {
            super();
            this.props = props;
        }
        
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(props.getProperty("mail.account.username") , props.getProperty("mail.account.pw"));
        }
    }
}
