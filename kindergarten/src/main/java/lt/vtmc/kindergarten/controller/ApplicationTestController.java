package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.ApplicationCreationTestDto;
import lt.vtmc.kindergarten.service.ApplicationTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationTestController {

    @Autowired
    private ApplicationTestService applicationTestService;

    @RequestMapping(value="/api/applications", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create application", notes = "Creates a new application")
    public void addApplication(
            @ApiParam(value = "", required = true)
            @RequestBody ApplicationCreationTestDto applicationCreationTestDto){
        applicationTestService.addApplication(applicationCreationTestDto);
    }

}
