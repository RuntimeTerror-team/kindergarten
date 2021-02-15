package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.PersonDto;
import lt.vtmc.kindergarten.dto.PersonUserDto;
import lt.vtmc.kindergarten.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value="/api/persons", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create person", notes = "Creates new person")
    public void addPerson(
            @ApiParam(value = "", required = true)
            @RequestBody PersonDto personDto){
        personService.addPerson(personDto);
    }

    @RequestMapping(value="/api/persons/username", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adds person", notes = "Creates new person and assigns it to the existing user")
    public void addPersonWithUsername(
            @ApiParam(value = "", required = true)
            @RequestBody PersonUserDto personDto) {
        personService.addPersonWithUsername(personDto);
    }

    @ApiOperation(value = "Update person", notes = "Updates person by id")
    @RequestMapping(value = "/api/persons/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody PersonDto personDto
    ){
        personService.updatePerson(id, personDto);
    }

    @ApiOperation(value = "Get single person by id", notes = "Returns a single person by id")
    @RequestMapping(method = RequestMethod.GET, value = "/api/persons/{personID}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPerson(
            @PathVariable final Long personID
    ){
        return personService.getPerson(personID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/persons")
    @ApiOperation(value="Get persons",notes ="Returns all persons")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPersons(){
        return personService.getPersons();
    }
}
