package cat.udl.eps.softarch.hello.service;

import cat.udl.eps.softarch.hello.model.Alert;
import cat.udl.eps.softarch.hello.model.User;
import cat.udl.eps.softarch.hello.util.Weather;

/**
 * Created by http://rhizomik.net/~roberto/
 */
public interface UserAlertsService {
    User getUserAndAlerts(String username);

    Alert addAlertToUser(String username, String weather, String region, Integer regionId);

    void removeAlertFromUser(Long alertId);
}
