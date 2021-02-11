//package lt.vtmc.kindergarten.domain;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "application")
//public class Application {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Temporal(TemporalType.DATE)
//    private Date date;
//
//    @OneToMany(mappedBy = "application")
//    private Set<KindergartenApplicationForm> kindergartenApplicationForms = new HashSet<>();
//
//    @NotNull
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "child_id")
//    private Child child;
//
//    @NotNull
//    @ManyToOne
//    private User applicant;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Person secondParentInfo;
//
//    @Column
//    private int score;
//
//    @Column
//    private boolean isAdopted;
//
//    @Column
//    private boolean isMultiChild;
//
//    @Column
//    private boolean isGuardianStudent;
//
//    @Column
//    private boolean isGuardianDisabled;
//
//    @Column
//    @Enumerated(EnumType.STRING)
//    private ApplicationStatusEnum applicationStatus;
//
//    public Application() {
//    }
//
//    public ApplicationStatusEnum getApplicationStatus() {
//        return applicationStatus;
//    }
//
//    public void setApplicationStatus(ApplicationStatusEnum applicationStatus) {
//        this.applicationStatus = applicationStatus;
//    }
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public boolean isAdopted() {
//        return isAdopted;
//    }
//
//    public void setAdopted(boolean adopted) {
//        isAdopted = adopted;
//    }
//
//    public boolean isMultiChild() {
//        return isMultiChild;
//    }
//
//    public void setMultiChild(boolean multiChild) {
//        isMultiChild = multiChild;
//    }
//
//    public boolean isGuardianStudent() {
//        return isGuardianStudent;
//    }
//
//    public void setGuardianStudent(boolean guardianStudent) {
//        isGuardianStudent = guardianStudent;
//    }
//
//    public boolean isGuardianDisabled() {
//        return isGuardianDisabled;
//    }
//
//    public void setGuardianDisabled(boolean guardianDisabled) {
//        isGuardianDisabled = guardianDisabled;
//    }
//
//    public Child getChild() {
//        return child;
//    }
//
//    public void setChild(Child child) {
//        this.child = child;
//    }
//
//
//    public Set<KindergartenApplicationForm> getKindergartenApplicationForms() {
//        return kindergartenApplicationForms;
//    }
//
//    public void setKindergartenApplicationForms(Set<KindergartenApplicationForm> kindergartenApplicationForms) {
//        this.kindergartenApplicationForms = kindergartenApplicationForms;
//    }
//
//    public User getApplicant() {
//        return applicant;
//    }
//
//    public void setApplicant(User applicant) {
//        this.applicant = applicant;
//    }
//
//    public Person getSecondParentInfo() {
//        return secondParentInfo;
//    }
//
//    public void setSecondParentInfo(Person secondParentInfo) {
//        this.secondParentInfo = secondParentInfo;
//    }
//
//}
