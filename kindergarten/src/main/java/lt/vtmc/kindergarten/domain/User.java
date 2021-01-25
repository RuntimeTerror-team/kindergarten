package lt.vtmc.kindergarten.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column
    private String username;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Long personalCode;
    @NotBlank
    private String password;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "role_roleType")
    private Role role;

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
}
