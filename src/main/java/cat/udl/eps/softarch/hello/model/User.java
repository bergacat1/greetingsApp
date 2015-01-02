package cat.udl.eps.softarch.hello.model;

import java.util.ArrayList;
import java.util.Date;
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
public class User {

    @Id
    @NotNull(message = "E-mail cannot be empty")
    @Email(message = "E-mail should be valid")
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL)
    private List<Alert> alerts;



    public User() {}

    public User(String email) {
        this.email = email;
        alerts = new ArrayList<>();
    }

    public String getEmail() { return email; }

    public void addAlert(Alert alert){
        this.alerts.add(alert);
    }

    public List<Alert> getAlerts() {
        return alerts;
    }
}
