package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.dao.UserDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.PersonDto;
import lt.vtmc.kindergarten.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Transactional
    public void addPerson(@Valid PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setPersonalCode(personDto.getPersonalCode());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setAddress(personDto.getAddress());
        person.setCity(personDto.getCityEnum());
        person.setPostalCode(personDto.getPostalCode());
        person.setEmail(personDto.getEmail());

        personDao.save(person);
    }

    @Transactional
    public void updatePerson(Long id, PersonDto personDto) {
        Person person = personDao.getOne(id);

        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setPersonalCode(personDto.getPersonalCode());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setAddress(personDto.getAddress());
        person.setCity(personDto.getCityEnum());
        person.setPostalCode(personDto.getPostalCode());
        person.setEmail(personDto.getEmail());

        personDao.save(person);
    }

    @Transactional(readOnly = true)
    public PersonDto getPerson(Long id) {
        Person person = personDao.getOne(id);
        return new PersonDto(person);
    }

    @Transactional(readOnly = true)
    public List<PersonDto> getPersons() {
        List<Person> persons = personDao.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
        List<PersonDto> personList = persons.stream().map(person -> new PersonDto(person)).collect(Collectors.toList());
        return personList;
    }

}
