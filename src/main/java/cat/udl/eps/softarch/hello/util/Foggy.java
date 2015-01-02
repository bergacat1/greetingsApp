package cat.udl.eps.softarch.hello.util;

import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class Foggy extends Observable implements Weather{
    private static final String name = "Boira";
    private static final String img = "http://www.clipartbest.com/cliparts/4cb/oRo/4cboRoMcg.jpeg";


    public static final Foggy foggy = new Foggy();

    private Foggy(){}

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
        return foggy;
    }
}
