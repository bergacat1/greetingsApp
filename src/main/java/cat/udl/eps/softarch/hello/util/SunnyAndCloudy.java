package cat.udl.eps.softarch.hello.util;

import java.util.Observable;

/**
 * Created by http://rhizomik.net/~roberto/
 */

public class SunnyAndCloudy extends Observable implements Weather {
    private static final String name = "Sol i nubols";
    private static final String img = "http://thumb7.shutterstock.com/display_pic_with_logo/540784/104489459/stock-photo-sun-character-and-cloud-as-a-symbol-of-sunny-weather-peeking-through-a-cloudy-sky-showing-fun-104489459.jpg";


    public static final SunnyAndCloudy sunnyAndCloudy = new SunnyAndCloudy();

    private SunnyAndCloudy(){}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImage() {
        return img;
    }
}
