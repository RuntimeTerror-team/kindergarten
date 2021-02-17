package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.QueueDto;
import lt.vtmc.kindergarten.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @ApiOperation(value = "Update queue", notes = "Updates queue by id")
    @RequestMapping(value = "/api/queues/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateQueue(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody QueueDto queueDto
    ){
        queueService.updateQueue(id, queueDto);
    }

    @ApiOperation(value = "Get single queue by id", notes = "Returns a single queue by id")
    @RequestMapping(method = RequestMethod.GET, value = "/api/queues/{queue_id}")
    @ResponseStatus(HttpStatus.OK)
    public QueueDto getQueue(
            @PathVariable final Long queue_id
    ){
        return queueService.getQueue(queue_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/queues")
    @ApiOperation(value="Get queues",notes ="Returns all queues")
    @ResponseStatus(HttpStatus.OK)
    public List<QueueDto> getQueues(){
        return queueService.getQueues();
    }


}
