package lt.vtmc.kindergarten.dto;

import java.util.Date;
import java.util.Map;

public class ApplicationCreationDto {

    private Long firstParentId;

    private Long childId;

    private Long secondParentId;

    private Date date;

    private Map<Integer,Long> priorityForKindergartenID;

    private boolean isAdopted;

    private boolean isMultiChild;

    private boolean isGuardianStudent;

    private boolean isGuardianDisabled;

    public ApplicationCreationDto() {
    }


    public Long getFirstParentId() {
        return firstParentId;
    }

    public void setFirstParentId(Long firstParentId) {
        this.firstParentId = firstParentId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getSecondParentId() {
        return secondParentId;
    }

    public void setSecondParentId(Long secondParentId) {
        this.secondParentId = secondParentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Integer, Long> getPriorityForKindergartenID() {
        return priorityForKindergartenID;
    }

    public void setPriorityForKindergartenID(Map<Integer, Long> priorityForKindergartenID) {
        this.priorityForKindergartenID = priorityForKindergartenID;
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
}
