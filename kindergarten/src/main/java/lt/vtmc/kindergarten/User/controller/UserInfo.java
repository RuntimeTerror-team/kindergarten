package lt.vtmc.kindergarten.User.controller;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserInfo {

    @NotNull
    @Length(min=8)
    private String username;
    private String firstName;
    private String lastName;
    private Long personalCode;
    private String password;
    private String role;

    public UserInfo() {
    }

    public UserInfo(@NotNull @Length(min = 8)String username, String firstName, String lastName, Long personalCode, String password, String role) {
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
