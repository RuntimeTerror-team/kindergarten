package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.District;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class DistrictDto {

    private Long id;

    @NotNull
    @Length(min = 5,max = 20 )
    private String title;

    public DistrictDto() {
    }

    public DistrictDto(District district) {
        this.id = district.getId();
        this.title = district.getTitle();
    }

    public DistrictDto(@NotNull @Length(min = 5, max = 20) String title) {
        this.title = title;
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

    public void setTitle(@Length(min = 5)  String title) {
        this.title = uppercaseFirstLetter(title);
    }

}
