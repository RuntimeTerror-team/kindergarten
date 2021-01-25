package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.dao.UserDao;
import lt.vtmc.kindergarten.domain.Role;
import lt.vtmc.kindergarten.domain.RoleType;
import lt.vtmc.kindergarten.dto.UserFromService;
import lt.vtmc.kindergarten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Override
    public void run(String...args) throws Exception {
        userService.createUser(new UserFromService("administratorius", "firstname", "lastname", 123456789L, "Administratorius1", "ADMIN"));
        userService.createUser(new UserFromService("asas", "firstname", "lastname", 123456789L, "asas", "ADMIN"));
    }
}
