package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.controller.KindergartenController;
import lt.vtmc.kindergarten.dao.ChildDao;
import lt.vtmc.kindergarten.dao.DistrictDao;
import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.dto.ChildDto;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import lt.vtmc.kindergarten.dto.UserDto;
import lt.vtmc.kindergarten.service.ChildService;
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
    KindergartenService kindergartenService;
    @Autowired
    DistrictDao districtDao;
    @Autowired
    ChildService childService;
    @Autowired
    ChildDao childDao;
    

    @Override
    public void run(String...args) throws Exception {
        userService.createUser(new UserDto("administratorius", "Adminas", "Adminaitis", 12345678989L, "Administratorius1", "ADMIN"));
        KindergartenDto kindergartenDto = new KindergartenDto();
        
        District district = new District();
        district.setTitle("Antakalnis");
        districtDao.save(district);
        
        kindergartenDto.setTitle("Pušaitė");
        kindergartenDto.setAddress("Bistryčios f. 3");
        kindergartenDto.setCity(CityEnum.VILNIUS);
        kindergartenDto.setPostalCode("10323");
        kindergartenDto.setPhoneNumber("852343902");
        kindergartenDto.setEmail("direktore@pusdassadte.vilnius.lm.lt");
        kindergartenDto.setWebsite("www.darzelispusdsaaite.lt");
        kindergartenDto.setCompanyCode("190025821");
        kindergartenDto.setDistrict(district);

        kindergartenService.addKindergarten(kindergartenDto);
        
        KindergartenDto kindergartenDto1 = new KindergartenDto();
        kindergartenDto1.setTitle("Boruzele");
        kindergartenDto1.setAddress("Bistryčios g. 10");
        kindergartenDto1.setCity(CityEnum.VILNIUS);
        kindergartenDto1.setPostalCode("10335");
        kindergartenDto1.setPhoneNumber("852343965");
        kindergartenDto1.setEmail("direktore@pusaitdasdase.vilnius.lm.lt");
        kindergartenDto1.setWebsite("www.darzelispddafusaite.lt");
        kindergartenDto1.setCompanyCode("190025431");
        kindergartenDto1.setDistrict(district);

        kindergartenService.addKindergarten(kindergartenDto1);
        
        KindergartenDto kindergartenDto2 = new KindergartenDto();
        kindergartenDto2.setTitle("Azuolelis");
        kindergartenDto2.setAddress("Bistryčios g. 1");
        kindergartenDto2.setCity(CityEnum.VILNIUS);
        kindergartenDto2.setPostalCode("10420");
        kindergartenDto2.setPhoneNumber("854543900");
        kindergartenDto2.setEmail("direktofda@pusaite.vilnius.lm.lt");
        kindergartenDto2.setWebsite("www.dardfflispusaite.lt");
        kindergartenDto2.setCompanyCode("190435890");
        kindergartenDto2.setDistrict(district);

        kindergartenService.addKindergarten(kindergartenDto2);
        
        KindergartenDto kindergartenDto3 = new KindergartenDto();
        kindergartenDto3.setTitle("Dobilelis");
        kindergartenDto3.setAddress("Bistrygios g. 3");
        kindergartenDto3.setCity(CityEnum.VILNIUS);
        kindergartenDto3.setPostalCode("10720");
        kindergartenDto3.setPhoneNumber("812343900");
        kindergartenDto3.setEmail("direkttye@pusaite.vilnius.lm.lt");
        kindergartenDto3.setWebsite("www.jirzelispusaite.lt");
        kindergartenDto3.setCompanyCode("191025890");
        kindergartenDto3.setDistrict(district);

        kindergartenService.addKindergarten(kindergartenDto3);
        
        KindergartenDto kindergartenDto4 = new KindergartenDto();
        kindergartenDto4.setTitle("Berzelis");
        kindergartenDto4.setAddress("Bisttreios g. 3");
        kindergartenDto4.setCity(CityEnum.VILNIUS);
        kindergartenDto4.setPostalCode("16620");
        kindergartenDto4.setPhoneNumber("833343900");
        kindergartenDto4.setEmail("direktore@pusaite.vilnidd.lm.lt");
        kindergartenDto4.setWebsite("www.darzelispusadde.lt");
        kindergartenDto4.setCompanyCode("190025800");
        kindergartenDto4.setDistrict(district);

        kindergartenService.addKindergarten(kindergartenDto4);
        
        KindergartenDto kindergartenDto5 = new KindergartenDto();
        kindergartenDto5.setTitle("Kelmelis");
        kindergartenDto5.setAddress("Bisddyčios g. 3");
        kindergartenDto5.setCity(CityEnum.VILNIUS);
        kindergartenDto5.setPostalCode("15340");
        kindergartenDto5.setPhoneNumber("852443900");
        kindergartenDto5.setEmail("direktore@ddfgusaite.vilnius.lm.lt");
        kindergartenDto5.setWebsite("www.darztyugispusaite.lt");
        kindergartenDto5.setCompanyCode("190995890");
        kindergartenDto5.setDistrict(district);

        kindergartenService.addKindergarten(kindergartenDto5);
        
        ChildDto childDto = new ChildDto();
        childDto.setFirstName("Rimas");
        childDto.setLastName("Povilaitis");
        childDto.setPersonalCode(3708433230L);
        childDto.setStreetAddress("Kedru g. 10");
        childDto.setCity(CityEnum.VILNIUS);
        
        childService.addChild(childDto);
        
        ChildDto childDto1 = new ChildDto();
        childDto1.setFirstName("Paulius");
        childDto1.setLastName("Rimaitis");
        childDto1.setPersonalCode(3708433243L);
        childDto1.setStreetAddress("Kedru g. 4");
        childDto1.setCity(CityEnum.VILNIUS);
        
        childService.addChild(childDto1);
    }
        
}

