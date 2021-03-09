package lt.vtmc.kindergarten.controller;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.domain.ApplicationAfterDistribution;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.ApplicationDto;
import lt.vtmc.kindergarten.dto.ApplicationInfoDto;
import lt.vtmc.kindergarten.service.ApplicationService;

import java.util.Date;
import java.util.List;

import lt.vtmc.kindergarten.exception.QueueDoesntExistException;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class ApplicationController{

    private Marker applicationEvent = MarkerFactory.getMarker("AUDIT_EVENT");
    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/api/applications", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create application", notes = "Creates a new application")
    public ResponseEntity addApplication(
            @ApiParam(value = "", required = true)
            @RequestBody ApplicationCreationDto applicationCreationDto) {
        try {
            applicationService.addApplication(applicationCreationDto);
            logger.info(applicationEvent, "Vartotojas {} sukūrė prašymą. Įvykio laikas: {}", getLoggedInUserName(), new Date());
            return new ResponseEntity(HttpStatus.OK);
        } catch (QueueDoesntExistException exception) {
            return new ResponseEntity("Nėra aktyvios eilės priskirti aplikacijai", HttpStatus.FORBIDDEN);
        } catch (Exception exception) {
            return new ResponseEntity("Ivyko klaida", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/api/applicationsDtos", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Get applications", notes = "Returns a list of applications")
    public List<ApplicationDto> getApplicationsList() {
        return applicationService.getApplicationsList();
    }

    @ApiOperation(value = "Update application", notes = "Updates application by id")
    @RequestMapping(value = "/api/applications/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateApplication(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody ApplicationCreationDto applicationDto
    ) {
        try {
            applicationService.updateApplication(id, applicationDto);
            logger.info(applicationEvent, "Vartotojas {} atnaujino prašymą. Įvykio laikas: {}", getLoggedInUserName(), new Date());
            return new ResponseEntity(HttpStatus.OK);
        } catch (QueueDoesntExistException exception) {
            return new ResponseEntity("Prašymo, negalima redaguoti, nes nėra aktyvios eilės", HttpStatus.FORBIDDEN);
        }

    }

    @ApiOperation(value = "Get single application by id", notes = "Returns a single application by id")
    @RequestMapping(method = RequestMethod.GET, value = "/api/applications/{application_id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationCreationDto getApplication(
            @PathVariable final Long application_id
    ) {
        return applicationService.getApplication(application_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applications")
    @ApiOperation(value = "Get applications", notes = "Returns all applications")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationCreationDto> getApplications() {
        return applicationService.getApplications();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applications/info/{username}")
    @ApiOperation(value = "Get applications info", notes = "Returns all applications with info about children first, last names and application status, date by username")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationInfoDto> getApplicationsInfo(
            @PathVariable final String username
    ) {
        return applicationService.getApplicationsInfo(username);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/applications/recalculation")
    @ApiOperation(value = "Trigers applications queuing", notes = "Trigers sorting algorithm and applications are queued again")
    @ResponseStatus(HttpStatus.OK)
    public void recalculateApplicationsOrder(){
        applicationService.recalculateApplicationOrderInQueue();
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/api/applications/{id}/{status}")
    @ApiOperation(value = "Change application status", notes = "Changes application status after distribution")
    @ResponseStatus(HttpStatus.OK)
    public void changeApplicationAfterDistributionStatus(@PathVariable final long id, @PathVariable final String status) {
    	applicationService.changeApplicationStatus(id, status);
    }

    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    private String getLoggedInUserName() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getAuthentication() != null){
            return securityContext.getAuthentication().getName();
        }
        return "UNKNOWN";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applications/sorted")
    @ApiOperation(value = "Get sorted applications", notes = "Returns all application after distribution")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ApplicationAfterDistribution>> getApplicationsAfterDistribution(Pageable pageable) {
        return new ResponseEntity<>(applicationService.findAll(pageable), HttpStatus.OK);
    }

}
