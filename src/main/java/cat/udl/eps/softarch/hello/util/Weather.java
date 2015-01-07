package cat.udl.eps.softarch.hello.util;

import javax.persistence.Entity;
import java.util.Observer;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Weather  {
    private String name;
    private String image;

    public Weather(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
