package lt.vtmc.kindergarten.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserDtoFromAdmin {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String role;

    public UserDtoFromAdmin() {
    }

    public UserDtoFromAdmin(@NotNull String firstName, @NotNull String lastName, @NotNull String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
