package no.java.massmailer;

import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The main class of the mass mailer application. Uses the {@link PersonalizedInfoReader} to read evaluations and then
 * the {@link TemplateReader} to read and merge the template with the data. After the mails has been rendered the massmailer
 * use the {@link MailSender} to send the evaluation results.
 */
public class MassMailer {

    /**
     * The mail class of the massmailer app.
     * @param args
     */
    public static void main(String[] args) throws IOException {

        // setup logging
        //Logger log = Logger.getLogger(MassMailer.class);
        //log.addAppender(new FileAppender(new SimpleLayout(), "massMailer.txt", false));

        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/mail.properties");
        props.load(fis);

        PersonalizedInfoReader er = new PersonalizedInfoReader();
        List result = er.readFile(props.getProperty("data"));
        TemplateReader tm = new TemplateReader( );
        List<MailMsg> mailsToSend = tm.render(props.getProperty("template"), result);

        if ("true".equals(props.getProperty("sendMail"))) {
            MailSender ms = new MailSender();
            ms.send(mailsToSend);
        }
    }
}
