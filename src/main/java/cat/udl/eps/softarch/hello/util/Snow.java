package cat.udl.eps.softarch.hello.util;

import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Snow extends Observable implements Weather {
    private static final String name = "Neu";
    private static final String img = "http://franciscoponce.com/wp-content/uploads/2007/12/Nieve_y_Navidad.jpg";


    public static final Snow snow = new Snow();

    private Snow(){}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImage() {
        return img;
    }

    @Override
    public Weather getInstance() {
        return snow;
    }
}
