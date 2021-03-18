package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.AgeRangeDao;
import lt.vtmc.kindergarten.dao.GroupDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.AgeRange;
import lt.vtmc.kindergarten.domain.Group;
import lt.vtmc.kindergarten.domain.Kindergarten;
import lt.vtmc.kindergarten.dto.ChildrenCountDto;
import lt.vtmc.kindergarten.dto.GroupCreationDto;
import lt.vtmc.kindergarten.dto.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private AgeRangeDao ageRangeDao;


    @Transactional(readOnly = true)
    public Set<GroupDto> getGroups(Long kindergartenId){
        Kindergarten kindergarten = kindergartenDao.getOne(kindergartenId);
        Set<Group> groups = kindergarten.getGroups();
        Set<GroupDto> groupList= groups.stream().map(group -> new GroupDto(group)).collect(Collectors.toSet());
        return groupList;
    }

    @Transactional(readOnly = true)
    public GroupDto getGroup(Long kindergartenId, Long groupId){
        Kindergarten kindergarten = kindergartenDao.getOne(kindergartenId);
        Set<Group> groups = kindergarten.getGroups();
        Group group = groups.stream().filter(item -> item.getId().equals(groupId)).findFirst().get();
        return new GroupDto(group);
    }


    @Transactional
    public void addGroup(Long ageRangeId, Long kindergartenId, GroupCreationDto groupDto){
        Kindergarten kindergarten = kindergartenDao.getOne(kindergartenId);
        AgeRange ageRange = ageRangeDao.getOne(ageRangeId);
        Group group = new Group();

        group.setChildrenCount(groupDto.getChildrenCount());
        group.setKindergartenId(kindergarten);
        group.setAgeRange(ageRange);

        kindergarten.addGroup(group);
        kindergartenDao.save(kindergarten);
    }

    @Transactional
    public void updateGroup(Long id,GroupDto groupDto){
        Group group = groupDao.getOne(id);

        group.setChildrenCount(groupDto.getChildrenCount());

        groupDao.save(group);
    }

    @Transactional
    public GroupDto updateGroupChildrenCount(Long group_id, ChildrenCountDto childrenCount) {
        Group group = groupDao.getOne(group_id);

        group.setChildrenCount(childrenCount.getChildrenCount());

        groupDao.save(group);
        return new GroupDto(group);
    }


}