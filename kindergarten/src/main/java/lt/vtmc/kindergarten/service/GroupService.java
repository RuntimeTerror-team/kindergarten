package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.GroupDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.Group;
import lt.vtmc.kindergarten.domain.Kindergarten;
import lt.vtmc.kindergarten.dto.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Transactional
    public void addGroup(Long kindergartenId, GroupDto groupDto){
        Kindergarten kindergarten = kindergartenDao.getOne(kindergartenId);

        Group group = new Group();
        group.setAgeMin(groupDto.getAgeMin());
        group.setAgeMax(groupDto.getAgeMax());
        group.setTitle(groupDto.getTitle());
        group.setChildrenCount(group.getChildrenCount());
        group.setKindergartenId(kindergarten);
        kindergarten.addGroup(group);

       kindergartenDao.save(kindergarten);
    }

    @Transactional
    public void updateGroup(Long id,GroupDto groupDto){
        Group group = groupDao.getOne(id);

        group.setAgeMin(groupDto.getAgeMin());
        group.setAgeMax(groupDto.getAgeMax());
        group.setTitle(groupDto.getTitle());
        group.setChildrenCount(group.getChildrenCount());

        groupDao.save(group);
    }


    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void setKindergartenDao(KindergartenDao kindergartenDao) {
        this.kindergartenDao = kindergartenDao;
    }
}


