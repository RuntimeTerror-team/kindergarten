package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.dto.UserDto;
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
        userService.createUser(new UserDto("administratorius", "Adminas", "Adminaitis", 12345678989L, "Administratorius1", "ADMIN"));

    }
}
