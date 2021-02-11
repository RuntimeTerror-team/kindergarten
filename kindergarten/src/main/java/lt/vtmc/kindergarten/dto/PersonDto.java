package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.Person;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PersonDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Length(min = 11,max = 11)
    private String personalCode;


    @Pattern(regexp = "(^8\\d{8}|^\\+370\\d{8})")
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private CityEnum cityEnum;

    @NotNull
    @Pattern(regexp = "^\\d{1,5}")
    private String postalCode;

    @Email
    private String email;

    public PersonDto() {
    }

    public PersonDto(@Valid Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.personalCode = person.getPersonalCode();
        this.phoneNumber = person.getPhoneNumber();
        this.address = person.getAddress();
        this.cityEnum = person.getCity();
        this.postalCode = person.getPostalCode();
        this.email = person.getEmail();
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

    public CityEnum getCityEnum() {
        return cityEnum;
    }

    public void setCityEnum(CityEnum cityEnum) {
        this.cityEnum = cityEnum;
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
}
