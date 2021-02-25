package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.ApprovedApplication;

import java.util.Date;

public class ApprovedApplicationDto {


    private Long id;

    private Date date;

    private String childFirstName;

    private String childLastName;

    private String parentFirstName;

    private String parentLastName;

    private int score;

    private String status;

    public ApprovedApplicationDto() {
    }

    public ApprovedApplicationDto(Application application) {
        this.id = application.getId();
        this.date = application.getDate();
        this.childFirstName = application.getChild().getFirstName();
        this.childLastName = application.getChild().getLastName();
        this.parentFirstName = application.getParent().getFirstName();
        this.parentLastName = application.getParent().getLastName();
        this.score = application.getScore();
        this.status = application.getApplicationStatus().toString();
    }


//    public ApprovedApplicationDto(ApprovedApplication approvedApplication) {
//        this.id = approvedApplication.getId();
//        this.date = approvedApplication.getDate();
//        this.childFirstName = approvedApplication.getChildFirstName();
//        this.childLastName = approvedApplication.getParentLastName();
//        this.parentFirstName = approvedApplication.getParentFirstName();
//        this.parentLastName = approvedApplication.getParentLastName();
//        this.score = approvedApplication.getScore();
//        this.status = approvedApplication.getStatus();
//    }

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
