package cat.udl.eps.softarch.hello.Service;

import cat.udl.eps.softarch.hello.config.GreetingsAppContext;
import cat.udl.eps.softarch.hello.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Albert on 08/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GreetingsAppContext.class)
@WebAppConfiguration
public class MailSenderTest {
    @Autowired
    MailService ms;

    @Test
    public void test(){
        //ms.sendMail("albertbergagatius@gmail.com", "Segria", "Sol");
        assert(true);
    }
}
