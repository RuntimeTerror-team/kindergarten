package lt.vtmc.kindergarten.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "application",cascade = CascadeType.ALL)
    private Set<KindergartenApplicationForm> kindergartenApplicationForms = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_person_id", nullable = false)
    private Person child;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_person_id", nullable = false)
    private Person parent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_parent_person_id", nullable = true)
    private Person secondParent;

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

    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationStatusEnum applicationStatus;

    @ManyToOne
    @JoinColumn(name = "queue_id")
    private Queue queue;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<KindergartenApplicationForm> getKindergartenApplicationForms() {
        return kindergartenApplicationForms;
    }

    public void setKindergartenApplicationForms(Set<KindergartenApplicationForm> kindergartenApplicationForms) {
        this.kindergartenApplicationForms = kindergartenApplicationForms;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public ApplicationStatusEnum getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatusEnum applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getChild() {
        return child;
    }


    public void setChild(Person childId) {
        this.child = childId;
    }

    public Person getParent() {
        return parent;
    }

    public void setParent(Person parentId) {
        this.parent = parentId;
    }

    public Person getSecondParent() {
        return secondParent;
    }

    public void setSecondParent(Person secondParentId) {
        this.secondParent = secondParentId;
    }
}
