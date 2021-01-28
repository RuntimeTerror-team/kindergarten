package lt.vtmc.kindergarten.domain;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "child")
public class Child {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Long personalCode;

    @Column
    private String streetAddress;

    @Column
    private CityEnum city;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    public Child() {
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
