package cat.udl.eps.softarch.hello.service;

import cat.udl.eps.softarch.hello.model.Alert;
import cat.udl.eps.softarch.hello.model.User;
import cat.udl.eps.softarch.hello.repository.AlertRepository;
import cat.udl.eps.softarch.hello.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Service
public class UserAlertsServiceImpl implements UserAlertsService {
    final Logger logger = LoggerFactory.getLogger(UserAlertsServiceImpl.class);

    @Autowired
    AlertRepository alertRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public User createUser(User user){
        User newUser = userRepository.findOne(user.username);
        if (newUser == null){
            logger.info("New user: " + user.username + user.email);
            userRepository.save(user);
            return user;
        }
        return newUser;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserAndAlerts(String username) {
        User u = userRepository.findOne(username);
        if(u != null){
            logger.info("User {} has {} alerts", u.getUsername(), u.getAlerts().size());
        }

        return u;
    }

    @Transactional
    @Override
    public Alert addAlertToUser(Alert alert) {
        User u = userRepository.findOne(alert.user.username);
        u.addAlert(alert);
        alertRepository.save(alert);
        userRepository.save(u);
        return alert;
    }

    @Transactional
    @Override
    public void removeAlertFromUser(long alertId) {
        Alert a = alertRepository.findOne(alertId);
        User u = userRepository.findOne(a.getUser().getUsername());
        if (u != null) {
            u.removeAlert(a);
            userRepository.save(u);
        }
        alertRepository.delete(a);
    }

    @Transactional
    @Override
    public void changeEnabledAlert(long alertId){
        Alert a = alertRepository.findOne(alertId);
        a.changeEnabled();
        alertRepository.save(a);
    }

}
