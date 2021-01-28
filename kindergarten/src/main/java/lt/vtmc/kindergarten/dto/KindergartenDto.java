package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.domain.Kindergarten;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class KindergartenDto {


    @NotNull
    private Long id;

    private String title;

    private String address;

    private CityEnum city;

    private Integer postalCode;

    private Integer phoneNumber;

    @Email
    private String email;

    private String website;

    private District district;


    public KindergartenDto() {
    }

    public KindergartenDto(Kindergarten kindergarten) {
        this.id = kindergarten.getId();
        this.title = kindergarten.getTitle();
        this.address = kindergarten.getAddress();
        this.city = kindergarten.getCity();
        this.postalCode = kindergarten.getPostalCode();
        this.phoneNumber = kindergarten.getPhoneNumber();
        this.email = kindergarten.getEmail();
        this.website = kindergarten.getWebsite();
        this.district =kindergarten.getDistrict();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
