package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.PersonTestDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.PersonTestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class PersonTestService {

    @Autowired
    private PersonTestDao personTestDao;

    @Transactional
    public void addPerson(@Valid PersonTestDto personTestDto){
        PersonTest person = new PersonTest();
        person.setFirstName(personTestDto.getFirstName());
        person.setLastName(personTestDto.getLastName());
        person.setPersonalCode(personTestDto.getPersonalCode());
        person.setPhoneNumber(personTestDto.getPhoneNumber());
        person.setAddress(personTestDto.getAddress());
        person.setCity(personTestDto.getCityEnum());
        person.setPostalCode(personTestDto.getPostalCode());
        person.setEmail(personTestDto.getEmail());

        personTestDao.save(person);
    }
}
