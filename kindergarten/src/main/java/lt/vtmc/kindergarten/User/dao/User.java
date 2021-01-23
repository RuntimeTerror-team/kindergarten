package lt.vtmc.kindergarten.User.dao;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="User")
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
    @Column
    private String role;

//    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.DETACH})
//    @JoinColumn(name = "role_id")
//    private Role role;

    public User() {
    }

    public User(String username, String firstName, String lastName, Long personalCode, @NotBlank String password, String role) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
