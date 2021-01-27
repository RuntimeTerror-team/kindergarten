package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.District;

import javax.validation.constraints.NotNull;

public class DistrictDto {

    @NotNull
    private String title;

    public DistrictDto(District district) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
