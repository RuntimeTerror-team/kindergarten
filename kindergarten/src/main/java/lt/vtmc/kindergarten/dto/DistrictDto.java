package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.District;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class DistrictDto {

    @NotNull
    private Long id;

    @NotNull
    @Length(min = 5)
    @Length(max = 20)
    private String title;

    public DistrictDto() {
    }

    public DistrictDto(District district) {
        this.id = district.getId();
        this.title = district.getTitle();
    }

    private String uppercaseFirstLetter(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return uppercaseFirstLetter(title);
    }

    public void setTitle(String title) {
        this.title = uppercaseFirstLetter(title);
    }

}
