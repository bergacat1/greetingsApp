package cat.udl.eps.softarch.hello.model;

import javax.persistence.Entity;
import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Entity
public class Thunderstorm extends Observable implements Weather {


    public static final Thunderstorm thunderstorm = new Thunderstorm();

    private Thunderstorm(){}

}
