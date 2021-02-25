package lt.vtmc.kindergarten.config;


import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;


@Component
public class DataSeeder {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private KindergartenService kindergartenService;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private PersonService personService;

    @Autowired
    private AgeRangeService ageRangeService;

    @Autowired
    private UserService userService;

    @Autowired
    private QueueService queueService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private AgeRangeDao ageRangeDao;


    public District createDistrict(String title, Long id) {
        District district = new District();
        district.setTitle(title);
        district.setId(id);
        districtService.addDistrict(new DistrictDto(district));
        return district;
    }

    public void cretePersons() {
        Role guardian = new Role();
        guardian.setType(RoleType.GUARDIAN);

        //Useris
        UserDtoFromAdmin edgarasUserForAdm = new UserDtoFromAdmin();
        edgarasUserForAdm.setFirstName("Edgaras");
        edgarasUserForAdm.setLastName("Bujonauskas");
        edgarasUserForAdm.setRole("GUARDIAN");

        userService.createUserFromAdmin(edgarasUserForAdm);
        User edgarasUser = userDao.findAll().stream().filter(user -> user.getUsername().contains("Edgaras")).findFirst().get();


        Person personEdgaras = new Person();
        personEdgaras.setFirstName("Edgaras");
        personEdgaras.setLastName("Bujonauskas");
        personEdgaras.setEmail("tadasbujonauskas@gmail.com");
        personEdgaras.setCity(CityEnum.VILNIUS);
        personEdgaras.setAddress("Kanklių g. 4");
        personEdgaras.setPersonalCode("39004180111");
        personEdgaras.setPhoneNumber("862412323");
        personEdgaras.setPostalCode("10321");
        personEdgaras.setUser(edgarasUser);

        PersonDto personEdgarasDto = new PersonDto(personEdgaras);

        PersonUserDto personEdgarasUser = new PersonUserDto(personEdgarasDto, edgarasUser.getUsername());
        personService.addPersonWithUsername(personEdgarasUser);


        Person child = new Person();
        child.setFirstName("JONUKAS");
        child.setLastName("Bujonauskas");
        child.setEmail(null);
        child.setCity(CityEnum.VILNIUS);
        child.setAddress("Kanklių g. 4");
        child.setPersonalCode("51504180332");
        child.setPhoneNumber(null);
        child.setPostalCode("10321");
        personService.addPerson(new PersonDto(child));


        Person child2 = new Person();
        child2.setFirstName("MARYTE");
        child2.setLastName("Bujonauskaitė");
        child2.setEmail(null);
        child2.setCity(CityEnum.VILNIUS);
        child2.setAddress("Kanklių g. 4");
        child2.setPersonalCode("61602221111");
        child2.setPhoneNumber(null);
        child2.setPostalCode("10321");
        child2.setTribeId("fam1");
        personService.addPerson(new PersonDto(child2));


        //Useris
        UserDtoFromAdmin monikaUserForAdmin = new UserDtoFromAdmin();
        monikaUserForAdmin.setFirstName("Monika");
        monikaUserForAdmin.setLastName("Bujonauskienė");
        monikaUserForAdmin.setRole("GUARDIAN");

        userService.createUserFromAdmin(monikaUserForAdmin);
        User monikaUser = userDao.findAll().stream().filter(user -> user.getUsername().contains("Monika")).findFirst().get();
        guardian.addUser(monikaUser);

        Person person2 = new Person();
        person2.setFirstName("Monika");
        person2.setLastName("Bujonauskienė");
        person2.setEmail("monika@gmail.com");
        person2.setCity(CityEnum.VILNIUS);
        person2.setAddress("Kanklių g. 4");
        person2.setPersonalCode("49004170458");
        person2.setPhoneNumber("862412322");
        person2.setPostalCode("10321");
        person2.setUser(monikaUser);


        PersonDto monikaUserDto = new PersonDto(person2);

        PersonUserDto personMonikaUser = new PersonUserDto(monikaUserDto, monikaUser.getUsername());
        personService.addPersonWithUsername(personMonikaUser);


        //Useris
        UserDtoFromAdmin ievaUserForAdmin = new UserDtoFromAdmin();
        ievaUserForAdmin.setFirstName("Ieva");
        ievaUserForAdmin.setLastName("Urbelienė");
        ievaUserForAdmin.setRole("GUARDIAN");

        userService.createUserFromAdmin(ievaUserForAdmin);
        User ievaUser = userDao.findAll().stream().filter(user -> user.getUsername().contains("Ieva")).findFirst().get();
        guardian.addUser(ievaUser);

        Person person3 = new Person();
        person3.setFirstName("Ieva");
        person3.setLastName("Urbelienė");
        person3.setEmail("ieva@gmail.com");
        person3.setCity(CityEnum.OTHER);
        person3.setAddress("Vėjų g. 16");
        person3.setPersonalCode("48901110222");
        person3.setPhoneNumber("862412111");
        person3.setPostalCode("10321");
        person3.setTribeId("fam2");
        personService.addPerson(new PersonDto(person3));

        Person child3 = new Person();
        child3.setFirstName("IGLUTė");
        child3.setLastName("Urbelytė");
        child3.setEmail(null);
        child3.setCity(CityEnum.OTHER);
        child3.setAddress("Vėjų g. 16");
        child3.setPersonalCode("61707120444");
        child3.setPhoneNumber(null);
        child3.setPostalCode("10321");
        child3.setTribeId("fam2");
        personService.addPerson(new PersonDto(child3));

        Person child4 = new Person();
        child4.setFirstName("IGLUTė");
        child4.setLastName("ZBIGNAITė");
        child4.setEmail(null);
        child4.setCity(CityEnum.OTHER);
        child4.setAddress("Vėjų g. 16");
        child4.setPersonalCode("61707120478");
        child4.setPhoneNumber(null);
        child4.setPostalCode("10321");
        child4.setTribeId("fam2");
        personService.addPerson(new PersonDto(child4));

        Person person4 = new Person();
        person4.setFirstName("Simas");
        person4.setLastName("Urbelis");
        person4.setEmail("simas@gmail.com");
        person4.setCity(CityEnum.OTHER);
        person4.setAddress("Vėjų g. 16");
        person4.setPersonalCode("38905200112");
        person4.setPhoneNumber("862414444");
        person4.setPostalCode("10321");
        person4.setTribeId("fam2");
        personService.addPerson(new PersonDto(person4));
    }

