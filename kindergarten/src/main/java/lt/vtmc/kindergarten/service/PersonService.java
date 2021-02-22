package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.dao.UserDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.PersonDto;
import lt.vtmc.kindergarten.dto.PersonUserDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Person person = personDao.findByPersonalCode(personDto.getPersonalCode());
        if (person == null) {
            person = createPersonFromDto(personDto);
            person.setTribeId(getLoggedInUserTribeId());
            personDao.save(person);
        } else {
            updatePerson(person.getId(), personDto);
        }

    }

    @Transactional
    public void updatePerson(Long id, PersonDto personDto) {
        Person person = personDao.getOne(id);
        if(!isPersonFamilyMember(person)){
            throw new FamilyMemberValidationException("Person does not belong to the family");
        }
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

    @Transactional
    public PersonDto getPersonByPersonalCode(String personalCode){

    	Person person = personDao.findByPersonalCode(personalCode);
    	System.out.println("RETURNING PERSON WITH ID");

    	return new PersonDto(person.getId(), person.getFirstName(), person.getLastName(), person.getPersonalCode(), person.getPhoneNumber(),
    			person.getAddress(), person.getCity(), person.getPostalCode(), person.getEmail());
    }

    

    @Transactional(readOnly = true)
    public List<PersonDto> getPersons() {
        List<Person> persons = personDao.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
        List<PersonDto> personList = persons.stream().map(person -> new PersonDto(person)).collect(Collectors.toList());
        return personList;
    }

    @Transactional
    public void addPersonWithUsername(PersonUserDto personDto) {
        Person person = createPersonFromDto(personDto);
        User user = userDao.findUserByUsername(personDto.getUsername());
        person.setTribeId(RandomStringUtils.random(30,true,false));
        person.setUser(user);

        personDao.save(person);
    }

    private Person createPersonFromDto(PersonDto person) {
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
    public boolean checkIfPersonExistsByPersonalCode(String personalCode) {
        if(personDao.findByPersonalCode(personalCode)!=null){
            return true;
        } else {
            return false;
        }

    }

    private String getLoggedInUserTribeId(){
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        // Used as a workaround for data seeding as it happens during app startup when no user is logged in.
        if (context == null ){
            return "swagger_family";
        }
        String username = context.getName();
        User user = userDao.findUserByUsername(username);
        Person person = personDao.findByUser(user);
        return person.getTribeId();
    }

    private boolean isPersonFamilyMember(Person person){
        String tribeId = getLoggedInUserTribeId();
        if(person.getTribeId()==tribeId){
            return true;
        } else {
            return false;
        }

    }
}
