package lt.vtmc.kindergarten.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.QueueDto;
import lt.vtmc.kindergarten.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class QueueController {

    @Autowired
    private QueueService queueService;

    @RequestMapping(value="/api/queues", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create queue", notes = "Creates new queue")
    public void addQueue(
            @ApiParam(value = "", required = true)
            @RequestBody QueueDto queueDto){
        queueService.addQueue(queueDto);
    }

}
