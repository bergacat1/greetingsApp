package cat.udl.eps.softarch.hello.controller;

import java.util.HashMap;
import java.util.Map;

import cat.udl.eps.softarch.hello.model.*;
import cat.udl.eps.softarch.hello.repository.GreetingRepository;
//import cat.udl.eps.softarch.hello.util.XQueryHelper;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by http://rhizomik.net/~roberto/
 */

@Controller
@RequestMapping(value = "/")
public class WeatherController {
    final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired GreetingRepository greetingRepository;

    private static final Map<String, Integer> regions = new HashMap<String, Integer>(){
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

    private static final Map<Integer, Weather> weathers = new HashMap<Integer, Weather>(){
        {
            put(1, Sunny.sunny);
            put(2, Sunny.sunny);
            put(3, SunnyAndCloudy.sunnyAndCloudy);
            put(4, Cloudy.cloudy);
            put(5, Rain.rain);
            put(6, Rain.rain);
            put(7, Rain.rain);
            put(8, Thunderstorm.thunderstorm);
            put(9, Thunderstorm.thunderstorm);
            put(10, Snow.snow);
            put(11, Foggy.foggy);
            put(12, Foggy.foggy);
            put(13, Snow.snow);
            put(20, Cloudy.cloudy);
            put(21, Cloudy.cloudy);
            put(22, Sunny.sunny);
            put(23, Rain.rain);
            put(24, Thunderstorm.thunderstorm);
            put(25, Rain.rain);
            put(26, Rain.rain);
            put(27, Snow.snow);
            put(28, SnowThunderstorm.snowThunderstorm);
            put(29, Rain.rain);
            put(30, Rain.rain);
            put(31, Rain.rain);
            put(32, Rain.rain);
        }


    };


// LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<String> list(@RequestParam(required=false, defaultValue="0") int page,
                                   @RequestParam(required=false, defaultValue="10") int size) {
        PageRequest request = new PageRequest(page, size);
        return regions.keySet();
    }
    @RequestMapping(method=RequestMethod.GET, produces="text/html")
    public ModelAndView listHTML(@RequestParam(required=false, defaultValue="0") int page,
                                 @RequestParam(required=false, defaultValue="10") int size) {

        //XQueryHelper.prova();

        return new ModelAndView("regions", "regions", list(page, size));
    }

// RETRIEVE
    @RequestMapping(value = "/{comarca}", method = RequestMethod.GET )
    @ResponseBody
    public String retrieve(@PathVariable( "comarca" ) String comarca) {
        logger.info("Retrieving greeting number {}", comarca);
        return comarca;
    }
    @RequestMapping(value = "/{comarca}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "comarca" ) String comarca) {
        return new ModelAndView("region", "region", retrieve(comarca));
    }


}
