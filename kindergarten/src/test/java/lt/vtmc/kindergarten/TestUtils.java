package lt.vtmc.kindergarten;


import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.QueueDtoFromAdmin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public final class TestUtils {

    public static District createDefaultDistrict(String title) {
        District district = new District();
        district.setTitle(title);
        return district;
    }

    public static Kindergarten createDefaultKindergarten(String companyCode) {
        Kindergarten kindergarten = new Kindergarten();
        kindergarten.setTitle("Pušaitė");
        kindergarten.setAddress("Gatves g. 56");
        kindergarten.setCity(CityEnum.VILNIUS);
        kindergarten.setPostalCode("12546");
        kindergarten.setPhoneNumber("862403523");
        kindergarten.setEmail("darzelispusaite@gmail.com");
        kindergarten.setWebsite("www.darzelis.lt");
        kindergarten.setCompanyCode(companyCode);

        return kindergarten;
    }

    public static Person createDefaultPerson(String personalCode) {
        Person person = new Person();
        person.setFirstName("Antanas");
        person.setLastName("Antanaitis");
        person.setPersonalCode(personalCode);
        person.setAddress("Kanklių g.4");
        person.setCity(CityEnum.VILNIUS);
        person.setPostalCode("12355");
        return person;
    }

    public static ApplicationCreationDto createDefaultApplicationDto() {
        ApplicationCreationDto applicationDto = new ApplicationCreationDto();
        applicationDto.setDate(new Date());
        applicationDto.setIsAdopted(true);
        applicationDto.setIsGuardianDisabled(false);
        applicationDto.setIsMultiChild(false);
        applicationDto.setIsGuardianStudent(false);

        return applicationDto;
    }

    public static Group createDefaultGroup(Kindergarten kindergarten) {
        Group group = new Group();
        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(2);
        group.setAgeRange(ageRange);
        group.setKindergartenId(kindergarten);
        group.setChildrenCount(10);
        group.setTitle("Pukelis");

        return group;
    }

    public static QueueDtoFromAdmin createDefaultQueue() {
        QueueDtoFromAdmin queue = new QueueDtoFromAdmin();
        queue.setOpeningDate(new Date());
        return queue;
    }

}
