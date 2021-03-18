package lt.vtmc.kindergarten.controller;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.AgeRangeDto;
import lt.vtmc.kindergarten.dto.MessageResponse;
import lt.vtmc.kindergarten.service.AgeRangeService;

import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AgeRangeController {

    private Marker ageRangeEvent = MarkerFactory.getMarker("AUDIT_EVENT");
    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(AgeRangeController.class);

    @Autowired
    private AgeRangeService ageRangeService;

    @PreAuthorize("hasRole('EDUCATION_SPECIALIST')")
    @RequestMapping(method = RequestMethod.GET, value = "/api/ageRanges")
    @ApiOperation(value="Get age ranges",notes ="Returns age ranges")
    @ResponseStatus(HttpStatus.OK)
    public List<AgeRangeDto> getAgeRanges(){
        return ageRangeService.getAgeRanges();
    }


    @PreAuthorize("hasRole('EDUCATION_SPECIALIST')")
    @RequestMapping(value="/api/saveInterval", method = RequestMethod.POST)
    @ApiOperation(value = "Create age range", notes = "Creates a new age range")
    public ResponseEntity<?> saveAgeRange(
            @ApiParam(value = "Age range data", required = true)
            @Valid
            @RequestBody AgeRangeDto ageRangeDto){
    	
        boolean addedAgeRange = ageRangeService.addAgeInterval(ageRangeDto);
        logger.info(ageRangeEvent, "Sukurtas {}-{} amžiaus intervalas. Įvykio laikas: {}",ageRangeDto.getMinAge(), ageRangeDto.getMaxAge(), new Date());
        
        if(!addedAgeRange) {
        	
        	return ResponseEntity.ok(new MessageResponse("Toks amžiaus intervalas jau yra įrašytas", addedAgeRange));
        }
        
      	return ResponseEntity.ok(new MessageResponse("Grupės intervalas sėkmingai išsaugotas", addedAgeRange));
    }

    @PreAuthorize("hasRole('EDUCATION_SPECIALIST')")
    @RequestMapping(value = "/api/ageRanges/{ageMin}/{ageMax}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete age range", notes = "Deletes age range by ageMin adn ageMax")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAgeRange(
            @PathVariable int ageMin,
            @PathVariable int ageMax
            
    ){
        ageRangeService.deleteAgeRange(ageMin, ageMax);
    }


}
