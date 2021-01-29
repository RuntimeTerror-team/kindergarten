package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.DistrictDao;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.dto.DistrictDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {

    @Autowired
    private DistrictDao districtDao;


    @Transactional
    public DistrictDto getDistrict(Long id){
        District district = districtDao.getOne(id);
        return new DistrictDto(district);
    }

    @Transactional
    public List<DistrictDto> getDistricts(){
        List<District> districts = districtDao.findAll();
        List<DistrictDto> districtList= districts.stream().map(district -> new DistrictDto(district)).collect(Collectors.toList());
        return districtList;
    }


    @Transactional
    public void addDistrict(DistrictDto districtDto){
        District district = new District();
        district.setTitle(districtDto.getTitle());
        districtDao.save(district);
    }

    @Transactional
    public void updateDistrict(Long id, DistrictDto districtDto){
        District district = districtDao.getOne(id);
        district.setTitle(districtDto.getTitle());
        districtDao.save(district);
    }




    public void setDistrictDao(DistrictDao districtDao) {
        this.districtDao = districtDao;
    }
}
