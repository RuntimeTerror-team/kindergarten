package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.PersonDto;
import lt.vtmc.kindergarten.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
