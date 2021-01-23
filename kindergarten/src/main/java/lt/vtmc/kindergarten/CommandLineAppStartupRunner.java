package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.User.dao.User;
import lt.vtmc.kindergarten.User.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserDao userDao;

    @Override
    public void run(String...args) throws Exception {
        User admin = new User("administratorius", "firstname", "lastname", 123456789L, "Administratorius1", "ADMIN");
        userDao.save(admin);
    }
}
