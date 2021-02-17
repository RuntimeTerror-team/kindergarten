package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.QueueDto;
import lt.vtmc.kindergarten.dto.QueueDtoFromAdmin;
import lt.vtmc.kindergarten.dto.QueueDtoFromEducationSpecialist;
import lt.vtmc.kindergarten.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueueController {

    @Autowired
    private QueueService queueService;

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


    @RequestMapping(value="/api/queues", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create queue with opening Date", notes = "Creates new queue with opening date")
    public void addQueueWithOpeningDate(
            @ApiParam(value = "", required = true)
            @RequestBody QueueDtoFromAdmin queueDtoFromAdmin){
        queueService.addQueueWithOpeningDate(queueDtoFromAdmin);
    }

    @ApiOperation(value = "Update queue", notes = "Updates queue closing date and closing registration date by id")
    @RequestMapping(value = "/api/queues/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateQueueFromES(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody QueueDtoFromEducationSpecialist queueDtoFromES
            ){
        queueService.updateQueueFromES(id, queueDtoFromES);
    }

}
