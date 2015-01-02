package cat.udl.eps.softarch.hello.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Observable;
import java.util.Date;
import java.util.Observer;

import cat.udl.eps.softarch.hello.util.Weather;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "ALERT")
public class Alert implements Observer, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER", referencedColumnName = "EMAIL")
    private User user;

    @javax.persistence.Transient()
    private Weather weather;

    @NotNull
    private String region;
    @NotNull
    private Integer idRegion;




    public Alert() {}


    public Alert(User user, Weather weather, String region, Integer idRegion) {
        this.user = user;
        this.weather = weather;
        this.weather.addObserver(this);
        this.region = region;
        this.idRegion = idRegion;
    }


    public Weather getWeather() {
        return weather;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public void update(Observable o, Object arg) {
        Integer idRegion;
        if(arg instanceof Integer){
            idRegion = (Integer)arg;
            if(idRegion == this.idRegion){} //Enviar correu
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alert alert = (Alert) o;

        if (!idRegion.equals(alert.idRegion)) return false;
        if (!user.equals(alert.user)) return false;
        if (!weather.equals(alert.weather)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + weather.hashCode();
        result = 31 * result + idRegion.hashCode();
        return result;
    }
}
