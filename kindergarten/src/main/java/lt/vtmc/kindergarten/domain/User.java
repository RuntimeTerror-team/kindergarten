package lt.vtmc.kindergarten.domain;


import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    private String password;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "roleType")
    private Role role;

    @OneToMany(mappedBy = "applicant",cascade = CascadeType.ALL)
    private Set<Application> applications = new HashSet<>();

//    @NotNull
    @Column
    @Length(min = 11,max = 11)
    private String personalCode;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;

    @Column
    @Pattern(regexp = "(^8\\d{8}|^\\+370\\d{8})")
    private String phoneNumber;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column
    private CityEnum city;

    @Pattern(regexp = "^\\d{1,5}")
    @Column
    private String postalCode;

    @Column
    @Email
    private String email;


    public User() {
    }

    public User(String username, String firstName, String lastName, String personalCode, @NotBlank String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
        this.password = password;
    }
    public User(String username, String firstName, String lastName, String personalCode, @NotBlank String password, Role role) {
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

    public Set<Application> getUserApplications() {
        return applications;
    }

    public void setUserApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CityEnum getCity() {
        return city;
    }

    public void setCity(CityEnum city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addUserApplication(Application application){
        application.setApplicant(this);
        this.applications.add(application);
    }
}
