package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value="/api/application", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create application", notes = "Creates a new application")
    public void addApplication(
            @ApiParam(value = "", required = true)
            @RequestBody ApplicationCreationDto applicationCreationDto){
        applicationService.addApplication(applicationCreationDto);
    }
}
