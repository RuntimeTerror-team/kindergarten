package lt.vtmc.kindergarten.dto;

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

    private Long applicationId;

    private String childPersonalCode;

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
        this.status = application.getStatus().toString();
        this.approvedKindergartenTitle = application.getApprovedKindergarten();
        this.waitingNumber = application.getWaitingNumber();
        this.applicationId = application.getApplicationId();
        this.childPersonalCode = application.getChildPersonalCode();
    }

    public String getChildPersonalCode() {
        return childPersonalCode;
    }

    public void setChildPersonalCode(String childPersonalCode) {
        this.childPersonalCode = childPersonalCode;
    }

    public Long getWaitingNumber() {
        return waitingNumber;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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
