package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.User;
import cat.udl.eps.softarch.hello.repository.AlertRepository;
import cat.udl.eps.softarch.hello.repository.UserRepository;
import cat.udl.eps.softarch.hello.service.UserAlertsService;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView usersHTML() {
        return new ModelAndView("users");
    }

    @RequestMapping(method = RequestMethod.POST)
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
        Preconditions.checkArgument(!username.isEmpty(), "Heu d'indicar un nom d'usuari.");
        Preconditions.checkArgument(!email.isEmpty(), "Heu d'indicar un email");
        logger.info("Redirecting user: " + username);
        createUserIfNotExists(username, email);
        return "redirect:/users/" + username;
    }



    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    @ResponseBody
    public User retrieveUser(@PathVariable("user") String username) {
        logger.info("Retrieving user: " + username);
        User user = userAlertsService.getUserAndAlerts(username);
        Preconditions.checkNotNull(user, "L'usuari %s no s'ha trobat", username);
        return user;
    }
    @RequestMapping(value = "/{user}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView usersHTML(@PathVariable("user") String username) {
        User user = retrieveUser(username);
        logger.info("Retrieved user: " + user.getUsername());
        return new ModelAndView("user", "user", user);
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void saveAlert(@PathVariable("user") String username,
                       @RequestParam("region") String region, @RequestParam("weather") String weather) {
        logger.info("Creating alert:" + username + region + weather);
        userAlertsService.addAlertToUser(username, weather, region, WeatherController.regions.get(region));
        User user = userAlertsService.getUserAndAlerts(username);
        /*alertRepository.save(newAlert);
        user.addAlert(newAlert);
        userRepository.save(user);
        logger.info("ALERT!!!!!!!!!!!!!!!!!!!!!!" + userRepository.findOne(username).getAlerts().toString());*/
    }
    @RequestMapping(value = "/{user}", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html", params = { "enable_disable" })
    public String changeStateAndReloadHTML(@PathVariable("user") String username, /*@PathVariable("id") String id,*/
                                      @RequestParam String enable_disable, @RequestParam("id") long alertId) {
        logger.info("Managing alert:" + enable_disable + alertId);
        userAlertsService.changeEnabledAlert(alertId);
        return "redirect:/users/" + username;
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html", params = { "delete" })
    public String deleteAndReloadHTML(@PathVariable("user") String username, /*@PathVariable("id") String id,*/
                                      @RequestParam String delete, @RequestParam("id") long alertId) {
        logger.info("Managing alert:" + delete + alertId);
        userAlertsService.removeAlertFromUser(alertId);
        return "redirect:/users/" + username;
    }
    @RequestMapping(value = "/{user}", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html", params = { "addAlert" })
    public String saveAndReloadHTML(@PathVariable("user") String username, @RequestParam String addAlert,
                             @RequestParam("region") String region, @RequestParam("weather") String weather) {
        saveAlert(username, region, weather);
        return "redirect:/users/" + username;
    }





}
