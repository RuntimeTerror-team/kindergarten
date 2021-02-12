package lt.vtmc.kindergarten.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user")
public class User {

    @Id
    @NotNull
    @Length(min = 8, max = 30)
    private String username;

    @NotNull
    private String password;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "type")
    private Role role;


    public User() {
    }
    public User(String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String firstName, String lastName, Long personalCode, @NotBlank String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
