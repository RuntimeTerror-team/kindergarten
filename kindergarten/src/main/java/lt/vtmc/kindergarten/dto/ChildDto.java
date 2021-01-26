package lt.vtmc.kindergarten.dto;


import lt.vtmc.kindergarten.domain.Child;
import lt.vtmc.kindergarten.domain.CityEnum;
import javax.validation.constraints.NotNull;

public class ChildDto {

    @NotNull
    private Long id;

    private String firstName;

    private String lastName;

    private Long personalCode;

    private String streetAddress;

    private CityEnum city;

    public ChildDto() {
    }

    public ChildDto(Child child) {
        this.id = child.getId();
        this.firstName = child.getFirstName();
        this.lastName = child.getLastName();
        this.personalCode = child.getPersonalCode();
        this.streetAddress = child.getStreetAddress();
        this.city = child.getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public CityEnum getCity() {
        return city;
    }

    public void setCity(CityEnum city) {
        this.city = city;
    }

}
