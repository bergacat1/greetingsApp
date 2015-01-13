package cat.udl.eps.softarch.hello.service;

import cat.udl.eps.softarch.hello.controller.WeatherController;
import cat.udl.eps.softarch.hello.model.Alert;
import cat.udl.eps.softarch.hello.repository.AlertRepository;
import cat.udl.eps.softarch.hello.util.Weather;
import cat.udl.eps.softarch.hello.util.XQueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Albert on 07/01/2015.
 */
@Service
public class AlertNotifier {
    final Logger logger = LoggerFactory.getLogger(AlertNotifier.class);
    @Autowired
    MailService mailService;

    @Autowired
    AlertRepository alertRepository;

    @Scheduled(fixedDelay = 86400000)
    public void notifyAlerts(){
        logger.info("Notifying users");
        for(Map.Entry<String, Integer> region: WeatherController.regions.entrySet()){
            List<Alert> regionAlerts = alertRepository.findByRegionAndEnabled(region.getKey(), true);
            if(!regionAlerts.isEmpty()){
                try{
                    Weather weather = WeatherController.weathers.get(XQueryHelper.getRegionWeather(region.getValue()));
                    for(Alert alert: regionAlerts){
                        if(alert.getWeather().equals(weather.getName())){
                            logger.info("Sending a mail to: " + alert.getUser().getUsername());
                            mailService.sendMail(alert.getUser().getEmail(),alert.getRegion(), weather.getName());
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
}
