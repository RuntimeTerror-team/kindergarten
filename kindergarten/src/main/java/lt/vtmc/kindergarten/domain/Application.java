package lt.vtmc.kindergarten.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(mappedBy = "username")
    private Set<User> users = new HashSet<User>();

    @Column
    private Long childId;


    @Temporal(TemporalType.DATE)
    private Date date;


    @OneToMany(mappedBy = "application")
    private Set<KindergartenApplication> kindergartenApplications = new HashSet<>();

    public Application() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<KindergartenApplication> getKindergartenApplications() {
        return kindergartenApplications;
    }

    public void setKindergartenApplications(Set<KindergartenApplication> kindergartenApplications) {
        this.kindergartenApplications = kindergartenApplications;
    }
}
