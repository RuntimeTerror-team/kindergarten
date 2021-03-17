package lt.vtmc.kindergarten.dto;

import javax.validation.constraints.NotNull;

public class GroupCreationDto{

    @NotNull
    private Long id;

    private int childrenCount;

    private Long ageRangeId;


    public GroupCreationDto(@NotNull Long id, int childrenCount, Long ageRangeId) {
        this.id = id;
        this.childrenCount = childrenCount;
        this.ageRangeId = ageRangeId;
    }

    public Long getAgeRangeId() {
        return ageRangeId;
    }

    public void setAgeRangeId(Long ageRangeId) {
        this.ageRangeId = ageRangeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

}
