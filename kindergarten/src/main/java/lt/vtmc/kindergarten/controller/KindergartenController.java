package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.domain.Kindergarten;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import lt.vtmc.kindergarten.service.KindergartenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class KindergartenController {

    @Autowired
    private KindergartenService kindergartenService;

    @RequestMapping(value="/api/kindergartens", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create kindergarten", notes = "Creates kindergarten")
    public void addKindergarten(
            @ApiParam(value = "Kindergarten Data", required = true)
            @RequestBody KindergartenDto kindergartenDto){
        kindergartenService.addKindergarten(kindergartenDto);
    }

    @ApiOperation(value = "Update Kindergarten", notes = "Uptades Kindergarten by id")
    @RequestMapping(value = "/api/kindergartens/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateKindergarten(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody KindergartenDto kindergartenDto
    ){
        kindergartenService.updateKindergarten(id, kindergartenDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/kindergartens")
    @ApiOperation(value="Get kindergartens",notes ="Returns kindergartens")
    @ResponseStatus(HttpStatus.OK)
    public List<KindergartenDto> getKindergartens(){
        return kindergartenService.getKindergartens();
    }


    public void setKindergartenService(Kindergarten service){
        this.kindergartenService = kindergartenService;
    }

}
