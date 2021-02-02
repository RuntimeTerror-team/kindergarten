package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.GroupDto;
import lt.vtmc.kindergarten.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

//    @RequestMapping(value="/api/kindergartens/{kindergartenId}/group", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation(value = "Create group", notes = "Creates a new group")
//    public void addGroup(
//            @ApiParam(value = "", required = true)
//            @PathVariable Long kindergartenId,
//            @RequestBody GroupDto groupDto){
//        groupService.addGroup(kindergartenId, groupDto);
//    }

    @ApiOperation(value = "Update group", notes = "Uptades group by id")
    @RequestMapping(value = "/api/kindergartens/{kindergartenId}/group/{groupId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateGroup(
            @ApiParam(value = "", required = true)
            @PathVariable Long kindergartenId,
            @PathVariable Long groupId,
            @RequestBody GroupDto groupDto
    ){
//        groupService.updateGroup(kindergartenId, groupId, groupDto);
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
}
