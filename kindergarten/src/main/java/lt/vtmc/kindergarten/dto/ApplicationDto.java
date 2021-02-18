package lt.vtmc.kindergarten.dto;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lt.vtmc.kindergarten.domain.ApplicationStatusEnum;
import lt.vtmc.kindergarten.domain.KindergartenApplicationForm;
import lt.vtmc.kindergarten.domain.Person;


public class ApplicationDto {


    private Long id;
    private Date date;
    private Set<String> kindergartensIds = new LinkedHashSet<>();
    private Person child;
    private PersonDto parent;
    private Person secondParent;
    private int score;
    private boolean isAdopted;
    private boolean isMultiChild;
    private boolean isGuardianStudent;
    private boolean isGuardianDisabled;
    private ApplicationStatusEnum applicationStatus;

    public ApplicationDto() {
    }

    ;

    public ApplicationDto(Long id, ApplicationStatusEnum applicationStatus, Person child,
                          PersonDto parent, int score,
                          Person secondParent, Date date, boolean isAdopted, boolean isMultiChild, boolean isGuardianDisabled, boolean isGuardianStudent,
                          Set<KindergartenApplicationForm> kindergartenApplicationForms) {
        this.id = id;
        this.applicationStatus = applicationStatus;
        this.child = child;
        this.date = date;
        this.parent = parent;
        this.score = score;
        this.secondParent = secondParent;
        this.isAdopted = isAdopted;
        this.isMultiChild = isMultiChild;
        this.isGuardianDisabled = isGuardianDisabled;
        this.isGuardianStudent = isGuardianStudent;
        this.kindergartensIds = kindergartenApplicationForms.stream()
                .map(application -> "Priority" + application.getPriority()
                        + ":" + application.getKindergarten().getId()).collect(Collectors.toSet());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<String> getKindergartensIds() {
        return kindergartensIds;
    }

    public void setKindergartensIds(Set<String> kindergartensIds) {
        this.kindergartensIds = kindergartensIds;
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

    public Person getChildId() {
        return child;
    }


    public void setChild(Person child) {
        this.child = child;
    }

    public PersonDto getParent() {
        return parent;
    }

    public void setParent(PersonDto parent) {
        this.parent = parent;
    }

    public Person getSecondParent() {
        return secondParent;
    }

    public void setSecondParent(Person secondParent) {
        this.secondParent = secondParent;
    }
}


