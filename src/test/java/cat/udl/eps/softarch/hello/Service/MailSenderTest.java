package cat.udl.eps.softarch.hello.Service;

import cat.udl.eps.softarch.hello.service.MailService;
import org.junit.Test;

/**
 * Created by Albert on 08/01/2015.
 */
public class MailSenderTest {
    MailService ms = new MailService();

    @Test
    public void test(){
        ms.sendMail("albertbergagatius@gmail.com");
        assert(true);
    }
}
