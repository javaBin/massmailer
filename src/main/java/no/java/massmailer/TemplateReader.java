package no.java.massmailer;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;

import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;

/**
 * Reads a template and merges with data.
 */
public class TemplateReader {

    /**
     * Reads the template and merges with the evaluations passed as parameters
     * @param fileName the name of the templatefile to render.
     * @param  personalizedInfos a list of evaluations to send feedback for.
     * @return a list of messages to send.
     */
    public List<MailMsg> render(String fileName, List<PersonalizedInfo> personalizedInfos) {

        List<MailMsg> mailMessages = new ArrayList<MailMsg>();
        try {
            Velocity.init();
            VelocityContext context = new VelocityContext();
            Template template = Velocity.getTemplate(fileName);

            for (PersonalizedInfo info : personalizedInfos) {
                context.put("person", info.getPerson());
                context.put("data", info.getData());
                StringWriter sw = new StringWriter();
                template.merge(context, sw);

                System.out.println("Template" + sw.toString());
                mailMessages.add(new MailMsg(info.getPerson(), sw.toString()));
            }

        } catch (Exception rnfx) {
            rnfx.printStackTrace();
            throw new RuntimeException(rnfx.getMessage());
        }
          return mailMessages;
    }

}
