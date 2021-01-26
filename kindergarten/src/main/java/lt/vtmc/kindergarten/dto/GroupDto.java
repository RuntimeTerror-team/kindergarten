package lt.vtmc.kindergarten.dto;


import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.Group;
import lt.vtmc.kindergarten.domain.Kindergarten;

import javax.validation.constraints.NotNull;

public class GroupDto {

    @NotNull
    private Long id;

    private int ageMin;

    private int ageMax;

    private int childrenCount;

    private String title;

    public GroupDto() {
    }

    public GroupDto(Group group) {
        this.id = group.getId();
        this.ageMin = group.getAgeMin();
        this.ageMax = group.getAgeMax();
        this.childrenCount = group.getChildrenCount();
        this.title = group.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
