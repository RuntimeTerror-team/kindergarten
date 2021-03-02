package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.ApplicationAfterDistribution;


import java.util.Date;

public class ApplicationAfterDistributionDto {


    private Long id;

    private Date date;

    private String childFirstName;

    private String childLastName;

    private String parentFirstName;

    private String parentLastName;

    private int score;

    private String status;

    private String approvedKindergartenTitle;

    private Long waitingNumber;

    public ApplicationAfterDistributionDto() {
    }

    public ApplicationAfterDistributionDto(ApplicationAfterDistribution application) {
        this.id = application.getId();
        this.date = application.getDate();
        this.childFirstName = application.getChildFirstName();
        this.childLastName = application.getChildLastName();
        this.parentFirstName = application.getParentFirstName();
        this.parentLastName = application.getParentLastName();
        this.score = application.getScore();
        this.status = application.getStatus();
        this.approvedKindergartenTitle = application.getApprovedKindergarten();
        this.waitingNumber = application.getWaitingNumber();
    }

    public Long getWaitingNumber() {
        return waitingNumber;
    }

    public void setWaitingNumber(Long waitingNumber) {
        this.waitingNumber = waitingNumber;
    }

    public String getApprovedKindergartenTitle() {
        return approvedKindergartenTitle;
    }

    public void setApprovedKindergartenTitle(String approvedKindergartenTitle) {
        this.approvedKindergartenTitle = approvedKindergartenTitle;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
