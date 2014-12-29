package cat.udl.eps.softarch.hello.model;

import javax.persistence.Entity;
import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Entity
public class SnowThunderstorm extends Observable implements Weather {


    public static final SnowThunderstorm snowThunderstorm = new SnowThunderstorm();

    private SnowThunderstorm(){}

}
