package lt.vtmc.kindergarten.config;


import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class DataSeeder {

    @Autowired
    private DistrictService districtService;

    @Autowired
    KindergartenService kindergartenService;

    @Autowired
    PersonService personService;

    @Autowired
    AgeRangeService ageRangeService;

    @Autowired
    UserService userService;

    @Autowired
    QueueService queueService;

    @Autowired
    ApplicationService applicationService;

    public District createDistrict(){
        District district = new District();
        district.setTitle("Antakalnis");
        district.setId(1L);
        districtService.addDistrict(new DistrictDto(district));
        return district;
    }

    public void cretePersons(){
        Person person = new Person();
        person.setFirstName("Edgaras");
        person.setLastName("Bujonauskas");
        person.setEmail("tadasbujonauskas@gmail.com");
        person.setCity(CityEnum.VILNIUS);
        person.setAddress("Kanklių g. 4");
        person.setPersonalCode("33333333333");
        person.setPhoneNumber("862412323");
        person.setPostalCode("10321");
        person.setTribeId("qwerty");
        personService.addPerson(new PersonDto(person));

        Person child = new Person();
        child.setFirstName("Jonukas");
        child.setLastName("Bujonauskas");
        child.setEmail(null);
        child.setCity(CityEnum.VILNIUS);
        child.setAddress("Kanklių g. 4");
        child.setPersonalCode("11111111111");
        child.setPhoneNumber(null);
        child.setPostalCode("10321");
        child.setTribeId("qwerty");
        personService.addPerson(new PersonDto(child));

        Person person2 = new Person();
        person2.setFirstName("Monika");
        person2.setLastName("Bujonauskienė");
        person2.setEmail("monika@gmail.com");
        person2.setCity(CityEnum.VILNIUS);
        person2.setAddress("Kanklių g. 4");
        person2.setPersonalCode("22222222222");
        person2.setPhoneNumber("862412322");
        person2.setPostalCode("10321");
        person2.setTribeId("qwerty");;
        personService.addPerson(new PersonDto(person2));

        Application application = new Application();
        Person person3 = new Person();
        person3.setFirstName("Ieva");
        person3.setLastName("Urbelienė");
        person3.setEmail("ieva@gmail.com");
        person3.setCity(CityEnum.OTHER);
        person3.setAddress("Vėjų g. 16");
        person3.setPersonalCode("66666666666");
        person3.setPhoneNumber("862412111");
        person3.setPostalCode("10321");
        person3.setTribeId("qwerty");
        personService.addPerson(new PersonDto(person3));

        Person child2 = new Person();
        child2.setFirstName("Marytė");
        child2.setLastName("Urbelytė");
        child2.setEmail(null);
        child2.setCity(CityEnum.OTHER);
        child2.setAddress("Vėjų g. 16");
        child2.setPersonalCode("55555555555");
        child2.setPhoneNumber(null);
        child2.setPostalCode("10321");
        child2.setTribeId("qwerty");
        personService.addPerson(new PersonDto(child2));

        Person person4 = new Person();
        person4.setFirstName("Simas");
        person4.setLastName("Urbelis");
        person4.setEmail("simas@gmail.com");
        person4.setCity(CityEnum.OTHER);
        person4.setAddress("Vėjų g. 16");
        person4.setPersonalCode("99999999999");
        person4.setPhoneNumber("862414444");
        person4.setPostalCode("10321");
        person4.setTribeId("qwerty");
        personService.addPerson(new PersonDto(person4));
    }

    public void createAgeRanges(){
        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(2);

        AgeRange ageRange2 = new AgeRange();
        ageRange2.setAgeMin(2);
        ageRange2.setAgeMax(3);

        ageRangeService.addAgeRange(new AgeRangeDto(ageRange));
        ageRangeService.addAgeRange(new AgeRangeDto(ageRange2));
    }

    public void createKindergartens(District district){
        Kindergarten kindergarten = new Kindergarten();
        kindergarten.setTitle("Pušaitė");
        kindergarten.setAddress("Gatvės g. 56");
        kindergarten.setCity(CityEnum.VILNIUS);
        kindergarten.setPostalCode("12546");
        kindergarten.setPhoneNumber("862405555");
        kindergarten.setEmail("darzelispusaite@gmail.com");
        kindergarten.setWebsite("www.darzelispusaite.lt");
        kindergarten.setCompanyCode("19555587");
        kindergarten.setDistrict(district);

        kindergartenService.addKindergarten(new KindergartenDto(kindergarten));

        Kindergarten kindergarten2 = new Kindergarten();
        kindergarten2.setTitle("Smalsučiai");
        kindergarten2.setAddress("Vėjų g. 12");
        kindergarten2.setCity(CityEnum.VILNIUS);
        kindergarten2.setPostalCode("12546");
        kindergarten2.setPhoneNumber("862404444");
        kindergarten2.setEmail("smalsuciai@gmail.com");
        kindergarten2.setWebsite("www.smalsuciai.lt");
        kindergarten2.setCompanyCode("19555888");
        kindergarten2.setDistrict(district);

        kindergartenService.addKindergarten(new KindergartenDto(kindergarten2));
    }

    public void createUsers(){
        userService.createGuardian("Petras", "Petrauskas");
        userService.createGuardian("Marija", "Pūkienė");
        userService.createGuardian("Justinas", "Bingelis");
    }

    public void createQueueWithOpeningDate(){
        Date date = new Date();
        queueService.addQueueWithOpeningDate(new QueueDtoWithOpeningDate(date));
    }

}
