package cat.udl.eps.softarch.hello.util;

import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class SnowThunderstorm extends Observable implements Weather {
    private static final String name = "Tempesta de neu";
    private static final String img = "http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2013/2/6/1360155536870/Thundersnow-will-this-bec-011.jpg";


    public static final SnowThunderstorm snowThunderstorm = new SnowThunderstorm();

    private SnowThunderstorm(){}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImage() {
        return img;
    }
}
