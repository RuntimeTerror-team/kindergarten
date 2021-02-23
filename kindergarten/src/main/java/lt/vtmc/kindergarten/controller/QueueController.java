package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.QueueDto;
import lt.vtmc.kindergarten.dto.QueueDtoClosingDate;
import lt.vtmc.kindergarten.dto.QueueDtoWithOpeningDate;
import lt.vtmc.kindergarten.dto.QueueDtoRegistrationClosingDate;
import lt.vtmc.kindergarten.service.QueueService;
import lt.vtmc.kindergarten.service.exceptions.RegistrationClosingValidationExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ) {
        return queueService.getQueue(queue_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/queues")
    @ApiOperation(value = "Get queues", notes = "Returns all queues")
    @ResponseStatus(HttpStatus.OK)
    public List<QueueDto> getQueues() {
        return queueService.getQueues();
    }


    @RequestMapping(value = "/api/queues", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create queue with opening Date", notes = "Creates new queue with opening date")
    public void addQueueOpeningDate(
            @ApiParam(value = "", required = true)
            @RequestBody QueueDtoWithOpeningDate queueDtoWithOpeningDate) {
        queueService.addQueueWithOpeningDate(queueDtoWithOpeningDate);
    }


    @RequestMapping(value = "/api/queues/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update queue registration closing date", notes = "Updates queue closing registration date by id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateQueueWithRegistrationClosingDate(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody QueueDtoRegistrationClosingDate queueDtoRegistrationClosingDate
    ) {
        try {
            queueService.updateQueueWithRegistrationClosingDate(id, queueDtoRegistrationClosingDate);
            return new ResponseEntity<>("Registracijos uždarymo data sėkmingai įvesta", HttpStatus.OK);
        } catch (RegistrationClosingValidationExeption exception) {
            return new ResponseEntity("Registracijos uždarymo data turi būti ne senesnė nei eilės atidarymo data", HttpStatus.CONFLICT);
        }
    }


    @RequestMapping(value = "/api/queues/closing/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update queue closing date", notes = "Updates queue closing date by id")
    @ResponseStatus(HttpStatus.OK)
    public void updateQueueClosingDate(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody QueueDtoClosingDate queueDtoClosingDate
    ) {
        queueService.updateQueueWithClosingDate(id, queueDtoClosingDate);
    }

}
