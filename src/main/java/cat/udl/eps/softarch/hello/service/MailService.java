package cat.udl.eps.softarch.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by Albert on 08/01/2015.
 */
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("weathercatapp@gmail.com");
        message.setTo(to);
        message.setSubject("Funciono");
        message.setText("Ole, ole i oleeeee!");
        mailSender.send(message);

    }
}