    public void createAgeRanges(int min, int max) {
        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(min);
        ageRange.setAgeMax(max);

        ageRangeService.addAgeRange(new AgeRangeDto(ageRange));
    }



    public void createKindergartens(District district, District district2) {
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

        Kindergarten kindergarten3 = new Kindergarten();
        kindergarten3.setTitle("Nykštukų darželis lopšelis");
        kindergarten3.setAddress("Kažinkokia g. 56");
        kindergarten3.setCity(CityEnum.VILNIUS);
        kindergarten3.setPostalCode("12546");
        kindergarten3.setPhoneNumber("862406666");
        kindergarten3.setEmail("darzelisnykstukai@gmail.com");
        kindergarten3.setWebsite("www.darzelisnykstukai.lt");
        kindergarten3.setCompanyCode("19555333");
        kindergarten3.setDistrict(district2);

        kindergartenService.addKindergarten(new KindergartenDto(kindergarten3));
    }

    public void createGroupForKindergarten(String kindergartenCompanyCode, int minAgeRange, int maxAgeRange){
        Kindergarten kindergarten = kindergartenDao.findByCompanyCode(kindergartenCompanyCode);
        AgeRange ageRange = ageRangeDao.findByAgeMinAndAgeMax(minAgeRange,maxAgeRange);

        Group group = new Group();
        group.setChildrenCount(10);
        group.setKindergartenId(kindergarten);
        group.setAgeRange(ageRange);

        groupDao.save(group);

        kindergartenDao.save(kindergarten);

    }

    public void createUsers() {
        userService.createGuardian("Petras", "Petrauskas");
        userService.createGuardian("Marija", "Pūkienė");
        userService.createGuardian("Justinas", "Bingelis");
    }

    public void createQueueWithOpeningDate() {
        Date date = new Date();
        queueService.addQueueWithOpeningDate(new QueueDtoWithOpeningDate(date));
    }

    public void createApplication(String parentPersonalCode, String childPersonalCode, String kindergarten1CompanyCode,
                                  Boolean isAdopted, Boolean isGuardianStudent, Boolean isMultiChild, Boolean isDisabled) {

        Person parent1 = personDao.findByPersonalCode(parentPersonalCode);
        Person child = personDao.findByPersonalCode(childPersonalCode);
        Kindergarten kindergarten1 = kindergartenDao.findByCompanyCode(kindergarten1CompanyCode);

        ApplicationCreationDto application = new ApplicationCreationDto();
        application.setDate(new Date());
        application.setIsAdopted(isAdopted);
        application.setIsGuardianStudent(isGuardianStudent);
        application.setIsMultiChild(isMultiChild);
        application.setIsGuardianDisabled(isDisabled);

        application.setFirstParentId(parent1.getId());
        application.setChildId(child.getId());

        application.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergarten1.getId());
        }});

        applicationService.addApplication(application);

    }

}
