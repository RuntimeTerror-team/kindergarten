package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.config.DataSeeder;
import lt.vtmc.kindergarten.dao.QueueDao;
import lt.vtmc.kindergarten.domain.AgeRange;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configuration
@PropertySource("classpath:application.properties")
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Value("${startup.enable.data.seed}")
    private boolean seedData;

    @Autowired
    UserService userService;

    @Autowired
    DataSeeder dataSeeder;

    @Autowired
    QueueDao queueDao;


    @Override
    public void run(String... args) throws Exception {
//        userService.createUser(new UserDto("administratorius", "Adminas", "Adminaitis", "12345678989", "Administratorius1", "ADMIN"));
        userService.createUser(new UserDto("administratorius", "Administratorius1", "ADMIN"));

        if (seedData) {
            District districtAntakalnis = dataSeeder.createDistrict("Antakalnis", 1L);
            District districtZirmunai = dataSeeder.createDistrict("Žirmūnai", 2L);
            dataSeeder.createUsers();
            dataSeeder.cretePersons();
            dataSeeder.createAgeRanges(1,2);
            dataSeeder.createAgeRanges(3,4);
            dataSeeder.createKindergartens(districtAntakalnis, districtZirmunai);
            dataSeeder.createQueueWithOpeningDate();
            dataSeeder.createGroupForKindergarten("19555587", 1, 2);
            dataSeeder.createGroupForKindergarten("19555587", 3, 4);
            dataSeeder.createApplication("39004180111","51504180332", "19555587",
                    false, false, true, true);
            dataSeeder.createApplication("39004180111","61602221111", "19555587",
                    false, false, true, false);
            dataSeeder.createApplication("48901110222","61707120444", "19555587",
                    true, true, true, false);
            dataSeeder.createApplication("48901110222","61707120478", "19555587",
                    true, true, true, false);
        }


    }

}
