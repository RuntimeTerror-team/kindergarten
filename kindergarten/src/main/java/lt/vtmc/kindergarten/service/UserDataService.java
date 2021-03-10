package lt.vtmc.kindergarten.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.domain.Person;
import lt.vtmc.kindergarten.dto.UserDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class UserDataService {

    @Autowired
    private PersonDao personDao;

    public byte[] getUserData(String username) throws JsonProcessingException {
        UserDataDto user = getUserDto(username);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

        return jsonString.getBytes(StandardCharsets.UTF_8);
    }

    private UserDataDto getUserDto(String username) {

        Person person = personDao.findPersonByUsername(username);

        UserDataDto user = new UserDataDto();

        user.setPrisijungimo_vardas(username);

        if (person != null) {
            user.setVardas(person.getFirstName());
            user.setPavardė(person.getLastName());
            user.setAsmens_kodas(person.getPersonalCode());
            user.setAdresas(person.getAddress());
            user.setMiestas(person.getCity().toString());
            user.setPašto_kodas(person.getPostalCode());
            user.setTelefono_numeris(person.getPhoneNumber());
            user.setEl_paštas(person.getEmail());
        }
        return user;
    }
}
