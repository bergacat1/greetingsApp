package cat.udl.eps.softarch.hello.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Entity
@Table(name = "USER")
public class User implements Serializable{

    @Id
    @NotBlank(message = "Username cannot be empty")
    @Column(name = "USERNAME")
    @Size(max = 30, message = "The name is too long")
    private String name;

    @NotBlank(message = "E-mail cannot be empty")
    @Email(message = "E-mail should be valid")
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="user")
    public List<Alert> alerts;

    public User(){}
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        alerts = new ArrayList<>();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return email; }

    public void addAlert(Alert alert){
        System.out.println("ADD--------------------------------------------------");
        this.alerts.add(alert);
        System.out.println(alerts.toString());
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void removeAlert(Alert a){ alerts.remove(a); }
}
