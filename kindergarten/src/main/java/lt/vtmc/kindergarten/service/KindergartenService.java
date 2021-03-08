package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.Kindergarten;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import lt.vtmc.kindergarten.dto.KindergartenInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class KindergartenService {

    @Autowired
    private KindergartenDao kindergartenDao;

    @Transactional
    public List<KindergartenInfoDto> getKindergartens(){
        List<Kindergarten> kindergartens = kindergartenDao.findAll();
        List<KindergartenInfoDto> kindergartenList= kindergartens.stream().map(kindergarten -> new KindergartenInfoDto(kindergarten)).collect(Collectors.toList());
        return kindergartenList;
    }

    @Transactional(readOnly = true)
    public KindergartenInfoDto getKindergarten(Long id){
        Kindergarten kindergarten = kindergartenDao.getOne(id);
        return new KindergartenInfoDto(kindergarten);
    }

    @Transactional
    public void addKindergarten(@Valid KindergartenDto kindergartenDto){
        Kindergarten kindergarten = new Kindergarten();

        kindergarten.setTitle(kindergartenDto.getTitle());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setPhoneNumber(kindergartenDto.getPhoneNumber());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setCity(kindergartenDto.getCity());
        kindergarten.setPostalCode(kindergartenDto.getPostalCode());
        kindergarten.setEmail(kindergartenDto.getEmail());
        kindergarten.setCompanyCode(kindergartenDto.getCompanyCode());
        kindergarten.setDistrict(kindergartenDto.getDistrict());
        kindergarten.setWebsite(kindergartenDto.getWebsite());

        kindergartenDao.save(kindergarten);
    }


    @Transactional
    public void updateKindergarten(Long id,@Valid KindergartenDto kindergartenDto){
        Kindergarten kindergarten = kindergartenDao.getOne(id);

        kindergarten.setTitle(kindergartenDto.getTitle());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setPhoneNumber(kindergartenDto.getPhoneNumber());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setCity(kindergartenDto.getCity());
        kindergarten.setPostalCode(kindergartenDto.getPostalCode());
        kindergarten.setEmail(kindergartenDto.getEmail());
        kindergarten.setCompanyCode(kindergartenDto.getCompanyCode());
        kindergarten.setDistrict(kindergartenDto.getDistrict());
        kindergarten.setWebsite(kindergartenDto.getWebsite());

        kindergartenDao.save(kindergarten);
    }

    @Transactional(readOnly = true)
    public Kindergarten getKindergartenByCompanyCode(String companyCode) {
        try {
            return kindergartenDao.findByCompanyCode(companyCode);
        } catch (Exception e) {
            return null;
        }
    }


    public void setKindergartenDao(KindergartenDao kindergartenDao) {
        this.kindergartenDao = kindergartenDao;
    }
}
