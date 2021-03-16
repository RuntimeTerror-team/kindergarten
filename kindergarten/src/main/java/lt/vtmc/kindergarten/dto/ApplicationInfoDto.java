package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.ApplicationStatusEnum;

import java.util.Date;

public class ApplicationInfoDto {
	
	private Long applicationId;

    private Date applicationCreationDate;

    private String childFirstName;

    private String childLastName;

    private ApplicationStatusEnum applicationStatus;

    public ApplicationInfoDto() {
    }

    public ApplicationInfoDto(Application application) {
    	this.applicationId = application.getId();
        this.applicationCreationDate = application.getDate();
        this.childFirstName = application.getChild().getFirstName();
        this.childLastName = application.getChild().getLastName();
        this.applicationStatus = application.getApplicationStatus();
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

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
    
}
