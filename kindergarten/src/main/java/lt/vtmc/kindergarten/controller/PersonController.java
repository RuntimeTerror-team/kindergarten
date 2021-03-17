package lt.vtmc.kindergarten.controller;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.domain.Person;
import lt.vtmc.kindergarten.dto.PersonDto;
import lt.vtmc.kindergarten.dto.PersonUserDto;
import lt.vtmc.kindergarten.exception.FamilyMemberValidationException;
import lt.vtmc.kindergarten.service.PersonService;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

@RestController
public class PersonController {

    private Marker personEvent = MarkerFactory.getMarker("AUDIT_EVENT");
    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @PreAuthorize("hasRole('GUARDIAN')")
    @RequestMapping(value="/api/persons", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create person", notes = "Creates new person")
    public ResponseEntity addPerson(
            @ApiParam(value = "", required = true)
            @RequestBody PersonDto personDto){

        try {
            personService.addPerson(personDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException exception){
            return new ResponseEntity<>("Data Validation failed", HttpStatus.BAD_REQUEST);
        } catch (FamilyMemberValidationException exception) {
            return new ResponseEntity<>("Can not edit person", HttpStatus.UNAUTHORIZED);
        }

    }

    @PreAuthorize("hasRole('GUARDIAN')")
    @RequestMapping(value="/api/persons/username", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adds person", notes = "Creates new person and assigns it to the existing user")
    public ResponseEntity addPersonWithUsername(
            @ApiParam(value = "", required = true)
            @RequestBody PersonUserDto personDto) {

        if(personService.checkIfPersonExistsByPersonalCode(personDto.getPersonalCode())){
            return new ResponseEntity<>("Asmuo su tokiu asmens kodu jau egzistuoja", HttpStatus.CONFLICT);
        }

        try {
            personService.addPersonWithUsername(personDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException exception){
            return new ResponseEntity<>("Data Validation failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('GUARDIAN')")
    @ApiOperation(value = "Update person", notes = "Updates person by id")
    @RequestMapping(value = "/api/persons/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody PersonDto personDto
    ){
        personService.updatePerson(id, personDto);
        logger.info(personEvent,"Atnaujinti {} {} duomenys. Įvykio laikas: {}", personDto.getFirstName(), personDto.getLastName(), new Date());
    }

    /* TODO - check if in use */
    @ApiOperation(value = "Get single person by id", notes = "Returns a single person by id")
    @RequestMapping(method = RequestMethod.GET, value = "/api/persons/{personID}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPerson(
            @PathVariable final Long personID
    ){
        return personService.getPerson(personID);
    }

    /* TODO - check if in use */
    @RequestMapping(method = RequestMethod.GET, value = "/api/persons")
    @ApiOperation(value="Get persons",notes ="Returns all persons")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPersons(){
        return personService.getPersons();
    }

    @PreAuthorize("hasRole('GUARDIAN')")
    @RequestMapping(value="/api/persons/byPersonalCode/{personalCode}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Get person by personalCode", notes = "Returns a person by personalCode")
    public PersonDto getPersonById(@PathVariable final String personalCode){
        return personService.getPersonByPersonalCode(personalCode);
    }

    @PreAuthorize("hasRole('GUARDIAN')")
    @RequestMapping(value="/api/persons/childrenOfTribe/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get children of a person", notes = "Returns a list of children of a person by username")
    public List<Person> getChildrenByParentUsername(@PathVariable final String username){
        return personService.getChildrenByParentUsername(username);
    }
}
