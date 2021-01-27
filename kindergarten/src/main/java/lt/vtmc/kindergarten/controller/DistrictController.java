package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.DistrictDto;
import lt.vtmc.kindergarten.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    //user/admin/district?????????????????????????????????????????????????????????????
    @RequestMapping(value="/api/district", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create district", notes = "Creates a new district")
    public void addDistrict(
            @ApiParam(value = "District Data", required = true)
            @RequestBody DistrictDto districtDto){
        districtService.addDistrict(districtDto);
    }


    @ApiOperation(value = "Update District", notes = "Uptades district by id")
    @RequestMapping(value = "/api/district/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDistrict(
            @ApiParam(value = "", required = true)
            @PathVariable Long id,
            @RequestBody DistrictDto districtDto
    ){
        districtService.updateDistrict(id, districtDto);
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
