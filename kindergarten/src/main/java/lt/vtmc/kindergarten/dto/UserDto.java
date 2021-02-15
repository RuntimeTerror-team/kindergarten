package lt.vtmc.kindergarten.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    @Length(min=8, max=30)
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;

    public UserDto() {
    }

    public UserDto(@NotNull @Length(min = 8, max=30) String username, String password, String role) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
