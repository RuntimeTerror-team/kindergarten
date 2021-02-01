package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.Kindergarten;
import lt.vtmc.kindergarten.dto.KindergartenDto;
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
    public List<KindergartenDto> getKindergartens(){
        List<Kindergarten> kindergartens = kindergartenDao.findAll();
        List<KindergartenDto> kindergartenList= kindergartens.stream().map(kindergarten -> new KindergartenDto(kindergarten)).collect(Collectors.toList());
        return kindergartenList;
    }

    @Transactional(readOnly = true)
    public KindergartenDto getKindergarten(Long id){
        Kindergarten kindergarten = kindergartenDao.getOne(id);
        return new KindergartenDto(kindergarten);
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
        kindergarten.setDistrict(kindergartenDto.getDistrict());

        kindergartenDao.save(kindergarten);
    }


    @Transactional
    public void updateKindergarten(Long id, KindergartenDto kindergartenDto){
        Kindergarten kindergarten = kindergartenDao.getOne(id);

        kindergarten.setTitle(kindergartenDto.getTitle());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setPhoneNumber(kindergartenDto.getPhoneNumber());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setCity(kindergartenDto.getCity());
        kindergarten.setPostalCode(kindergartenDto.getPostalCode());
        kindergarten.setEmail(kindergartenDto.getEmail());
        kindergarten.setDistrict(kindergartenDto.getDistrict());

        kindergartenDao.save(kindergarten);
    }


    public void setKindergartenDao(KindergartenDao kindergartenDao) {
        this.kindergartenDao = kindergartenDao;
    }
}
