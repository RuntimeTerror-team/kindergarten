package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.domain.Kindergarten;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class KindergartenDto {

    @NotNull
    private String title;

    @NotNull
    private String address;

    @NotNull
    private CityEnum city;

    @NotNull
    private String postalCode;

    @NotNull
    private Long phoneNumber;

    @Email
    private String email;

    private String website;

   // @NotNull
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
        this.district =kindergarten.getDistrict();
    }
    
    
    public KindergartenDto(String title, String address, String city, String postalCode,
    		Long phoneNumber, String email, String website) {
    	
    	this.title = title;
    	this.address = address;
    	this.city = CityEnum.valueOf(city);
    	this.postalCode = postalCode;
    	this.phoneNumber = phoneNumber;
    	this.email = email;
    	this.website = website; 
    	
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
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
