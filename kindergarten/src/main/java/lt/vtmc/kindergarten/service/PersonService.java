package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.dao.UserDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.PersonDto;
import lt.vtmc.kindergarten.dto.PersonUserDto;
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


    @Transactional
    public void addPerson(@Valid PersonDto personDto) {
        if (personDao.findByPersonalCode(personDto.getPersonalCode()) == null) {
            Person person = createFromDto(personDto);
            personDao.save(person);
        } else {
            throw new RuntimeException("Toks asmuo jau egzistuoja");
        }

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

    @Transactional
    public void addPersonWithUsername(PersonUserDto personDto) {
        Person person = createFromDto(personDto);
        User user = userDao.findUserByUsername(personDto.getUsername());
        person.setUser(user);

        personDao.save(person);
    }

    private Person createFromDto(PersonDto person) {
        Person personEntity = new Person();
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setPersonalCode(person.getPersonalCode());
        personEntity.setPhoneNumber(person.getPhoneNumber());
        personEntity.setAddress(person.getAddress());
        personEntity.setCity(person.getCityEnum());
        personEntity.setPostalCode(person.getPostalCode());
        personEntity.setEmail(person.getEmail());

        return personEntity;
    }

    @Transactional(readOnly = true)
    public Person getPersonByPersonalCode(String personalCode) {
        try {
            return personDao.findByPersonalCode(personalCode);
        } catch (Exception e) {
            return null;
        }
    }
}
