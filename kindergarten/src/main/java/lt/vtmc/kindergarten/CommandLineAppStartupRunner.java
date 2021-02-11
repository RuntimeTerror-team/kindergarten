package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.service.*;
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
    PersonTestService personTestService;

    @Autowired
    PersonTestService personService;

    @Autowired
    AgeRangeService ageRangeService;


    @Override
    public void run(String...args) throws Exception {
        userService.createUser(new UserDto("administratorius", "Adminas", "Adminaitis", "12345678989", "Administratorius1", "ADMIN"));


        District district = new District();
        district.setTitle("Antakalnis");
        districtService.addDistrict(new DistrictDto(district));


        PersonTest person = new PersonTest();
        person.setFirstName("Tadas");
        person.setLastName("Bujonauskas");
        person.setEmail("tadasbujonauskas@gmail.com");
        person.setCity(CityEnum.VILNIUS);
        person.setAddress("Kankliu g. 4");
        person.setPersonalCode("38005201234");
        person.setPhoneNumber("862412323");
        person.setPostalCode("10321");
        personTestService.addPerson(new PersonTestDto(person));

        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(2);

        ageRangeService.addAgeRange(new AgeRangeDto(ageRange));




//        Kindergarten kindergarten = new Kindergarten();
//        kindergarten.setTitle("Pušaitė");
//        kindergarten.setAddress("Gatvės g. 56");
//        kindergarten.setCity(CityEnum.VILNIUS);
//        kindergarten.setPostalCode("12546");
//        kindergarten.setPhoneNumber("862405555");
//        kindergarten.setEmail("darzelispusaite@gmail.com");
//        kindergarten.setWebsite("www.darzelispusaite.lt");
//        kindergarten.setCompanyCode("19555587");
//        kindergarten.setDistrict(district);
//
//        kindergartenService.addKindergarten(new KindergartenDto(kindergarten));



    }
        
}
