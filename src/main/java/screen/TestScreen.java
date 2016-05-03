package screen;

import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.inject.Inject;
import javax.inject.Named;

@Named("testScreen")
@Scope("request")
public class TestScreen {

    @Inject
    private MailSender mailSender;

    public TestScreen() {
    }

    public void sendEmail(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("orbit3v1@gmail.com");
        message.setTo("duke007@ukr.net");
        message.setSubject("test");
        message.setText("hello it's test mail from java");
        mailSender.send(message);

    }
}
