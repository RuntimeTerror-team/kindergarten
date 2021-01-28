package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.AgeRangeDao;
import lt.vtmc.kindergarten.domain.AgeRange;
import lt.vtmc.kindergarten.dto.AgeRangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AgeRangeService {

    @Autowired
    private AgeRangeDao ageRangeDao;

    @Transactional
    public void addAgeRange(AgeRangeDto ageRangeDto){
        AgeRange ageRange = new AgeRange();

        ageRange.setAgeMin(ageRangeDto.getMinAge());
        ageRange.setAgeMax(ageRangeDto.getMaxAge());

        ageRangeDao.save(ageRange);
    }

    @Transactional
    public void updateAgeRange(Long id, AgeRangeDto ageRangeDto){
        AgeRange ageRange = ageRangeDao.getOne(id);

        ageRange.setAgeMin(ageRangeDto.getMinAge());
        ageRange.setAgeMax(ageRangeDto.getMaxAge());

        ageRangeDao.save(ageRange);
    }

}
