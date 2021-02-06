package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.ApplicationDao;
import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.dto.ApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Transactional
    public void addApplication(@Valid ApplicationDto applicationDto){
        Application application = new Application();

        application.setDate(applicationDto.getDate());
        application.setAdopted(applicationDto.isAdopted());
        application.setGuardianDisabled(applicationDto.isGuardianDisabled());
        application.setUsers(applicationDto.getUsers());
        application.setChildId(applicationDto.getChildId());
        application.setChild(applicationDto.getChild());
        application.setMultiChild(applicationDto.isMultiChild());
        application.setGuardianStudent(applicationDto.isGuardianDisabled());


        applicationDao.save(application);
    }

}
