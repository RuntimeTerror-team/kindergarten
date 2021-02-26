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
//            dataSeeder.createUsers();

            dataSeeder.cretePersons();

            dataSeeder.createAgeRanges(1, 2);
            dataSeeder.createAgeRanges(3, 4);
            dataSeeder.createAgeRanges(4, 5);
            dataSeeder.createAgeRanges(5, 6);
            dataSeeder.createAgeRanges(6, 7);

            dataSeeder.createKindergartens(districtAntakalnis, districtZirmunai);

            dataSeeder.createQueueWithOpeningDate();

            //Darželis Pušaitė
//            dataSeeder.createGroupForKindergarten("19555587", 4, 5,0);
//            dataSeeder.createGroupForKindergarten("19555587", 5, 6,0);
            dataSeeder.createGroupForKindergarten("19555587", 6, 7,3);

//            //Darželis Smalsučiai
//            dataSeeder.createGroupForKindergarten("19555888", 1, 2,0);
//            dataSeeder.createGroupForKindergarten("19555888", 4, 5,0);
//
//            //Darželis Nykštukai
//            dataSeeder.createGroupForKindergarten("19555333", 1, 2,0);


            //Jonukas
            dataSeeder.createApplication("39004180111", "51504180332", "19555587",
                    false, false, true, true);
            //Maryte
            dataSeeder.createApplication("39004180111", "61602221111", "19555587",
                    false, false, true, false);

            //Gabrielius
            dataSeeder.createApplication("38406160121", "51910280322", "19555587",
                    true, true, true, false);
            //Atene
            dataSeeder.createApplication("38406160121", "51910281112", "19555587",
                    true, true, true, false);

            //Iglute
            dataSeeder.createApplication("49004170458", "61507120478", "19555587",
                    true, true, true, false);
            //Erikas
            dataSeeder.createApplication("49004170458", "51512218211", "19555587",
                    true, true, true, false);


            //IgluteUrbelyte
            dataSeeder.createApplication("48901110222", "61507120444", "19555587",
                    true, true, true, false);
            //Poviliukas
            dataSeeder.createApplication("48901110222", "62001011211", "19555587",
                    true, true, true, false);


//            //Jonukas
//            dataSeeder.createApplication("39004180111", "51504180332", "19555587",
//                    false, false, true, true);
//            //Maryte
//            dataSeeder.createApplication("39004180111", "61602221111", "19555587",
//                    false, false, true, false);
//
//            //Gabrielius
//            dataSeeder.createApplication("38406160121", "51910280322", "19555888",
//                    true, true, true, false);
//            //Atene
//            dataSeeder.createApplication("38406160121", "51910281112", "19555888",
//                    true, true, true, false);
//
//            //Iglute
//            dataSeeder.createApplication("49004170458", "61507120478", "19555587",
//                    true, true, true, false);
//            //Erikas
//            dataSeeder.createApplication("49004170458", "51512218211", "19555587",
//                    true, true, true, false);
//
//
//            //IgluteUrbelyte
//            dataSeeder.createApplication("48901110222", "61507120444", "19555587",
//                    true, true, true, false);
//            //Poviliukas
//            dataSeeder.createApplication("48901110222", "62001011211", "19555333",
//                    true, true, true, false);
        }


    }

}
