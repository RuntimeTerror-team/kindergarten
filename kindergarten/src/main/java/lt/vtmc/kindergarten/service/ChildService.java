package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.ChildDao;
import lt.vtmc.kindergarten.domain.Child;
import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.dto.ChildDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class ChildService {

    @Autowired
    private ChildDao childDao;


    @Transactional(readOnly = true)
    public ChildDto getChild(Long id){
        Child child = childDao.getOne(id);
        return new ChildDto(child);
    }

    @Transactional
    public List<ChildDto> getChildren(){
        List<Child> children = childDao.findAll();
        List<ChildDto> childrenList= children.stream().map(child -> new ChildDto(child)).collect(Collectors.toList());
        return childrenList;
    }

    @Transactional
    public void addChild(@Valid ChildDto childDto){
        Child child = new Child();

        child.setCity(childDto.getCity());
//        child.setCity(CityEnum.valueOf(childDto.getCity()));
        child.setFirstName(childDto.getFirstName());
        child.setLastName(childDto.getLastName());
        child.setPersonalCode(childDto.getPersonalCode());
        child.setStreetAddress(childDto.getStreetAddress());

        childDao.save(child);
    }

    @Transactional
    public void updateChild(Long id, ChildDto childDto){
        Child child = childDao.getOne(id);

        child.setCity(childDto.getCity());
        child.setFirstName(childDto.getFirstName());
        child.setLastName(childDto.getLastName());
        child.setPersonalCode(childDto.getPersonalCode());
        child.setStreetAddress(childDto.getStreetAddress());

        childDao.save(child);
    }


    public void removeChild(Long id){
        childDao.deleteById(id);
    }

    public void setChildDao(ChildDao childDao) {
        this.childDao = childDao;
    }
}