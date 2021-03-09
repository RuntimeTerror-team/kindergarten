package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.domain.Person;
import lt.vtmc.kindergarten.dto.UserDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

    @Autowired
    private PersonDao personDao;

    public UserDataDto getUserData(String username) {

        Person person = personDao.findPersonByUsername(username);

        UserDataDto udd = new UserDataDto();
        udd.setUsername(username);
        udd.setFirstName(person.getFirstName());
        udd.setLastName(person.getLastName());
        udd.setEmail(person.getEmail());

        return udd;
    }
}
