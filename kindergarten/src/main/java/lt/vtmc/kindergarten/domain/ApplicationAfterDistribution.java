package lt.vtmc.kindergarten.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ApplicationAfterDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    private String childFirstName;

    @Column
    private String childLastName;

    @Column
    private String parentFirstName;

    @Column
    private String parentLastName;

    @Column
    private int score;

    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationStatusEnum status;

    @Column
    private Long applicationId;

    @Column(name = "kindergarten")
    private String approvedKindergarten;

    @Column
    private Long waitingNumber;

    public ApplicationAfterDistribution() {
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getWaitingNumber() {
        return waitingNumber;
    }

    public void setWaitingNumber(Long waitingNumber) {
        this.waitingNumber = waitingNumber;
    }

    public String getApprovedKindergarten() {
        return approvedKindergarten;
    }

    public void setApprovedKindergarten(String approvedKindergarten) {
        this.approvedKindergarten = approvedKindergarten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getChildFirstName() {
        return childFirstName;
    }

    public void setChildFirstName(String childFirstName) {
        this.childFirstName = childFirstName;
    }

    public String getChildLastName() {
        return childLastName;
    }

    public void setChildLastName(String childLastName) {
        this.childLastName = childLastName;
    }

    public String getParentFirstName() {
        return parentFirstName;
    }

    public void setParentFirstName(String parentFirstName) {
        this.parentFirstName = parentFirstName;
    }

    public String getParentLastName() {
        return parentLastName;
    }

    public void setParentLastName(String parentLastName) {
        this.parentLastName = parentLastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ApplicationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatusEnum status) {
        this.status = status;
    }
}
