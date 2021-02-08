package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.ApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
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

    @Autowired
    private PersonDao personDao;

    @Transactional
    public void addApplication(@Valid ApplicationCreationDto applicationCreationDto){
        Child child = childDao.getOne(applicationCreationDto.getChildId());
        User user = userDao.getOne(applicationCreationDto.getUsername());

        Application application = new Application();
        application.setDate(applicationCreationDto.getDate());
        application.setAdopted(applicationCreationDto.isAdopted());
        application.setMultiChild(applicationCreationDto.isMultiChild());
        application.setGuardianStudent(applicationCreationDto.isGuardianDisabled());
        application.setGuardianDisabled(applicationCreationDto.isGuardianDisabled());
        application.setChild(child);
        application.setScore(applicationCreationDto.getScore());
        application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);
        application.setApplicant(user);
        application.setKindergartenApplicationForms(parseKindergartenApplications(applicationCreationDto,application));
        user.addUserApplication(application);

        applicationDao.save(application);
    }

    @Transactional(readOnly = true)
    public Set<ApplicationDto> getApplications(String username){
        User user = userDao.findById(username).get();
        return user.getUserApplications().stream().map(application -> new ApplicationDto(application)).collect(Collectors.toSet());
    }


    private Set<KindergartenApplicationForm> parseKindergartenApplications(ApplicationCreationDto applicationCreationDto, Application application) {
        Map<Integer,Long> applicationMetadata = applicationCreationDto.getPriorityForKindergartenID();

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

    public void updateApplication(String username, Long id, ApplicationCreationDto applicationCreationDto) {
        Application application = applicationDao.getOne(id);
        application.setDate(applicationCreationDto.getDate());
        application.setAdopted(applicationCreationDto.isAdopted());
        application.setMultiChild(applicationCreationDto.isMultiChild());
        application.setGuardianStudent(applicationCreationDto.isGuardianDisabled());
        application.setGuardianDisabled(applicationCreationDto.isGuardianDisabled());
        application.setScore(applicationCreationDto.getScore());
        application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);
        application.setKindergartenApplicationForms(parseKindergartenApplications(applicationCreationDto,application));

        applicationDao.save(application);
    }
}
