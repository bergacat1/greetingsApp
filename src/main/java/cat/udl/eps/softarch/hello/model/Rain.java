package cat.udl.eps.softarch.hello.model;

import javax.persistence.Entity;
import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Rain extends Observable implements Weather {


    public static final Rain rain = new Rain();

    private Rain(){}

}
