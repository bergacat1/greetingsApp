package cat.udl.eps.softarch.hello.model;

import javax.persistence.Entity;
import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Sunny extends Observable implements Weather {


    public static final Sunny sunny = new Sunny();

    private Sunny(){}

}
