package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.util.Weather;
import cat.udl.eps.softarch.hello.util.XQueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by http://rhizomik.net/~roberto/
 */

@Controller
@RequestMapping(value = "/")
public class WeatherController {
    final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    public static Weather sunny = new Weather("Sol", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkZfjxP78i1XOVE5rmRW3VBzy4WpxrPLUW34r3CLWG2K6-B_zsXw");
    public static Weather cloudy = new Weather("Ennubolat", "http://www.factoryfy.com/wp-content/uploads/2013/04/nubeilustradora1.jpg");
    public static Weather foggy = new Weather("Boira", "http://www.clipartbest.com/cliparts/4cb/oRo/4cboRoMcg.jpeg");
    public static Weather rain = new Weather("Pluja", "http://us.123rf.com/450wm/lineartestpilot/lineartestpilot1408/lineartestpilot140802312/30780785-nube-de-lluvia-de-dibujos-animados.jpg");
    public static Weather snow = new Weather("Neu", "http://franciscoponce.com/wp-content/uploads/2007/12/Nieve_y_Navidad.jpg");
    public static Weather snowThunderstorm = new Weather("Tempesta de neu", "http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2013/2/6/1360155536870/Thundersnow-will-this-bec-011.jpg");
    public static Weather sunnyAndCloudy = new Weather("Sol i nubols", "http://thumb7.shutterstock.com/display_pic_with_logo/540784/104489459/stock-photo-sun-character-and-cloud-as-a-symbol-of-sunny-weather-peeking-through-a-cloudy-sky-showing-fun-104489459.jpg");
    public static Weather thunderstorm = new Weather("Tempesta", "http://previews.123rf.com/images/bruno1998/bruno19981105/bruno1998110500022/9517548-cartoon-illustration-showing-an-angry-dark-cloud-with-lightning-coming-out-of-it.jpg");


    public static final Map<String, Integer> regions = new HashMap<String, Integer>(){
        {
            put("Alt Camp",1);
            put("Alt Emporda",2);
            put("Alt Penedes",3);
            put("Alt Urgell",4);
            put("Alta Ribagorça",5);
            put("Anoia",6);
            put("Bages",7);
            put("Baix Camp",8);
            put("Baix Ebre",9);
            put("Baix Emporda",10);
            put("Baix Llobregat",11);
            put("Baix Penedes",12);
            put("Barcelones",13);
            put("Bergueda",14);
            put("La Cerdanya",15);
            put("Conca de barbera",16);
            put("Garraf",17);
            put("Garrigues",18);
            put("Garrotxa",19);
            put("Girones",20);
            put("Maresme",21);
            put("Montsia",22);
            put("Noguera",23);
            put("Osona",24);
            put("Pallars Jussa",25);
            put("Pallars Sobira",26);
            put("Pla Estany",27);
            put("Pla Urgell",28);
            put("Priorat",29);
            put("Ribera Ebre",30);
            put("Ripolles",31);
            put("Segarra",32);
            put("Segria",33);
            put("Selva",34);
            put("Solsolnes",35);
            put("Tarragones",36);
            put("Terra Alta",37);
            put("Urgell",38);
            put("Vall Aran",39);
            put("Valles Occidental",40);
            put("Valles Oriental",41);
        }
    };

    public static final Map<Integer, Weather> weathers = new HashMap<Integer, Weather>(){
        {
            put(1, sunny);
            put(2, sunny);
            put(3, sunnyAndCloudy);
            put(4, cloudy);
            put(5, rain);
            put(6, rain);
            put(7, rain);
            put(8, thunderstorm);
            put(9, thunderstorm);
            put(10, snow);
            put(11, foggy);
            put(12, foggy);
            put(13, snow);
            put(20, cloudy);
            put(21, cloudy);
            put(22, sunny);
            put(23, rain);
            put(24, thunderstorm);
            put(25, rain);
            put(26, rain);
            put(27, snow);
            put(28, snowThunderstorm);
            put(29, rain);
            put(30, rain);
            put(31, rain);
            put(32, rain);
        }


    };
    // HOME
    @RequestMapping(method=RequestMethod.GET, produces="text/html")
    public ModelAndView homeHTML() {
        return new ModelAndView("home");
    }


// LIST
    @RequestMapping(value = "/regions", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<String> list(@RequestParam(required=false, defaultValue="0") int page,
                                   @RequestParam(required=false, defaultValue="10") int size) {
        PageRequest request = new PageRequest(page, size);
        return regions.keySet();
    }
    @RequestMapping(value = "/regions", method=RequestMethod.GET, produces="text/html")
    public ModelAndView listHTML(@RequestParam(required=false, defaultValue="0") int page,
                                 @RequestParam(required=false, defaultValue="10") int size) {
        return new ModelAndView("regions", "regions", list(page, size));
    }

// RETRIEVE
    @RequestMapping(value = "/regions/{comarca}", method = RequestMethod.GET )
    @ResponseBody
    public List retrieve(@PathVariable( "comarca" ) String comarca) {
        logger.info("Retrieving greeting number {}", comarca);
        int regionWeather;
        try {
            regionWeather = XQueryHelper.getRegionWeather(regions.get(comarca));
            Weather weather = weathers.get(regionWeather);
            return Arrays.asList(comarca, weather.getName(), weather.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/regions/{comarca}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "comarca" ) String comarca)  {
        if(regions.get(comarca)==null) throw new RegionNotFoundException();
        return new ModelAndView("region", "region", retrieve(comarca));
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Region")  // 404
    public class RegionNotFoundException extends RuntimeException {

    }
}
