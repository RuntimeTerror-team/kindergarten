package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.ApplicationAfterDistribution;
import lt.vtmc.kindergarten.domain.ApplicationStatusEnum;

import java.util.Date;

public class DistributedApplicationInfoDto {

    private Date applicationCreationDate;

    private String childFirstName;

    private String childLastName;

    private ApplicationStatusEnum applicationStatus;
    
    private String approvedKindergarten;
    
    private Long waitingNumber;

    public DistributedApplicationInfoDto() {
    }

    public DistributedApplicationInfoDto(ApplicationAfterDistribution application) {
        this.applicationCreationDate = application.getDate();
        this.childFirstName = application.getChildFirstName();
        this.childLastName = application.getChildLastName();
        this.applicationStatus = application.getStatus();
        this.approvedKindergarten = application.getApprovedKindergarten();
        this.waitingNumber = application.getWaitingNumber();
    }

    public Date getApplicationCreationDate() {
        return applicationCreationDate;
    }

    public void setApplicationCreationDate(Date applicationCreationDate) {
        this.applicationCreationDate = applicationCreationDate;
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

    public ApplicationStatusEnum getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatusEnum applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

	public String getApprovedKindergarten() {
		return approvedKindergarten;
	}

	public void setApprovedKindergarten(String approvedKindergarten) {
		this.approvedKindergarten = approvedKindergarten;
	}

	public Long getWaitingNumber() {
		return waitingNumber;
	}

	public void setWaitingNumber(Long waitingNumber) {
		this.waitingNumber = waitingNumber;
	}
    
    
}

