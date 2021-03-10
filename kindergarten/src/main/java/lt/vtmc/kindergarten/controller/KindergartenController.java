package lt.vtmc.kindergarten.controller;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.service.GroupService;
import lt.vtmc.kindergarten.service.KindergartenService;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class KindergartenController {

    private Marker kindergartenEvent  = MarkerFactory.getMarker("AUDIT_EVENT");
    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(AgeRangeController.class);

    @Autowired
    private KindergartenService kindergartenService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value="/api/kindergartens", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create kindergarten", notes = "Creates kindergarten")
    public ResponseEntity addKindergarten(
            @ApiParam(value = "Kindergarten Data", required = true)
            @RequestBody KindergartenDto kindergartenDto){

        if(kindergartenService.getKindergartenByCompanyCode(kindergartenDto.getCompanyCode()) != null ){
            return new ResponseEntity<>("Darželis su tokiu įmonės kodu jau išsaugotas", HttpStatus.CONFLICT);
        }

        try {
            kindergartenService.addKindergarten(kindergartenDto);
            logger.info(kindergartenEvent,"Sukurtas darželis {}, įmonės kodas: {}. Įvykio laikas: {}", kindergartenDto.getTitle(), kindergartenDto.getCompanyCode(), new Date());
            return new ResponseEntity<>(HttpStatus.OK);

        } catch(ConstraintViolationException exception) {
            return new ResponseEntity<>("Data Validation failed",HttpStatus.BAD_REQUEST);
        }

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
        logger.info(kindergartenEvent,"Atnaujinti darželio {}, įmonės kodas: {} duomenys. Įvykio laikas: {}", kindergartenDto.getTitle(), kindergartenDto.getCompanyCode(), new Date());
    }


    @RequestMapping(method = RequestMethod.GET, value = "/api/kindergartens/{kindergarten_id}")
    @ApiOperation(value = "Get single kindergarten by id", notes = "Returns a single kindergarten by id")
    @ResponseStatus(HttpStatus.OK)
    public KindergartenInfoDto getKindergarten(
            @PathVariable final Long kindergarten_id
    ){
        return kindergartenService.getKindergarten(kindergarten_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/kindergartens")
    @ApiOperation(value="Get kindergartens",notes ="Returns kindergartens")
    @ResponseStatus(HttpStatus.OK)
    public List<KindergartenInfoDto> getKindergartens(){
        return kindergartenService.getKindergartens();
    }


    @RequestMapping(value = "/api/kindergartens/{kindergartenId}/groups", method = RequestMethod.GET)
    @ApiOperation(value = "get single groups", notes = "Returns all groups")
    @ResponseStatus(HttpStatus.OK)
    public Set<GroupDto> getGroups(
            @PathVariable final Long kindergartenId
    ){
        return groupService.getGroups(kindergartenId);
    }


    @RequestMapping(value = "/api/kindergartens/{kindergartenId}/groups/{group_id}", method = RequestMethod.GET)
    @ApiOperation(value = "get single group by id", notes = "Returns single group by id")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto getGroup(
            @PathVariable final Long kindergartenId,
            @PathVariable final Long group_id
    ){
        return groupService.getGroup(kindergartenId, group_id);
    }

    @RequestMapping(value = "/api/kindergartens/{kindergartenId}/groups/{group_id}/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Update group by id", notes = "Updates group by id")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto updateGroup(
            @PathVariable final Long group_id,
            @RequestBody ChildrenCountDto childrenCount
            ) {
        return groupService.updateGroupChildrenCount(group_id, childrenCount);
    }



    @RequestMapping(value="/api/kindergartens/{kindergartenId}/groups/{ageRangeId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create group", notes = "Creates a new group")
    public void addGroup(
            @ApiParam(value = "", required = true)
            @PathVariable Long kindergartenId,
            @PathVariable Long ageRangeId,
            @RequestBody GroupCreationDto groupCreationDto){
        groupService.addGroup(ageRangeId, kindergartenId, groupCreationDto);
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
}