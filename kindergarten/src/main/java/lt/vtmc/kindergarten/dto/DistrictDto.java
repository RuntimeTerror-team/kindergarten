package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.District;

import javax.validation.constraints.NotNull;

public class DistrictDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    public DistrictDto() {
    }

    public DistrictDto(District district) {
        this.id = district.getId();
        this.title = district.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
