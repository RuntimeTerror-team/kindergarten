package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.AgeRangeDao;
import lt.vtmc.kindergarten.domain.AgeRange;
import lt.vtmc.kindergarten.dto.AgeRangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class AgeRangeService {

    @Autowired
    private AgeRangeDao ageRangeDao;

    @Transactional(readOnly = true)
    public List<AgeRangeDto> getAgeRanges(){
        List<AgeRange> ageRanges = ageRangeDao.findAll();

        List<AgeRangeDto> ageRangeList = ageRanges.stream()
                .map(ageRange -> new AgeRangeDto(ageRange))
                .collect(Collectors.toList());

        return ageRangeList;
    }

    @Transactional
    public AgeRangeDto getAgeRange(Long id) {
        AgeRange ageRange = ageRangeDao.getOne(id);
        return new AgeRangeDto(ageRange);
    }



    @Transactional
    public void addAgeRange(@Valid AgeRangeDto ageRangeDto){
        AgeRange ageRange = new AgeRange();

        ageRange.setAgeMin(ageRangeDto.getMinAge());
        ageRange.setAgeMax(ageRangeDto.getMaxAge());

        ageRangeDao.save(ageRange);
    }
    
    
    //Paulius
    @Transactional
    public boolean addAgeInterval(@Valid AgeRangeDto ageRangeDto){

        AgeRange ageRange = new AgeRange();
        int minAge = ageRangeDto.getMinAge();
        int maxAge = ageRangeDto.getMaxAge();
        
        if(findAgeRange(minAge, maxAge) == null) {
        
        ageRange.setAgeMin(minAge);
        ageRange.setAgeMax(maxAge);
        ageRangeDao.save(ageRange);
        return true;
        
        }
        
        return false;
    }

    @Transactional
    public void updateAgeRange(@NotNull Long id, @Valid AgeRangeDto ageRangeDto){
        AgeRange ageRange = ageRangeDao.getOne(id);

        ageRange.setAgeMin(ageRangeDto.getMinAge());
        ageRange.setAgeMax(ageRangeDto.getMaxAge());

        ageRangeDao.save(ageRange);
    }
    
    @Transactional
    public void deleteAgeRange(@NotNull int ageMin, @NotNull int ageMax) {
    	
    	AgeRange ageRange = ageRangeDao.findByAgeMinAndAgeMax(ageMin, ageMax);
    	
    	if(ageRange != null) {
    		
    		ageRangeDao.delete(ageRange);
    	}
    }
    
    @Transactional
    public AgeRange findAgeRange(int ageMin, int ageMax){
        
    	AgeRange ageRange = ageRangeDao.findByAgeMinAndAgeMax(ageMin, ageMax);
    	return ageRange;
    	
    }

}
