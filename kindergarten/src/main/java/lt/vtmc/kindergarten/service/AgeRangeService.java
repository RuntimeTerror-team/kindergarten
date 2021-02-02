package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.AgeRangeDao;
import lt.vtmc.kindergarten.domain.AgeRange;
import lt.vtmc.kindergarten.dto.AgeRangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class AgeRangeService {

    @Autowired
    private AgeRangeDao ageRangeDao;
    
    @Transactional
    public List<AgeRangeDto> getAgeRanges(){
    	
    	return ageRangeDao.findAll().stream()
    			.map(ageRange -> new AgeRangeDto(ageRange.getAgeMin(), ageRange.getAgeMax())).collect(Collectors.toList());
    }

    @Transactional
    public boolean addAgeRange(AgeRangeDto ageRangeDto){
    	
        AgeRange ageRange = new AgeRange();
        int minAge = ageRangeDto.getMinAge();
        int maxAge = ageRangeDto.getMaxAge();
        
       
        if(findAgeRange(minAge, maxAge) == null) {
        
        ageRange.setAgeMin(minAge);
        ageRange.setAgeMax(maxAge);
        ageRangeDao.save(ageRange);
        return true;
        
        }
        
        System.out.println("this range already was");
        
        return false;
    }

    @Transactional
    public void updateAgeRange(Long id, AgeRangeDto ageRangeDto){
        AgeRange ageRange = ageRangeDao.getOne(id);

        ageRange.setAgeMin(ageRangeDto.getMinAge());
        ageRange.setAgeMax(ageRangeDto.getMaxAge());

        ageRangeDao.save(ageRange);
    }
    
    @Transactional
    public AgeRange findAgeRange(int ageMin, int ageMax){
        
    	AgeRange ageRange = ageRangeDao.findByAgeMinAndAgeMax(ageMin, ageMax);
    	return ageRange;
    	
    }

}
