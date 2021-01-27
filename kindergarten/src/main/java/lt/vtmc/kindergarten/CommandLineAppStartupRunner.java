package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.dto.UserFromService;
import lt.vtmc.kindergarten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(String...args) throws Exception {
        // FIXME investigate why this failes with data conversion error
        userService.createUser(new UserFromService("administratorius", "firstname", "lastname", 123456789L, "Administratorius1", "ADMIN"));
    }
}
