package lt.vtmc.kindergarten.service;


import lt.vtmc.kindergarten.dao.ApplicationDao;
import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.ApplicationStatusEnum;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


//@Service
public class Algorithm {

//    @Autowired
//    private ApplicationDao applicationDao;

    //TODO EITI PER SORTED PRASYMUS IR PRISKIRTI JIEMS DARZELIUS, KEISTI STATUSUS FORMUOTI NR LAUKIMO EILEJE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


//    @Transactional
//    public List<ApplicationCreationDto> getSortedApplications() {
//        List<Application> applications = applicationDao.findByApplicationStatus(ApplicationStatusEnum.WAITING);
//
//        applications.sort(Comparator.comparing(Application::getScore).thenComparing((o1, o2) -> {
//            int age1 = countChildAge(o1.getChild().getPersonalCode());
//            int age2 = countChildAge(o2.getChild().getPersonalCode());
//            if (age1 == age2) {
//                return o1.getChild().getLastName().compareTo(o2.getChild().getLastName());
//            } else {
//                if (age1 > age2) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        }));
//
//        List<ApplicationCreationDto> applicationList = applications
//                .stream()
//                .map(application -> new ApplicationCreationDto(application))
//                .collect(Collectors.toList());
//
//        return applicationList;
//    }


//    static class compareByAgeThenChildLastName implements Comparator<Application> {
//        @Override
//        public int compare(Application o1, Application o2) {
//            int age1 = countChildAge(o1.getChild().getPersonalCode());
//            int age2 = countChildAge(o2.getChild().getPersonalCode());
//
//            if (age1 == age2) {
//                return o1.getChild().getLastName().compareTo(o2.getChild().getLastName());
//            } else {
//                if (age1 > age2) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        }
//    }


//    public static int countChildAge(String personalCode) {
//        int birthdayYear = Integer.parseInt(personalCode.substring(1, 3));
//        LocalDate localDate = LocalDate.now();
//        int currentYear = localDate.getYear() - 2000;
//        return currentYear - birthdayYear;
//    }

}
