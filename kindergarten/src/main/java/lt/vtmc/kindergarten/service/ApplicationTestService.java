package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationTestDto;
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
public class ApplicationTestService {
    @Autowired
    private ApplicationTestDao applicationTestDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private PersonTestDao personTestDao;

    @Transactional
    public void addApplication(@Valid ApplicationCreationTestDto applicationCreationTestDto) {
        PersonTest child = personTestDao.getOne(applicationCreationTestDto.getChildId());
        PersonTest firstParent = personTestDao.getOne(applicationCreationTestDto.getFirstParentId());
        PersonTest secondParent = personTestDao.getOne(applicationCreationTestDto.getSecondParentId());

        ApplicationTest applicationTest = new ApplicationTest();
        applicationTest.setDate(applicationCreationTestDto.getDate());

        applicationTest.setAdopted(applicationCreationTestDto.isAdopted());
        applicationTest.setMultiChild(applicationCreationTestDto.isMultiChild());
        applicationTest.setGuardianStudent(applicationCreationTestDto.isGuardianDisabled());
        applicationTest.setGuardianDisabled(applicationCreationTestDto.isGuardianDisabled());

        if (child.getCity() == CityEnum.VILNIUS) {
            applicationTest.setScore(countScore(applicationCreationTestDto) + 1);
        } else {
            applicationTest.setScore(countScore(applicationCreationTestDto));
        }

        applicationTest.setChildId(child);
        applicationTest.setParentId(firstParent);
        applicationTest.setSecondParentId(secondParent);

        applicationTest.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);

        applicationTest.setKindergartenApplicationForms(parseKindergartenApplications(applicationCreationTestDto, applicationTest));

        applicationTestDao.save(applicationTest);
    }

    private int countScore(ApplicationCreationTestDto applicationCreationDto) {
        int sumOfPriorities = 0;

        if (applicationCreationDto.isAdopted()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isGuardianDisabled()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isAdopted()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isGuardianStudent()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        return sumOfPriorities;
    }


    private Set<KindergartenApplicationForm> parseKindergartenApplications(ApplicationCreationTestDto applicationCreationTestDto, ApplicationTest applicationTest) {
        Map<Integer, Long> applicationMetadata = applicationCreationTestDto.getPriorityForKindergartenID();

        Set<KindergartenApplicationForm> kindergartenApplications = applicationMetadata.entrySet().stream().map(entry -> {
            Kindergarten kindergarten = kindergartenDao.getOne(entry.getValue());
            KindergartenApplicationForm kindergartenApplicationForm = new KindergartenApplicationForm();
            kindergartenApplicationForm.setKindergarten(kindergarten);
            kindergartenApplicationForm.setPriority(entry.getKey());
            kindergartenApplicationForm.setAccepted(false);
            kindergartenApplicationForm.setApplication(applicationTest);

            return kindergartenApplicationForm;

        }).collect(Collectors.toSet());

        return kindergartenApplications;

    }


}
