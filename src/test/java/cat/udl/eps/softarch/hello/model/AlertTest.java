package cat.udl.eps.softarch.hello.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by Albert on 18/01/2015.
 */
public class AlertTest {
    private Alert alert;

    @Before
    public void setUp(){
        alert = new Alert(new User(), "", "");
    }

    @Test
    public void testChangeEnabled(){
        assertTrue(alert.enabled);
        alert.changeEnabled();
        assertFalse(alert.enabled);
        alert.changeEnabled();
        assertTrue(alert.enabled);
    }
}
