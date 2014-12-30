package cat.udl.eps.softarch.hello.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Observable;
import java.util.Date;
import java.util.Observer;

import cat.udl.eps.softarch.hello.util.Weather;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Alert implements Observer, Serializable {

    @javax.persistence.Transient()
    private Weather weather;
    @Id
    @NotNull(message = "E-mail cannot be empty")
    @Email(message = "E-mail should be valid")
    private String email;

    @Id
    @NotNull
    private Integer idRegion;

    @Id
    private String idWeather;


    public Alert() {}


    public Alert(String email, Weather weather, Integer idRegion) {
        this.email = email;
        this.weather = weather;
        this.weather.addObserver(this);
        this.idRegion = idRegion;
        this.idWeather = weather.getName();
    }


    public Weather getWeather() {
        return weather;
    }

    public String getEmail() {
        return email;
    }

    public String getIdWeather() {
        return idWeather;
    }


    public Integer getIdRegion() {
        return idRegion;
    }




    @Override
    public void update(Observable o, Object arg) {
        Integer idRegion;
        if(arg instanceof Integer){
            idRegion = (Integer)arg;
            if(idRegion == this.idRegion){} //Enviar correu
        }

    }
}
