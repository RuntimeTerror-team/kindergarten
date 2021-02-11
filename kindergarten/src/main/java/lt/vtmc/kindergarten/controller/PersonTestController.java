package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.PersonTestDto;
import lt.vtmc.kindergarten.service.PersonTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonTestController {

    @Autowired
    private PersonTestService personTestService;

    @RequestMapping(value="/api/persons", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create person", notes = "Creates new person")
    public void addPerson(
            @ApiParam(value = "", required = true)
            @RequestBody PersonTestDto personTestDto){
        personTestService.addPerson(personTestDto);
    }
}
