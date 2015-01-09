package cat.udl.eps.softarch.hello.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by Albert on 08/01/2015.
 */
@Service
public class MailService {
    private JavaMailSender javaMailSender;

    public MailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(25);
        javaMailSender.setUsername("weathercatapp");
        javaMailSender.setPassword("genisalbert");
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        javaMailSender.setJavaMailProperties(properties);
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String to, String region, String weather) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("weathercatapp@gmail.com");
        message.setTo(to);
        message.setSubject("Notificació WeatherCatApp");
        message.setText("Alerta de WeatherCat. \n Previsió del temps per dema: \n \t Comarca: "
        + region + "\n \t Temps: " + weather);
        javaMailSender.send(message);

    }
}
