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

    @OneToMany(mappedBy = "user")
    private Set<UserApplication> users = new HashSet<UserApplication>();

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "application")
    private Set<KindergartenApplicationForm> kindergartenApplicationForms = new HashSet<>();

    @OneToOne(mappedBy = "application")
    private Child child;

    @Column
    private int score;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserApplication> getUsers() {
        return users;
    }

    public void addUser(UserApplication user) {
        user.setApplication(this);
        this.users.add(user);
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

    public Set<KindergartenApplicationForm> getKindergartenApplications() {
        return kindergartenApplicationForms;
    }

    public void setKindergartenApplications(Set<KindergartenApplicationForm> kindergartenApplicationForms) {
        this.kindergartenApplicationForms = kindergartenApplicationForms;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
