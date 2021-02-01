package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.AgeRangeDao;
import lt.vtmc.kindergarten.dao.GroupDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.AgeRange;
import lt.vtmc.kindergarten.domain.Group;
import lt.vtmc.kindergarten.domain.Kindergarten;
import lt.vtmc.kindergarten.dto.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private AgeRangeDao ageRangeDao;

    @Transactional
    public void addGroup(Long ageRangeId, Long kindergartenId, GroupDto groupDto){
        Kindergarten kindergarten = kindergartenDao.getOne(kindergartenId);
        AgeRange ageRange = ageRangeDao.getOne(ageRangeId);
        Group group = new Group();

        group.setTitle(groupDto.getTitle());
        group.setChildrenCount(group.getChildrenCount());
        group.setKindergartenId(kindergarten);
        group.setAgeRange(ageRange);

        kindergarten.addGroup(group);
        kindergartenDao.save(kindergarten);
    }

    @Transactional
    public void updateGroup(Long id,GroupDto groupDto){
        Group group = groupDao.getOne(id);

        group.setTitle(groupDto.getTitle());
        group.setChildrenCount(groupDto.getChildrenCount());

        groupDao.save(group);
    }



    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void setKindergartenDao(KindergartenDao kindergartenDao) {
        this.kindergartenDao = kindergartenDao;
    }
}


