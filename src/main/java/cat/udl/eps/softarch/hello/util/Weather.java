package cat.udl.eps.softarch.hello.util;

import javax.persistence.Entity;
import java.util.Observer;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public interface Weather  {
    public String getName();
    public String getImage();
    public void addObserver(Observer o);
    public void notifyObservers(Object arg);
}
