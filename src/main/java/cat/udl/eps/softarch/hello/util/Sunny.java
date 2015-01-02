package cat.udl.eps.softarch.hello.util;

import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Sunny extends Observable implements Weather {
    private static final String name = "Solejat";
    private static final String img = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkZfjxP78i1XOVE5rmRW3VBzy4WpxrPLUW34r3CLWG2K6-B_zsXw";


    public static final Sunny sunny = new Sunny();

    private Sunny(){}

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
        return sunny;
    }
}
