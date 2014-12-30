package cat.udl.eps.softarch.hello.util;

import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Cloudy extends Observable implements Weather {
    private static final String name = "Ennubolat";
    private static final String img = "http://www.factoryfy.com/wp-content/uploads/2013/04/nubeilustradora1.jpg";


    public static final Cloudy cloudy = new Cloudy();

    private Cloudy(){}


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImage() {
        return img;
    }
}
