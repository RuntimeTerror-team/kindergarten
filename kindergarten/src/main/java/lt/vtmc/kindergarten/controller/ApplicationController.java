package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.ApplicationDto;
import lt.vtmc.kindergarten.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value="/api/applications", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create application", notes = "Creates a new application")
    public void addApplication(
            @ApiParam(value = "", required = true)
            @RequestBody ApplicationCreationDto applicationCreationDto){
        applicationService.addApplication(applicationCreationDto);
    }

    @ApiOperation(value = "Update application", notes = "Updates application by id")
    @RequestMapping(value = "/api/{username}/applications/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateApplication(
            @ApiParam(value = "", required = true)
            @PathVariable String username,
            @PathVariable Long id,
            @RequestBody ApplicationCreationDto applicationCreationDto
    ){
        applicationService.updateApplication(username, id, applicationCreationDto);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/api/{username}/applications")
    @ApiOperation(value="Get applications",notes ="Returns applications")
    @ResponseStatus(HttpStatus.OK)
    public Set<ApplicationDto> getApplications(
            @PathVariable String username
    ){
        return applicationService.getApplications(username);
    }

//    public ResponseEntity getUserApplications(Long userId){
//    }







}
