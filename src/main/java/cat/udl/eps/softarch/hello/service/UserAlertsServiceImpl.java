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

    @Transactional(readOnly = true)
    @Override
    public User getUserAndAlerts(String username) {
        User u = userRepository.findOne(username);
        logger.info("User {} has {} alerts", u.getUsername(), u.getAlerts().size());
        return u;
    }

    @Transactional
    @Override
    public Alert addAlertToUser(String username, String weather, String region, Integer regionId) {
        User u = userRepository.findOne(username);
        Alert newAlert = new Alert(u, weather, region);
        u.addAlert(newAlert);
        alertRepository.save(newAlert);
        userRepository.save(u);
        logger.info("DINS TRANSACTION!!!" + u.getAlerts().toString());
        return newAlert;
    }

    @Transactional
    @Override
    public void removeAlertFromUser(Long alertId) {
        Alert a = alertRepository.findOne(alertId);
        User u = userRepository.findOne(a.getUser().getUsername());
        if (u != null) {
            u.removeAlert(a);
            userRepository.save(u);
        }
        alertRepository.delete(a);
    }
}
