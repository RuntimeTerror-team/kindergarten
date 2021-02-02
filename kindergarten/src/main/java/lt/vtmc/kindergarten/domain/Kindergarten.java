package lt.vtmc.kindergarten.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "kindergarten")
public class Kindergarten {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @NotNull
    @Length(max = 35)
    private String title;

    @Column
    @NotNull
    @Length(max = 50)
    private String address;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private CityEnum city;

    @Column
    @NotNull
    @Length(max = 5)
    private String postalCode;

    @Column
    @NotNull
    private Long phoneNumber;

    @Column
    @Email
    private String email;

    @Column
    private String website;


    @ManyToOne
    @Cascade({CascadeType.DETACH})
    private District district;


    @OneToMany(mappedBy = "kindergarten")
    @Cascade(CascadeType.ALL)
    private Set<Group> groups = new HashSet<Group>();


    @OneToMany(mappedBy = "id")
    private Set<Application> applicationsSet = new HashSet<Application>();


    public Kindergarten() {
    }

    public void addGroup(Group group){
        this.groups.add(group);
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumeber) {
        this.phoneNumber = phoneNumeber;
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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Application> getApplicationsSet() {

        return applicationsSet;
    }

    public void setApplicationsSet(Set<Application> applicationsSet) {
        this.applicationsSet = applicationsSet;
    }


}
