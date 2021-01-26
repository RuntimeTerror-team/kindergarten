package lt.vtmc.kindergarten.domain;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "kindergarten")
public class Kindergarten {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private CityEnum city;

    @Column
    private Integer postalCode;

    @Column
    private Integer phoneNumber;

    @Column
    @Email
    private String email;

    @Column
    private String website;


    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.DETACH})
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

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumeber) {
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
