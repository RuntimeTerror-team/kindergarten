package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Person;

public class UserDetailsDto {
    private String username;
    private String role;
    private PersonDto personDetails;


    public UserDetailsDto() {
    }

    public UserDetailsDto(Person person, String username) {
        this.personDetails = new PersonDto(person.getId(), person.getFirstName(), person.getLastName(), person.getPersonalCode(),
        		person.getPhoneNumber(), person.getAddress(), person.getCity(), person.getPostalCode(), person.getEmail());
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public PersonDto getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDto personDetails) {
        this.personDetails = personDetails;
    }
}
