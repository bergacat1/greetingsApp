package cat.udl.eps.softarch.hello.service;

import cat.udl.eps.softarch.hello.model.Alert;
import cat.udl.eps.softarch.hello.model.User;

/**
 * Created by http://rhizomik.net/~roberto/
 */
public interface UserAlertsService {
    User createUser(User u);

    User getUserAndAlerts(String username);

    Alert addAlertToUser(Alert alert);

    void removeAlertFromUser(long alertId);

    void changeEnabledAlert(long alertId);
}
