package cat.udl.eps.softarch.hello.model;

import javax.persistence.Entity;
import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Entity
public class Cloudy extends Observable {


    public static final Cloudy cloudy = new Cloudy();

    private Cloudy(){}

}
