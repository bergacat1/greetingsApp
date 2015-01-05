package cat.udl.eps.softarch.hello.service;

import cat.udl.eps.softarch.hello.model.Alert;
import cat.udl.eps.softarch.hello.model.User;

/**
 * Created by http://rhizomik.net/~roberto/
 */
public interface UserAlertsService {
    User getUserAndAlerts(String username);

    Alert addAlertToUser(Alert alert);

    void removeAlertFromUser(Long alertId);
}
