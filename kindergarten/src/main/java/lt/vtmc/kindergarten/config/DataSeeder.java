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
    private GroupService groupService;

    @Autowired
    private AgeRangeDao ageRangeDao;


    public District createDistrict(String title, Long id) {
        District district = new District();
        district.setTitle(title);
        district.setId(id);
        districtService.addDistrict(new DistrictDto(district));
        return district;
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

    public void createGroupForKindergarten(String kindergartenCompanyCode, int minAgeRange, int maxAgeRange) {
        Kindergarten kindergarten = kindergartenDao.findByCompanyCode(kindergartenCompanyCode);

        AgeRange ageRange = ageRangeDao.findByAgeMinAndAgeMax(minAgeRange, maxAgeRange);

        Group group = new Group();
        group.setChildrenCount(1);
        group.setKindergartenId(kindergarten);
        group.setAgeRange(ageRange);

        kindergartenDao.save(kindergarten);
        groupDao.save(group);

        kindergarten.addGroup(group);
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


    public void cretePersons() {
        Role guardian = new Role();
        guardian.setType(RoleType.GUARDIAN);

        //User EDGARAS *********************************************************************************************************
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


        //JONUKAS
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

        Person jonukas = personDao.findByPersonalCode("51504180332");
        jonukas.setTribeId(personDao.findByPersonalCode("39004180111").getTribeId());
        personDao.save(jonukas);

        //MARYTĖ
        Person child2 = new Person();
        child2.setFirstName("MARYTE");
        child2.setLastName("Bujonauskaitė");
        child2.setEmail(null);
        child2.setCity(CityEnum.VILNIUS);
        child2.setAddress("Kanklių g. 4");
        child2.setPersonalCode("61602221111");
        child2.setPhoneNumber(null);
        child2.setPostalCode("10321");
        personService.addPerson(new PersonDto(child2));

        Person maryte = personDao.findByPersonalCode("61602221111");
        maryte.setTribeId(personDao.findByPersonalCode("39004180111").getTribeId());
        personDao.save(maryte);



        //User ALVYDAS *********************************************************************************************************
        UserDtoFromAdmin alvydasUserForAdm = new UserDtoFromAdmin();
        alvydasUserForAdm.setFirstName("Alvydas");
        alvydasUserForAdm.setLastName("Narkūnas");
        alvydasUserForAdm.setRole("GUARDIAN");

        userService.createUserFromAdmin(alvydasUserForAdm);
        User alvydasUser = userDao.findAll().stream().filter(user -> user.getUsername().contains("Alvydas")).findFirst().get();

        Person personAlvydas = new Person();
        personAlvydas.setFirstName("Alvydas");
        personAlvydas.setLastName("Narkūnas");
        personAlvydas.setEmail("alvydas@gmail.com");
        personAlvydas.setCity(CityEnum.VILNIUS);
        personAlvydas.setAddress("Giedraičių g. 69");
        personAlvydas.setPersonalCode("38406160121");
        personAlvydas.setPhoneNumber("862412180");
        personAlvydas.setPostalCode("22822");
        personAlvydas.setUser(alvydasUser);

        PersonDto personAlvydasDto = new PersonDto(personAlvydas);
        PersonUserDto personAlvydasUser = new PersonUserDto(personAlvydasDto, alvydasUser.getUsername());
        personService.addPersonWithUsername(personAlvydasUser);


        //GABRIELIUS
        Person childGabrielius = new Person();
        childGabrielius.setFirstName("GABRIELIUS");
        childGabrielius.setLastName("Narkūnas");
        childGabrielius.setEmail(null);
        childGabrielius.setCity(CityEnum.VILNIUS);
        childGabrielius.setAddress("Giedraičių g. 69");
        childGabrielius.setPersonalCode("51910280322");
        childGabrielius.setPhoneNumber(null);
        childGabrielius.setPostalCode("22822");
        personService.addPerson(new PersonDto(childGabrielius));

        Person gabrielius = personDao.findByPersonalCode("51910280322");
        gabrielius.setTribeId(personDao.findByPersonalCode("38406160121").getTribeId());
        personDao.save(gabrielius);

        //ATĖNĖ
        Person childAtene = new Person();
        childAtene.setFirstName("ATĖNĖ");
        childAtene.setLastName("Narkūnaitė");
        childAtene.setEmail(null);
        childAtene.setCity(CityEnum.VILNIUS);
        childAtene.setAddress("Giedraičių g. 69");
        childAtene.setPersonalCode("51910281112");
        childAtene.setPhoneNumber(null);
        childAtene.setPostalCode("22822");
        personService.addPerson(new PersonDto(childAtene));

        Person atene = personDao.findByPersonalCode("51910281112");
        atene.setTribeId(personDao.findByPersonalCode("38406160121").getTribeId());
        personDao.save(atene);


        //Useris MONIKA ZUBRIENĖ *********************************************************************************************************
        UserDtoFromAdmin monikaUserForAdmin = new UserDtoFromAdmin();
        monikaUserForAdmin.setFirstName("Monika");
        monikaUserForAdmin.setLastName("Zubrienė");
        monikaUserForAdmin.setRole("GUARDIAN");

        userService.createUserFromAdmin(monikaUserForAdmin);
        User monikaUser = userDao.findAll().stream().filter(user -> user.getUsername().contains("Monika")).findFirst().get();

        Person person2 = new Person();
        person2.setFirstName("Monika");
        person2.setLastName("Zubrienė");
        person2.setEmail("monika@gmail.com");
        person2.setCity(CityEnum.OTHER);
        person2.setAddress("Krokuvos g. 51");
        person2.setPersonalCode("49004170458");
        person2.setPhoneNumber("862412322");
        person2.setPostalCode("11185");
        person2.setUser(monikaUser);

        PersonDto monikaUserDto = new PersonDto(person2);
        PersonUserDto personMonikaUser = new PersonUserDto(monikaUserDto, monikaUser.getUsername());
        personService.addPersonWithUsername(personMonikaUser);


        //IGLUTĖ ZUBRYTĖ
        Person child4 = new Person();
        child4.setFirstName("IGLUTĖ");
        child4.setLastName("Zubrytė");
        child4.setEmail(null);
        child4.setCity(CityEnum.OTHER);
        child4.setAddress("Krokuvos g. 51");
        child4.setPersonalCode("61507120478");
        child4.setPhoneNumber(null);
        child4.setPostalCode("11185");
        personService.addPerson(new PersonDto(child4));

        Person igluteZub = personDao.findByPersonalCode("61507120478");
        igluteZub.setTribeId(personDao.findByPersonalCode("49004170458").getTribeId());
        personDao.save(igluteZub);


        //ERIKAS ZUBRUS
        Person erikasChild = new Person();
        erikasChild.setFirstName("ERIKAS");
        erikasChild.setLastName("Zubrus");
        erikasChild.setEmail(null);
        erikasChild.setCity(CityEnum.OTHER);
        erikasChild.setAddress("Krokuvos g. 51");
        erikasChild.setPersonalCode("51512218211");
        erikasChild.setPhoneNumber(null);
        erikasChild.setPostalCode("11185");
        personService.addPerson(new PersonDto(erikasChild));

        Person erikasZub = personDao.findByPersonalCode("51512218211");
        erikasZub.setTribeId(personDao.findByPersonalCode("49004170458").getTribeId());
        personDao.save(erikasZub);


        //Useris IEVA URBELIENĖ *********************************************************************************************************
        UserDtoFromAdmin ievaUserForAdmin = new UserDtoFromAdmin();
        ievaUserForAdmin.setFirstName("Ieva");
        ievaUserForAdmin.setLastName("Urbelienė");
        ievaUserForAdmin.setRole("GUARDIAN");

        userService.createUserFromAdmin(ievaUserForAdmin);
        User ievaUser = userDao.findAll().stream().filter(user -> user.getUsername().contains("Ieva")).findFirst().get();

        Person person3 = new Person();
        person3.setFirstName("Ieva");
        person3.setLastName("Urbelienė");
        person3.setEmail("ieva@gmail.com");
        person3.setCity(CityEnum.OTHER);
        person3.setAddress("Vėjų g. 16");
        person3.setPersonalCode("48901110222");
        person3.setPhoneNumber("862412111");
        person3.setPostalCode("98334");
        person3.setUser(ievaUser);

        PersonDto personIevaDto = new PersonDto(person3);
        PersonUserDto personIevaUser = new PersonUserDto(personIevaDto, ievaUser.getUsername());
        personService.addPersonWithUsername(personIevaUser);


        //IGLUTĖ URBELYTĖ
        Person child3 = new Person();
        child3.setFirstName("IGLUTĖ");
        child3.setLastName("Urbelytė");
        child3.setEmail(null);
        child3.setCity(CityEnum.OTHER);
        child3.setAddress("Vėjų g. 16");
        child3.setPersonalCode("61507120444");
        child3.setPhoneNumber(null);
        child3.setPostalCode("98334");
        personService.addPerson(new PersonDto(child3));

        Person igluteUrbelyte = personDao.findByPersonalCode("61507120444");
        igluteUrbelyte.setTribeId(personDao.findByPersonalCode("48901110222").getTribeId());
        personDao.save(igluteUrbelyte);

        //POVILIUKAS URBELIS
        Person poviliukasChild = new Person();
        poviliukasChild.setFirstName("POVILIUKAS");
        poviliukasChild.setLastName("Urbelis");
        poviliukasChild.setEmail(null);
        poviliukasChild.setCity(CityEnum.OTHER);
        poviliukasChild.setAddress("Vėjų g. 16");
        poviliukasChild.setPersonalCode("62001011211");
        poviliukasChild.setPhoneNumber(null);
        poviliukasChild.setPostalCode("98334");
        personService.addPerson(new PersonDto(poviliukasChild));

        Person poviliukasUrbelis = personDao.findByPersonalCode("62001011211");
        poviliukasUrbelis.setTribeId(personDao.findByPersonalCode("48901110222").getTribeId());
        personDao.save(poviliukasUrbelis);

        //TĖTIS SIMAS URBELIS
        Person personSimas = new Person();
        personSimas.setFirstName("Simas");
        personSimas.setLastName("Urbelis");
        personSimas.setEmail("simas@gmail.com");
        personSimas.setCity(CityEnum.OTHER);
        personSimas.setAddress("Vėjų g. 16");
        personSimas.setPersonalCode("38905200112");
        personSimas.setPhoneNumber("862414444");
        personSimas.setPostalCode("98334");
        personService.addPerson(new PersonDto(personSimas));

        Person simasUrbelis = personDao.findByPersonalCode("38905200112");
        simasUrbelis.setTribeId(personDao.findByPersonalCode("48901110222").getTribeId());
        personDao.save(simasUrbelis);

    }

}
