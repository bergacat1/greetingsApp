package cat.udl.eps.softarch.hello.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
//@Table(name = "ALERT")
public class Alert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    public User user;

    @NotBlank
    public String weather;

    @NotBlank
    public String region;

    @NotNull
    public boolean enabled;

    public Alert() {}


    public Alert(User user, String weather, String region) {
        this.user = user;
        this.weather = weather;
        this.region = region;
        this.enabled = true;
    }
    
    public void changeEnabled(){
        if(enabled){
            enabled = false;
        }else{
            enabled = true;
        }
    }

    public String getEnabledState(){
        if(enabled){
            return "Activada";
        }else{
            return "Desactivada";
        }
    }

    public String getWeather() {
        return weather;
    }

    public String getRegion() {
        return region;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alert alert = (Alert) o;

        if (id != alert.id) return false;
        if (!region.equals(alert.region)) return false;
        if (!user.equals(alert.user)) return false;
        if (!weather.equals(alert.weather)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + weather.hashCode();
        result = 31 * result + region.hashCode();
        return result;
    }
}
