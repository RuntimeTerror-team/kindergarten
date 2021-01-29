package lt.vtmc.kindergarten.domain;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    @Id
    @NotNull
    @Length(min = 8, max = 30)
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Long personalCode;
    @NotNull
    private String password;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "roleType")
    private Role role;

    @OneToMany(mappedBy = "id")
    private Set<UserApplication> userApplication = new HashSet<>();

    public User() {
    }

    public User(String username, String firstName, String lastName, Long personalCode, @NotBlank String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
        this.password = password;
    }
    public User(String username, String firstName, String lastName, Long personalCode, @NotBlank String password, Role role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Long personalCode) {
        this.personalCode = personalCode;
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

    public Set<UserApplication> getUserApplication() {
        return userApplication;
    }

    public void setUserApplication(Set<UserApplication> userApplication) {
        this.userApplication = userApplication;
    }
}
