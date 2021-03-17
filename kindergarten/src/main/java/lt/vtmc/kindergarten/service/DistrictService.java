package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.DistrictDao;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.dto.DistrictDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class DistrictService {

    @Autowired
    private DistrictDao districtDao;


    @Transactional(readOnly = true)
    public DistrictDto getDistrict(Long id){
        District district = districtDao.getOne(id);
        return new DistrictDto(district);
    }

    @Transactional(readOnly = true)
    public List<DistrictDto> getDistricts(){
        List<District> districts = districtDao.findAll(Sort.by(Sort.Direction.ASC,"title"));
        List<DistrictDto> districtList= districts.stream().map(district -> new DistrictDto(district)).collect(Collectors.toList());
        return districtList;
    }


    //Paulius
    @Transactional
    public boolean addDistrict(@Valid DistrictDto districtDto){
    	
        District district = new District();
        
        if(findDistrict(districtDto.getTitle()) == null) {
        
        	district.setTitle(districtDto.getTitle());
            districtDao.save(district);
            return true;
        }
        
        return false;
    }
    
    @Transactional
    public void updateDistrict(Long id, DistrictDto districtDto){
        District district = districtDao.getOne(id);
        district.setTitle(districtDto.getTitle());
        districtDao.save(district);
    }
    
    @Transactional(readOnly = true)
    public District findDistrict(String title) {
    	
    	District district = districtDao.findByTitle(title);
    	return district;
    	
    }

    public void setDistrictDao(DistrictDao districtDao) {
        this.districtDao = districtDao;
    }
}
