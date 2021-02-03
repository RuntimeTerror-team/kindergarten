package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.domain.Kindergarten;
import lt.vtmc.kindergarten.dto.ChildDto;
import lt.vtmc.kindergarten.dto.DistrictDto;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import lt.vtmc.kindergarten.dto.UserDto;
import lt.vtmc.kindergarten.service.ChildService;
import lt.vtmc.kindergarten.service.DistrictService;
import lt.vtmc.kindergarten.service.KindergartenService;
import lt.vtmc.kindergarten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    DistrictService districtService;
    @Autowired
    KindergartenService kindergartenService;
    @Autowired
    ChildService childService;

    @Override
    public void run(String...args) throws Exception {
        userService.createUser(new UserDto("administratorius", "Adminas", "Adminaitis", 12345678989L, "Administratorius1", "ADMIN"));

        District district = new District();
        district.setId(1L);
        district.setTitle("Antakalnis");
        districtService.addDistrict(new DistrictDto(district));

        KindergartenDto kindergartenDto = new KindergartenDto();
        kindergartenDto.setTitle("Pusaite");
        kindergartenDto.setAddress("Gatves g. 56");
        kindergartenDto.setCity(CityEnum.VILNIUS);
        kindergartenDto.setPostalCode("12546");
        kindergartenDto.setPhoneNumber("862403523");
        kindergartenDto.setEmail("darzeliasass@gmail.com");
        kindergartenDto.setWebsite("www.darzelis.lt");
        kindergartenDto.setCompanyCode("19555587");
        kindergartenDto.setDistrict(district);
        kindergartenService.addKindergarten(kindergartenDto);

        ChildDto childDto = new ChildDto();
        childDto.setLastName("Antanas");
        childDto.setLastName("Antanaitis");
        childDto.setPersonalCode(49004180333L);
        childDto.setStreetAddress("Kankliu g.4");
        childDto.setCity(CityEnum.VILNIUS);

        childService.addChild(childDto);

    }
}
