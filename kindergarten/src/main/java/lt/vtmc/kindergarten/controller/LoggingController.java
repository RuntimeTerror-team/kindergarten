package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import lt.vtmc.kindergarten.dto.LogDto;
import lt.vtmc.kindergarten.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;


@RestController
public class LoggingController {


    @Autowired
    private LoggingService loggingService;

    @ApiOperation(value = "Get all logs", notes="Returns a single ageRange by id")
    @RequestMapping(path="/api/logging",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAgeRange(){
        return loggingService.getLogs();
    }
}
