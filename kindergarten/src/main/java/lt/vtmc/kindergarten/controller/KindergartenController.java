package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.GroupDto;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import lt.vtmc.kindergarten.service.GroupService;
import lt.vtmc.kindergarten.service.KindergartenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KindergartenController {

    @Autowired
    private KindergartenService kindergartenService;

    @Autowired
    private GroupService groupService;

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


    @RequestMapping(method = RequestMethod.GET, value = "/api/kindergartens/{kindergarten_id}")
    @ApiOperation(value = "Get single kindergarten by id", notes = "Returns a single kindergarten by id")
    @ResponseStatus(HttpStatus.OK)
    public KindergartenDto getKindergarten(
            @PathVariable final Long kindergarten_id
    ){
        return kindergartenService.getKindergarten(kindergarten_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/kindergartens")
    @ApiOperation(value="Get kindergartens",notes ="Returns kindergartens")
    @ResponseStatus(HttpStatus.OK)
    public List<KindergartenDto> getKindergartens(){
        return kindergartenService.getKindergartens();
    }


    @RequestMapping(value="/api/kindergartens/{kindergartenId}/groups/{ageRangeId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create group", notes = "Creates a new group")
    public void addGroup(
            @ApiParam(value = "", required = true)
            @PathVariable Long kindergartenId,
            @PathVariable Long ageRangeId,
            @RequestBody GroupDto groupDto){
        groupService.addGroup(ageRangeId, kindergartenId, groupDto);
    }

    @ApiOperation(value = "Update group", notes = "Updates group by id")
    @RequestMapping(value = "/api/kindergartens/{kindergartenId}/groups/{groupId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateGroup(
            @ApiParam(value = "", required = true)
            @PathVariable Long groupId,
            @RequestBody GroupDto groupDto
    ){
        groupService.updateGroup(groupId, groupDto);
    }


    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
}
