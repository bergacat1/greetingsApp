package cat.udl.eps.softarch.hello.util;

import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Rain extends Observable implements Weather {
    private static final String name = "Pluja";
    private static final String img = "http://us.123rf.com/450wm/lineartestpilot/lineartestpilot1408/lineartestpilot140802312/30780785-nube-de-lluvia-de-dibujos-animados.jpg";


    public static final Rain rain = new Rain();

    private Rain(){}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImage() {
        return img;
    }
}
