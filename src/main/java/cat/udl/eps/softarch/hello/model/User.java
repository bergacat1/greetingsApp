package cat.udl.eps.softarch.hello.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Entity
public class User implements Serializable{

    @Id
    @NotBlank(message = "Username cannot be empty")
    @Size(max = 30, message = "The username is too long")
    private String username;

    @NotBlank(message = "E-mail cannot be empty")
    @Email(message = "E-mail should be valid")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="user")
    public List<Alert> alerts;

    public User(){}
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        alerts = new ArrayList<>();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() { return email; }

    public void addAlert(Alert alert){
        this.alerts.add(alert);
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void removeAlert(Alert a){ alerts.remove(a); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!alerts.equals(user.alerts)) return false;
        if (!email.equals(user.email)) return false;
        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + alerts.size();
        return result;
    }
}
