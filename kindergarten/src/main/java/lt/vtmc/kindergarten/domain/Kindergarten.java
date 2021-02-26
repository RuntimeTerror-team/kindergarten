package lt.vtmc.kindergarten.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kindergarten")
public class Kindergarten {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @NotNull(message = "title may not be null")
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
    private String phoneNumber;

    @Column
    @Email
    private String email;

    @Column
    private String website;

    @Column(unique = true)
    private String companyCode;

    @ManyToOne
    private District district;


    @OneToMany(mappedBy = "kindergarten")
    @Cascade(CascadeType.ALL)
    private Set<Group> groups = new HashSet<Group>();


    @OneToMany(mappedBy = "kindergarten")
    private Set<KindergartenApplicationForm> applicationsSet = new HashSet<KindergartenApplicationForm>();


    public Kindergarten() {
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

    public void addGroup(Group group){
        group.setKindergarten(this);
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

    public Set<KindergartenApplicationForm> getApplicationsSet() {
        return applicationsSet;
    }

    public void setApplicationsSet(Set<KindergartenApplicationForm> applicationsSet) {
        this.applicationsSet = applicationsSet;
    }

    public void addApplicationForm(KindergartenApplicationForm applicationForm) {
        applicationsSet.add(applicationForm);
    }
}
