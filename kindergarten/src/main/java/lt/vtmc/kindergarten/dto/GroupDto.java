package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Group;

import javax.validation.constraints.NotNull;

public class GroupDto {

    @NotNull
    private Long id;

    private int childrenCount;

    private String title;

    public GroupDto() {
    }

    public GroupDto(Group group) {
        this.id = group.getId();
        this.childrenCount = group.getChildrenCount();
        this.title = group.getTitle();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
