package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.domain.Kindergarten;



import javax.validation.constraints.*;


public class KindergartenDto {

    @NotNull
    private String title;

    @NotNull
    private String address;

    @NotNull
    private CityEnum city;

    @NotNull
    @Pattern(regexp = "^\\d{1,5}")
    private String postalCode;

    @NotNull
    @Pattern(regexp = "(^8\\d{8}|^\\+370\\d{8})")
    private String phoneNumber;

    @Email
    private String email;

    private String website;


    @NotNull
    @Pattern(regexp = "^\\d{7,9}")
    private String companyCode;

    @NotNull
    private District district;


    public KindergartenDto() {
    }

    public KindergartenDto(Kindergarten kindergarten) {
        this.title = kindergarten.getTitle();
        this.address = kindergarten.getAddress();
        this.city = kindergarten.getCity();
        this.postalCode = kindergarten.getPostalCode();
        this.phoneNumber = kindergarten.getPhoneNumber();
        this.email = kindergarten.getEmail();
        this.website = kindergarten.getWebsite();
        this.district = kindergarten.getDistrict();
        this.companyCode = kindergarten.getCompanyCode();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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
