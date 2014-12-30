package cat.udl.eps.softarch.hello.model;

import javax.persistence.Entity;
import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Foggy extends Observable implements Weather{


    public static final Foggy foggy = new Foggy();

    private Foggy(){}

}
