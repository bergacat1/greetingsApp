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
public class UserGreetingsServiceImpl implements UserAlertsService {
    final Logger logger = LoggerFactory.getLogger(UserGreetingsServiceImpl.class);

    @Autowired
    AlertRepository alertRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User getUserAndAlerts(String username) {
        User u = userRepository.findOne(username);
        logger.info("User {} has {} greetings", u.getName(), u.getAlerts().size());
        return u;
    }

    @Transactional
    @Override
    public Alert addAlertToUser(Alert a) {
        User u = userRepository.findOne(a.getUser().getName());
        //alertRepository.save(a);
        u.addAlert(a);
        userRepository.save(u);
        logger.info(("ALERTA GUARDADA! -.-.-.--.-.-.-.-...-.-.-.-.-..-.-.-" + userRepository.findOne(a.getUser().getName()).getAlerts().toString()));
        return a;
    }

    @Transactional
    @Override
    public void removeAlertFromUser(Long alertId) {
        Alert a = alertRepository.findOne(alertId);
        User u = userRepository.findOne(a.getUser().getName());
        if (u != null) {
            u.removeAlert(a);
            userRepository.save(u);
        }
        alertRepository.delete(a);
    }
}
