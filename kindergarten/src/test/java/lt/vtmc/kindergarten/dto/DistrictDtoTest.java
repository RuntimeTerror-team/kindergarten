package lt.vtmc.kindergarten.dto;
import lt.vtmc.kindergarten.domain.District;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DistrictDtoTest {
    @Test
    public void titleShouldBeGetWithUppercasedLetter(){
        DistrictDto districtDto = new DistrictDto();
        districtDto.setTitle("vilnius");

        assertEquals("Vilnius",districtDto.getTitle(), "First letter should be upper cased");
    }

    @Test
    public void titleShouldBeSetToUpperCasedLetter(){
        District districtEntity = new District();
        districtEntity.setTitle("kaunas");
        districtEntity.setId(1L);

        DistrictDto districtDto = new DistrictDto(districtEntity);

        assertEquals("Kaunas",districtDto.getTitle(), "First letter should be upper cased");
    }


}
