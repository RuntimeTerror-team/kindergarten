package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Transactional
    public void addPerson(@Valid PersonDto personDto){
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
}
