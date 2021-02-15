package lt.vtmc.kindergarten.dto;

public class UserValidateCommandDto {

    private String username;
    private boolean associatedPersonExists;

    public UserValidateCommandDto() {
    }

    public boolean isAssociatedPersonExists() {
        return associatedPersonExists;
    }

    public void setAssociatedPersonExists(boolean associatedPersonExists) {
        this.associatedPersonExists = associatedPersonExists;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
