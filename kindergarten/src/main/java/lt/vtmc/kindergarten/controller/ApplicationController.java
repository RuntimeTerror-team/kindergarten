package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.ApplicationDto;
import lt.vtmc.kindergarten.service.ApplicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    
    @RequestMapping(value="/api/applications", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Get applications", notes = "Returns a list of applications")
    public List<ApplicationDto> getApplications(){
        return applicationService.getApplications();
    }

    @ApiOperation(value = "Update application", notes = "Updates application by id")
    @RequestMapping(value = "/api/applications/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateApplication(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody ApplicationCreationDto applicationDto
    ){
        applicationService.updateApplication(id, applicationDto);
    }

    @ApiOperation(value = "Get single application by id", notes = "Returns a single application by id")
    @RequestMapping(method = RequestMethod.GET, value = "/api/applications/{application_id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationCreationDto getApplication(
            @PathVariable final Long application_id
    ){
        return applicationService.getApplication(application_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applications")
    @ApiOperation(value="Get applications",notes ="Returns all applications")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationCreationDto> getApplications(){
        return applicationService.getApplications();
    }


    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
}
