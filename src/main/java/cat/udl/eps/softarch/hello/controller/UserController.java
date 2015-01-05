package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.Alert;
import cat.udl.eps.softarch.hello.model.User;
import cat.udl.eps.softarch.hello.repository.AlertRepository;
import cat.udl.eps.softarch.hello.repository.UserRepository;
import cat.udl.eps.softarch.hello.service.UserAlertsService;
import cat.udl.eps.softarch.hello.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by http://rhizomik.net/~roberto/
 */

@Controller
@RequestMapping(value = "/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    AlertRepository alertRepository;
    @Autowired
    UserAlertsService userAlertsService;

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

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView usersHTML() {
        return new ModelAndView("users");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public void createUserIfNotExists(@RequestParam("username") String username, @RequestParam("email") String email) {
        logger.info("Retrieving user: " + username);
        User user = userRepository.findOne(username);
        if (user == null){
            user = new User(username, email);
            logger.info("New user: " + username + email);
            userRepository.save(user);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String userHTML(@RequestParam("username") String username, @RequestParam("email") String email) {
        logger.info("Redirecting user: " + username);
        createUserIfNotExists(username, email);
        return "redirect:/users/" + username;
    }



    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    @ResponseBody
    public User retrieveUser(@PathVariable("user") String username) {
        logger.info("Retrieving user: " + username);
        User user = userRepository.findOne(username);
        return user;
    }
    @RequestMapping(value = "/{user}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView usersHTML(@PathVariable("user") String username) {
        User user = retrieveUser(username);
        logger.info("Retrieved user: " + user.getName());
        return new ModelAndView("user", "user", user);
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void saveAlert(@PathVariable("user") String username, @RequestParam("email") String email,
                       @RequestParam("region") String region, @RequestParam("weather") String weather) {
        logger.info("Creating alert:" + username + region + weather);
        User user = userRepository.findOne(username);
        Alert newAlert = new Alert(user, weathers.get(weather), region, WeatherController.regions.get(region));
        userAlertsService.addAlertToUser(newAlert);
        logger.info(("ALERTA GUARDADA????????????????????????" + userRepository.findOne(newAlert.getUser().getName()).getAlerts().toString()));
        /*alertRepository.save(newAlert);
        user.addAlert(newAlert);
        logger.info("USER ALERTS:::::::::::::::::::::::::" + user.getAlerts().toString());
        userRepository.save(user);
        logger.info("ALERT!!!!!!!!!!!!!!!!!!!!!!" + userRepository.findOne(username).getAlerts().toString());*/
    }
    @RequestMapping(value = "/{user}", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String reloadHTML(@PathVariable("user") String username, @RequestParam("email") String email,
                             @RequestParam("region") String region, @RequestParam("weather") String weather) {
        saveAlert(username, email, region, weather);
        return "redirect:/users/" + username;
    }




}
