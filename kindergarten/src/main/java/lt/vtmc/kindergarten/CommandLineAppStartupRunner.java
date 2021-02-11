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
    PersonService personService;

    @Autowired
    AgeRangeService ageRangeService;


    @Override
    public void run(String...args) throws Exception {
        userService.createUser(new UserDto("administratorius", "Adminas", "Adminaitis", "12345678989", "Administratorius1", "ADMIN"));

//
//        District district = new District();
//        district.setTitle("Antakalnis");
//        districtService.addDistrict(new DistrictDto(district));
//
//        Person person = new Person();
//        person.setFirstName("Tadas");
//        person.setLastName("Bujonauskas");
//        person.setEmail("tadasbujonauskas@gmail.com");
//        person.setCity(CityEnum.VILNIUS);
//        person.setAddress("Kankliu g. 4");
//        person.setPersonalCode("38005201234");
//        person.setPhoneNumber("862412323");
//        person.setPostalCode("10321");
//        personService.addPerson(new PersonDto(person));
//
//        Person child = new Person();
//        child.setFirstName("Tadukas");
//        child.setLastName("Bujonauskas");
//        child.setEmail(null);
//        child.setCity(CityEnum.VILNIUS);
//        child.setAddress("Kankliu g. 4");
//        child.setPersonalCode("18005201234");
//        child.setPhoneNumber(null);
//        child.setPostalCode("10321");
//        personService.addPerson(new PersonDto(child));
//
//        Person person2 = new Person();
//        person2.setFirstName("Monika");
//        person2.setLastName("Bujonauskienė");
//        person2.setEmail("monika@gmail.com");
//        person2.setCity(CityEnum.VILNIUS);
//        person2.setAddress("Kankliu g. 4");
//        person2.setPersonalCode("68005201234");
//        person2.setPhoneNumber("862412322");
//        person2.setPostalCode("10321");
//        personService.addPerson(new PersonDto(person2));
//
//        AgeRange ageRange = new AgeRange();
//        ageRange.setAgeMin(1);
//        ageRange.setAgeMax(2);
//
//        ageRangeService.addAgeRange(new AgeRangeDto(ageRange));
//
//
//


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
