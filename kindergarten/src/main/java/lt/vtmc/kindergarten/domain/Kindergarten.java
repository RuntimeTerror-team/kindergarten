package lt.vtmc.kindergarten.domain;


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
    private Integer phoneNumeber;

    @Column
    @Email
    private String email;

    @Column
    private String website;


    @ManyToOne
    private District district;


    @OneToMany(mappedBy = "id")
    private Set<Group> groups = new HashSet<Group>();


    @OneToMany(mappedBy = "id")
    private Set<Application> applicationsSet = new HashSet<Application>();


    public Kindergarten() {
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

    public Integer getPhoneNumeber() {
        return phoneNumeber;
    }

    public void setPhoneNumeber(Integer phoneNumeber) {
        this.phoneNumeber = phoneNumeber;
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
