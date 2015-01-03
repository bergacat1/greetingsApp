package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.Alert;
import cat.udl.eps.softarch.hello.model.User;
import cat.udl.eps.softarch.hello.repository.AlertRepository;
import cat.udl.eps.softarch.hello.repository.UserRepository;
import cat.udl.eps.softarch.hello.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/user")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    AlertRepository alertRepository;

    private static final Map<String, Weather> weathers = new HashMap<String, Weather>(){
        {
            put("Sunny", Sunny.sunny);
            put("SunnyAndCloudy", SunnyAndCloudy.sunnyAndCloudy);
            put("Cloudy", Cloudy.cloudy);
            put("Rain", Rain.rain);
            put("Thunderstorm", Thunderstorm.thunderstorm);
            put("Snow", Snow.snow);
            put("Foggy", Foggy.foggy);
            put("SnowThunderstorm", SnowThunderstorm.snowThunderstorm);
        }


    };

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public User retrieve(@RequestParam("email") String email) {
        logger.info("Retrieving user: " + email);
        User user = userRepository.findOne(email);
        if (user == null){
            logger.info("New user: " + email);
            user = new User(email);
            userRepository.save(user);
        }
        logger.info("User alerts: " + user.alerts.toString());
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHTML(@RequestParam("email") String email) {
        return new ModelAndView("user", "user", retrieve(email));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestParam("email") String email, @RequestParam("region") String region, @RequestParam("weather") String weather) {
        logger.info("Creating alert");
        User user = userRepository.findOne(email);
        Alert newAlert = new Alert(user, weathers.get(weather), region, WeatherController.regions.get(region));
        alertRepository.save(newAlert);
        user.addAlert(newAlert);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String createHTML(@RequestParam("email") String email, @RequestParam("region") String region, @RequestParam("weather") String weather) {
        create(email, region, weather);
        return "redirect:/";
    }




}
