package cat.udl.eps.softarch.hello.util;

import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Thunderstorm extends Observable implements Weather {
    private static final String name = "Tempesta";
    private static final String img = "http://previews.123rf.com/images/bruno1998/bruno19981105/bruno1998110500022/9517548-cartoon-illustration-showing-an-angry-dark-cloud-with-lightning-coming-out-of-it.jpg";


    public static final Thunderstorm thunderstorm = new Thunderstorm();

    private Thunderstorm(){}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImage() {
        return img;
    }
}
