package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.Alert;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

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
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User createUserIfNotExists(@RequestBody User user, HttpServletResponse response) {
        logger.info("Retrieving user: " + user);
        User newUser = userAlertsService.createUser(user);
        response.setHeader("Location", "/users/" + newUser.getUsername());
        return newUser;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String userHTML(@ModelAttribute("User") User user, BindingResult binding, HttpServletResponse response) {
//        Preconditions.checkArgument(!username.isEmpty(), "Heu d'indicar un nom d'usuari.");
//        Preconditions.checkArgument(!email.isEmpty(), "Heu d'indicar un email");
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "redirect:/users";
        }
        logger.info("Redirecting user: " + user.username);
        return "redirect:/users/" + createUserIfNotExists(user, response).username;
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
    public void saveAlert(@PathVariable("user") String username, @RequestBody Alert alert) {
        logger.info("Creating alert:" + alert.user.username + alert.region + alert.weather);
        userAlertsService.addAlertToUser(alert);
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
    public String saveAndReloadHTML(@PathVariable("user") String username, @RequestParam String addAlert, @RequestParam("email") String email,
                             @RequestParam("region") String region, @RequestParam("weather") String weather) {
        //saveAlert(username, email, region, weather);
        return "redirect:/users/" + username;
    }





}
