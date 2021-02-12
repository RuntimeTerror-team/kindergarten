package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.config.DataSeeder;
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
    @Value( "${startup.enable.data.seed}" )
    private boolean seedData;

    @Autowired
    UserService userService;

    @Autowired
    DataSeeder dataSeeder;


    @Override
    public void run(String...args) throws Exception {
//        userService.createUser(new UserDto("administratorius", "Adminas", "Adminaitis", "12345678989", "Administratorius1", "ADMIN"));
        userService.createUser(new UserDto("administratorius", "Administratorius1", "ADMIN"));

        if(seedData){
            District district = dataSeeder.createDistrict();
            dataSeeder.cretePersons();
            dataSeeder.createAgeRanges();
//            dataSeeder.createKindergarten(district);
        }




    }
        
}
