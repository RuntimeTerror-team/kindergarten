package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Child;
import lt.vtmc.kindergarten.domain.CityEnum;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ChildDto {


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Pattern(regexp = "^\\d{1,11}")
    private String personalCode;

    @NotNull
    private String streetAddress;

    @NotNull
    private CityEnum city;

    public ChildDto() {
    }

    public ChildDto(Child child) {
        this.firstName = child.getFirstName();
        this.lastName = child.getLastName();
        this.personalCode = child.getPersonalCode();
        this.streetAddress = child.getStreetAddress();
        this.city = child.getCity();
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

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
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
