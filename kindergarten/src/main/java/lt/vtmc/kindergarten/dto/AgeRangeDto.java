package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.AgeRange;

import javax.validation.constraints.NotNull;

public class AgeRangeDto {

//    @NotNull
//    private Long id;

    @NotNull
    private int minAge;

    @NotNull
    private int maxAge;

    public AgeRangeDto() {
    }

    public AgeRangeDto(int minAge, int maxAge) {
        //this.id = ageRange.getId();
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
