package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.AgeRange;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AgeRangeDto {

    private Long id;

    @NotNull
    @Min(1)
    @Max(7)
    private int minAge;

    @NotNull
    @Min(1)
    @Max(7)
    private int maxAge;

    public AgeRangeDto() {
    }

    public AgeRangeDto(AgeRange ageRange) {
        this.id = ageRange.getId();
        this.minAge = ageRange.getAgeMin();
        this.maxAge = ageRange.getAgeMax();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
