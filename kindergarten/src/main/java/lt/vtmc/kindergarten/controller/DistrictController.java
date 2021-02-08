package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.DistrictDto;
import lt.vtmc.kindergarten.dto.MessageResponse;
import lt.vtmc.kindergarten.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping(value="/api/district", method = RequestMethod.POST)
    @ApiOperation(value = "Create district", notes = "Creates a new district")
    public ResponseEntity<?> addDistrict(
            @ApiParam(value = "District Data", required = true)
            @Valid @RequestBody DistrictDto districtDto){
    	
    	boolean addedDistrict = districtService.addDistrict(districtDto);
    	
    	if(!addedDistrict) {
    		
    		return ResponseEntity.ok(new MessageResponse("Toks rajonas jau yra įrašytas", addedDistrict));
    	}
    	
    	return ResponseEntity.ok(new MessageResponse("Rajonas sėkmingai sukurtas", addedDistrict));
        
    }
   
    @ApiOperation(value = "Update District", notes = "Updates district by id")
    @RequestMapping(value = "/api/district/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDistrict(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody DistrictDto districtDto
    ){
        districtService.updateDistrict(id, districtDto);
    }

    @ApiOperation(value = "Get single district by id", notes = "Returns a single district by id")
    @RequestMapping(method = RequestMethod.GET, value = "/api/districts/{district_id}")
    @ResponseStatus(HttpStatus.OK)
    public DistrictDto getDistrict(
            @PathVariable final Long district_id
    ){
        return districtService.getDistrict(district_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/districts")
    @ApiOperation(value="Get districts",notes ="Returns all districts")
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictDto> getDistricts(){
        return districtService.getDistricts();
    }

    public void setDistrictService(DistrictService districtService) {
        this.districtService = districtService;
    }
}
