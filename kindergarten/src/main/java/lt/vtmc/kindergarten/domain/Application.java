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

    @OneToOne(mappedBy = "application")
    private Child child;

    @Column
    private boolean isAdopted;

    @Column
    private boolean isMultiChild;

    @Column
    private boolean isGuardianStudent;

    @Column
    private boolean isGuardianDisabled;



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

    public boolean isAdopted() {
        return isAdopted;
    }

    public void setAdopted(boolean adopted) {
        isAdopted = adopted;
    }

    public boolean isMultiChild() {
        return isMultiChild;
    }

    public void setMultiChild(boolean multiChild) {
        isMultiChild = multiChild;
    }

    public boolean isGuardianStudent() {
        return isGuardianStudent;
    }

    public void setGuardianStudent(boolean guardianStudent) {
        isGuardianStudent = guardianStudent;
    }

    public boolean isGuardianDisabled() {
        return isGuardianDisabled;
    }

    public void setGuardianDisabled(boolean guardianDisabled) {
        isGuardianDisabled = guardianDisabled;
    }

    public Set<KindergartenApplication> getKindergartenApplications() {
        return kindergartenApplications;
    }

    public void setKindergartenApplications(Set<KindergartenApplication> kindergartenApplications) {
        this.kindergartenApplications = kindergartenApplications;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
