package lt.vtmc.kindergarten;

import ch.qos.logback.classic.Logger;
import lt.vtmc.kindergarten.config.DataSeeder;
import lt.vtmc.kindergarten.controller.UserController;
import lt.vtmc.kindergarten.dao.PermissionForESDao;
import lt.vtmc.kindergarten.dao.QueueDao;

import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.domain.PermissionForES;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.service.*;
import org.flywaydb.core.Flyway;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.IntStream;


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
    
    @Autowired
    PermissionForESDao permissionForESDao;

    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(new UserDto("administratorius", "Administratorius1", "ADMIN"));

        if (seedData) {
            District districtAntakalnis = dataSeeder.createDistrict("Antakalnis", 1L);
            District districtZirmunai = dataSeeder.createDistrict("Žirmūnai", 2L);


            dataSeeder.createAgeRanges(1, 2);
            dataSeeder.createAgeRanges(2, 3);
            dataSeeder.createAgeRanges(3, 4);
            dataSeeder.createAgeRanges(4, 5);
            dataSeeder.createAgeRanges(5, 6);
            dataSeeder.createAgeRanges(6, 7);


            dataSeeder.createKindergartens(districtAntakalnis, districtZirmunai);

            dataSeeder.createQueueWithOpeningDate();

            //Darželis Pušaitė
            dataSeeder.createGroupForKindergarten("19555587", 1, 2, 25);
            dataSeeder.createGroupForKindergarten("19555587", 2, 3,15);
            dataSeeder.createGroupForKindergarten("19555587", 3, 4,20);
            dataSeeder.createGroupForKindergarten("19555587", 4, 5,20);
            dataSeeder.createGroupForKindergarten("19555587", 5, 6,20);

            //Darželis Smalsučiai
            dataSeeder.createGroupForKindergarten("19555589", 1, 2, 14);
            dataSeeder.createGroupForKindergarten("19555589", 3, 4,10);
            dataSeeder.createGroupForKindergarten("19555589", 4, 5,13);
//            dataSeeder.createGroupForKindergarten("19555888", 4, 5,0);
//
            //Darželis Nykštukai
            dataSeeder.createGroupForKindergarten("19555590", 6, 7,25);
            dataSeeder.createGroupForKindergarten("19555590", 2, 3,20);
            dataSeeder.createGroupForKindergarten("19555590", 3, 4,22);
            dataSeeder.createGroupForKindergarten("19555590", 4, 5,8);


            dataSeeder.createGroupForKindergarten("19555591", 1, 2, 25);
            dataSeeder.createGroupForKindergarten("19555591", 2, 3,15);
            dataSeeder.createGroupForKindergarten("19555591", 3, 4,20);
            dataSeeder.createGroupForKindergarten("19555591", 4, 5,20);
            dataSeeder.createGroupForKindergarten("19555591", 5, 6,20);


            dataSeeder.createGroupForKindergarten("19555592", 1, 2, 25);
            dataSeeder.createGroupForKindergarten("19555592", 2, 3,15);
            dataSeeder.createGroupForKindergarten("19555592", 3, 4,18);
            dataSeeder.createGroupForKindergarten("19555592", 4, 5,20);
            dataSeeder.createGroupForKindergarten("19555592", 5, 6,16);


            // Create children
            // Create parents


            List<String> guardianPersonalCodes = new ArrayList<>();
            for ( int i =0; i<1000; i++){
                String createdPersonalCode = dataSeeder.createPerson(i, "GUARDIAN");
                guardianPersonalCodes.add(createdPersonalCode);
            }

            List<String> childrenPersonalCodes = new ArrayList<>();
            for ( int i = 0; i<3000; i++) {
                String createdPersonalCode = dataSeeder.createPerson(i, "NON_GUARDIAN");
                childrenPersonalCodes.add(createdPersonalCode);
            }

            ListIterator<String> guardianIterator = guardianPersonalCodes.listIterator();
            while(guardianIterator.hasNext()){
                String guardianPersonalCode = guardianIterator.next();
                ListIterator<String> childrenIterator = childrenPersonalCodes.listIterator();
                int childCount = 0;
                while(childrenIterator.hasNext()){
                    if(childCount<4) {
                        dataSeeder.createApplication(
                                guardianPersonalCode,
                                childrenIterator.next(),
                                "19555587",
                                "19555588",
                                "19555589",
                                "19555590",
                                "19555591",
                                false,
                                false,
                                true,
                                true);

                        childCount++;
                        childrenIterator.remove();
                        logger.info("Tevas id: " + guardianPersonalCode + " sukure prasyma");
                    } else {
                        break;
                    }
                }
                guardianIterator.remove();
            }
        }

        
        PermissionForES permission = new PermissionForES();
        permission.setId();
        permission.setIsAllowed(false);
        permissionForESDao.save(permission);

    }

}
