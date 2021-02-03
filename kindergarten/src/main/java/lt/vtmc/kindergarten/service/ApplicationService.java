package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.KindergartenApplication;
import lt.vtmc.kindergarten.dao.ApplicationDao;
import lt.vtmc.kindergarten.dao.ChildDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.dao.UserDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private ChildDao childDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Transactional
    public void addApplication(@Valid ApplicationCreationDto applicationCreationDto){
        Child child = childDao.getOne(applicationCreationDto.getChildId());
        UserApplication userApplication = new UserApplication();
        User user = userDao.getOne(applicationCreationDto.getUsername());

        Application application = new Application();

        application.setDate(applicationCreationDto.getDate());
        application.setAdopted(applicationCreationDto.isAdopted());
        application.setMultiChild(applicationCreationDto.isMultiChild());
        application.setGuardianStudent(applicationCreationDto.isGuardianDisabled());
        application.setGuardianDisabled(applicationCreationDto.isGuardianDisabled());
        application.setChild(child);
        application.setScore(applicationCreationDto.getScore());


        userApplication.setApplication(application);
        userApplication.setUser(user);

        application.addUser(userApplication);
        application.setKindergartenApplications(parseKindergartenApplications(applicationCreationDto,application));

        applicationDao.save(application);
    }

    private Set<KindergartenApplicationForm> parseKindergartenApplications(ApplicationCreationDto applicationCreationDto, Application application) {
        Map<Integer,Long> applicationMetadata = applicationCreationDto.getPriorityKindergarten();

        Set<KindergartenApplicationForm> kindergartenApplications = applicationMetadata.entrySet().stream().map(entry -> {
            Kindergarten kindergarten = kindergartenDao.getOne(entry.getValue());
            KindergartenApplicationForm kindergartenApplicationForm = new KindergartenApplicationForm();
            kindergartenApplicationForm.setKindergarten(kindergarten);
            kindergartenApplicationForm.setPriority(entry.getKey());
            kindergartenApplicationForm.setAccepted(false);
            kindergartenApplicationForm.setApplication(application);

            return kindergartenApplicationForm;

        } ).collect(Collectors.toSet());

        return kindergartenApplications;

    }
}
