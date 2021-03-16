package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.ChildDto;
import lt.vtmc.kindergarten.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChildController {

    @Autowired
    private ChildService childService;


    @RequestMapping(value="/api/children", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create child", notes = "Creates a new child")
    public void addChild(
            @ApiParam(value = "Child Data", required = true)
            @RequestBody ChildDto childDto){
        childService.addChild(childDto);
    }

    @ApiOperation(value = "Update Child", notes = "Uptades child by id")
    @RequestMapping(value = "/api/children/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateChild(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody ChildDto childDto
    ){
        childService.updateChild(id, childDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/children")
    @ApiOperation(value="Get children",notes ="Returns children")
    @ResponseStatus(HttpStatus.OK)
    public List<ChildDto> getChildren(){
        return childService.getChildren();
    }

    @ApiOperation(value = "Delete child", notes = "Delete child by id")
    @RequestMapping(value = "/api/children/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteChild(@ApiParam(value = "", required = true)@PathVariable final Long id){
        childService.removeChild(id);
    }

}