package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.domain.Queue;
import lt.vtmc.kindergarten.dto.*;

import lt.vtmc.kindergarten.exception.QueueDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;


import java.time.LocalDate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Validated
public class ApplicationService implements PagingLimit<ApplicationAfterDistribution>{
    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UserService userService;

    @Autowired
    private QueueDao queueDao;

    @Autowired
    private KindergartenApplicationFormService kindergartenApplicationService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationAfterDistributionDao applicationAfterDistributionDao;


    @Transactional
    public void addApplication(@Valid ApplicationCreationDto applicationCreationDto) {
        Queue queue = queueDao.findByStatus(QueueStatusEnum.ACTIVE);

        if (queue != null) {

            Person child = personDao.getOne(applicationCreationDto.getChildId());
            Person firstParent = personDao.getOne(applicationCreationDto.getFirstParentId());

            Person secondParent = null;
            boolean isSecondParent = false;
            if (applicationCreationDto.getSecondParentId() != null) {

                isSecondParent = true;
                secondParent = personDao.getOne(applicationCreationDto.getSecondParentId());

            }

            if (secondParent != null) {
                createParentUser(secondParent.getFirstName(), secondParent.getLastName());

            }

            Application application = applicationDao.findApplicationByChild(child);

            if (application == null) {
                application = new Application();

            } else {
                throw new RuntimeException("Application already exists");
            }


            application.setDate(java.sql.Date.valueOf(LocalDate.now()));

            application.setIsAdopted(applicationCreationDto.isAdopted());
            application.setIsMultiChild(applicationCreationDto.isMultiChild());
            application.setIsGuardianStudent(applicationCreationDto.isGuardianStudent());
            application.setIsGuardianDisabled(applicationCreationDto.isGuardianDisabled());

            if (child.getCity() == CityEnum.VILNIUS) {
                application.setScore(countScore(applicationCreationDto) + 10);
            } else {
                application.setScore(countScore(applicationCreationDto));
            }

            application.setChild(child);
            application.setParent(firstParent);
            if (isSecondParent) {
                application.setSecondParent(secondParent);
            }

            application.setQueue(queue);

            application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);

            application.setKindergartenApplicationForms(parseKindergartenApplications(applicationCreationDto, application));

            applicationDao.save(application);
        } else {
            throw new QueueDoesntExistException("Active queue must exists");
        }
    }

    @Transactional
    public List<ApplicationDto> getApplicationsList() {
        List<Application> applications = applicationDao.findAll();
        return applications.stream().map(application -> new ApplicationDto(application.getId(), application.getApplicationStatus(),
                application.getChild(), new PersonDto(application.getParent()),
                application.getScore(), application.getSecondParent(), application.getDate(), application.isAdopted(),
                application.isMultiChild(), application.isGuardianDisabled(), application.isGuardianStudent(),
                application.getKindergartenApplicationForms())).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public ApplicationCreationDto getApplication(Long id) {
        Application application = applicationDao.getOne(id);
        return new ApplicationCreationDto(application);
    }

    @Transactional
    public List<ApplicationCreationDto> getApplications() {
        List<Application> applications = applicationDao.findAll(Sort.by(Sort.Direction.ASC, "date"));
        List<ApplicationCreationDto> applicationList = applications.stream().map(application -> new ApplicationCreationDto(application))
                .collect(Collectors.toList());
        return applicationList;
    }

    @Transactional
    public List<ApplicationInfoDto> getApplicationsInfo(String username) {
        User user = userDao.findByUsername(username);
        Person parent = personDao.findByUser(user);

        Set<Application> applicationsByParent = applicationDao.findByParent(parent);

        List<ApplicationInfoDto> applicationInfoList = applicationsByParent.stream().map(application -> new ApplicationInfoDto(application)
        ).collect(Collectors.toList());

        return applicationInfoList;
    }
    
    @Transactional
    public List<DistributedApplicationInfoDto> getDistributedApplicationsInfo(String username) {
        User user = userDao.findByUsername(username);
        Person parent = personDao.findByUser(user);

        Set<Application> applications = applicationDao.findByParent(parent);
        Set<ApplicationAfterDistribution> distributedApplications = new LinkedHashSet<>();
        applications.stream().
        forEach(application -> distributedApplications.add(applicationAfterDistributionDao.findApplicationByApplicationId(application.getId())));

        List<DistributedApplicationInfoDto> distributedApplicationInfoList = distributedApplications.stream()
        		.map(distributedApplication -> new DistributedApplicationInfoDto(distributedApplication)
        ).collect(Collectors.toList());

        return distributedApplicationInfoList;
    }


    private int countScore(ApplicationCreationDto applicationCreationDto) {
        int sumOfPriorities = 0;

        if (applicationCreationDto.isAdopted()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isGuardianDisabled()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isMultiChild()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isGuardianStudent()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        return sumOfPriorities;
    }

    @Transactional
    public void updateApplication(Long id, ApplicationCreationDto applicationCreationDto) {
        Queue queue = queueDao.findByStatus(QueueStatusEnum.ACTIVE);

        if (queue != null) {
            Application application = applicationDao.getOne(id);

            Person child = personDao.getOne(applicationCreationDto.getChildId());
            Person firstParent = personDao.getOne(applicationCreationDto.getFirstParentId());
            Person secondParent = personDao.getOne(applicationCreationDto.getSecondParentId());

            application.setDate(applicationCreationDto.getDate());
            application.setIsAdopted(applicationCreationDto.isAdopted());
            application.setIsMultiChild(applicationCreationDto.isMultiChild());
            application.setIsGuardianStudent(applicationCreationDto.isGuardianDisabled());
            application.setIsGuardianDisabled(applicationCreationDto.isGuardianDisabled());

            if (child.getCity() == CityEnum.VILNIUS) {
                application.setScore(countScore(applicationCreationDto) + 10);
            } else {
                application.setScore(countScore(applicationCreationDto));
            }

            application.setChild(child);
            application.setParent(firstParent);
            application.setSecondParent(secondParent);

            application.setQueue(queue);

            application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);

            //Delete preexisting applications before applying new ones
            kindergartenApplicationService.deleteApplicationFormsByApplicationId(application.getId());

            Set<KindergartenApplicationForm> kindergartenApplicationForms = parseKindergartenApplications(applicationCreationDto, application);
            application.setKindergartenApplicationForms(kindergartenApplicationForms);

            applicationDao.save(application);
        } else {
            throw new QueueDoesntExistException("Active queue must exists");
        }

    }

    /**
     * Creates applications to concrete kindergartens
     *
     * @param applicationCreationDto dto that contains Map<Integer priority, Long kindergartenId>
     * @param application            application to which new KindergartenApplicationForms will be created
     * @return Set of application forms to specific kindergartens
     */
    private Set<KindergartenApplicationForm> parseKindergartenApplications(ApplicationCreationDto applicationCreationDto, Application application) {
        Map<Integer, Long> applicationMetadata = applicationCreationDto.getPriorityForKindergartenID();

        Set<KindergartenApplicationForm> kindergartenApplications = applicationMetadata.entrySet().stream().map(entry -> {
            Kindergarten kindergarten = kindergartenDao.getOne(entry.getValue());
            removeApplicationFormsFromApplication(application);
            removeApplicationFormsFromKindergarten(kindergarten, entry.getValue());

            KindergartenApplicationForm kindergartenApplicationForm = new KindergartenApplicationForm();
            kindergartenApplicationForm.setKindergarten(kindergarten);
            kindergartenApplicationForm.setPriority(entry.getKey());
            kindergartenApplicationForm.setAccepted(false);
            kindergartenApplicationForm.setApplication(application);

            return kindergartenApplicationForm;

        }).collect(Collectors.toSet());

        return kindergartenApplications;

    }

    private void removeApplicationFormsFromApplication(Application application) {
        application
                .setKindergartenApplicationForms(
                        application.getKindergartenApplicationForms()
                                .stream()
                                .filter(item -> item.getApplication().getId() != application.getId())
                                .collect(Collectors.toSet()));
    }

    private void removeApplicationFormsFromKindergarten(Kindergarten kindergarten, Long kindergartenIdToRemove) {
        kindergarten
                .setApplicationsSet(
                        kindergarten.getApplicationsSet()
                                .stream()
                                .filter(item -> item.getKindergarten().getId() != kindergartenIdToRemove)
                                .collect(Collectors.toSet()));
    }

    public void createParentUser(String firstName, String lastName) {
        userService.createGuardian(firstName, lastName);
    }


    @Transactional
    public List<ApplicationAfterDistributionDto> getApplicationsAfterDistribution() {
        List<ApplicationAfterDistribution> applications = applicationAfterDistributionDao.findAll(Sort.by(Sort.Direction.ASC, "status"));
        List<ApplicationAfterDistributionDto> applicationListAfterDistribution = applications.stream().map(application -> new ApplicationAfterDistributionDto(application))
                .collect(Collectors.toList());
        return applicationListAfterDistribution;
    }


    @Transactional
    public void persistApplicationsAfterDistribution(List<Application> applications) {
        AtomicReference<Long> waitingNum = new AtomicReference<>(1L);
        applications.stream().forEach(application -> {
            if (application.getApplicationStatus() != ApplicationStatusEnum.APPROVED) {

                ApplicationAfterDistribution applicationAfterDistribution = new ApplicationAfterDistribution();

                applicationAfterDistribution.setChildFirstName(application.getChild().getFirstName());
                applicationAfterDistribution.setChildLastName(application.getChild().getLastName());
                applicationAfterDistribution.setParentFirstName(application.getParent().getFirstName());
                applicationAfterDistribution.setParentLastName(application.getParent().getLastName());
                applicationAfterDistribution.setDate(application.getDate());
                applicationAfterDistribution.setScore(application.getScore());
                applicationAfterDistribution.setApplicationId(application.getId());
                applicationAfterDistribution.setChildPersonalCode(application.getChild().getPersonalCode());
                applicationAfterDistribution.setStatus(application.getApplicationStatus());
                applicationAfterDistribution.setWaitingNumber(waitingNum.get());
                waitingNum.getAndSet(waitingNum.get() + 1);
                applicationAfterDistributionDao.save(applicationAfterDistribution);

            } else if (application.getApplicationStatus() == ApplicationStatusEnum.APPROVED) {

                ApplicationAfterDistribution applicationAfterDistribution = new ApplicationAfterDistribution();

                applicationAfterDistribution.setChildFirstName(application.getChild().getFirstName());
                applicationAfterDistribution.setChildLastName(application.getChild().getLastName());
                applicationAfterDistribution.setParentFirstName(application.getParent().getFirstName());
                applicationAfterDistribution.setParentLastName(application.getParent().getLastName());
                applicationAfterDistribution.setDate(application.getDate());
                applicationAfterDistribution.setScore(application.getScore());
                applicationAfterDistribution.setStatus(application.getApplicationStatus());
                applicationAfterDistribution.setApplicationId(application.getId());
                applicationAfterDistribution.setChildPersonalCode(application.getChild().getPersonalCode());
                applicationAfterDistribution.setApprovedKindergarten(application.getKindergartenApplicationForms().stream()
                        .filter(item -> item.isAccepted())
                        .map(applicationForm -> applicationForm.getKindergarten().getTitle())
                        .collect(Collectors.joining(application.toString())));

                applicationAfterDistributionDao.save(applicationAfterDistribution);
            }
        });
    }


    @Transactional
    public void recalculateApplicationOrderInQueue() {
        applicationAfterDistributionDao.deleteAll();
        List<Application> applications = applicationDao.findAll();
        applications.stream().filter(application -> application.getApplicationStatus() != ApplicationStatusEnum.REJECTED)
                .forEachOrdered(application -> {
                    application.setApplicationStatus(ApplicationStatusEnum.WAITING);
                });
        calculateApplicationStatus();
    }
    
    

    /**
     * Assigns kindergartens according to the order of priority provided in the application
     */
    @Transactional
    public void calculateApplicationStatus() {
        List<Application> applications = getSortedApplications();

        applications.stream().forEach(application -> {
            application.getKindergartenApplicationForms().stream().forEach(applicationForm -> {
                applicationForm.getKindergarten().getGroups().forEach(group -> group.setOccupiedSpace(0));
                applicationForm.setAccepted(false);
            });
        });

        applications.stream()
                // Only check applications that are not yet approved
                .filter(application -> application.getApplicationStatus() == ApplicationStatusEnum.WAITING)
                .forEachOrdered(application -> {
                    application
                            .getKindergartenApplicationForms()
                            .stream()
                            .sorted(Comparator.comparing(KindergartenApplicationForm::getPriority))
                            .forEachOrdered(applicationForm -> {
                                applicationForm
                                        .getKindergarten()
                                        .getGroups()
                                        .stream()
                                        .sorted(Comparator.comparing(group -> group.getAgeRange().getAgeMin()))
                                        .forEachOrdered(group -> {
                                            boolean wasAccepted = application.getKindergartenApplicationForms().stream().filter(item -> item.isAccepted() == true)
                                                    .count() > 0;
                                            Integer age = PersonService.countChildAge(application.getChild().getPersonalCode());
                                            AgeRange ageRange = group.getAgeRange();
                                            // Check if there is a group that kid fits in by his age
                                            if (!wasAccepted && ageRange.getAgeMin() <= age && age <= ageRange.getAgeMax()) {
                                                Integer seatsAvailable = group.getChildrenCount() - group.getOccupiedSpace();
                                                // Check if following group has available seat
                                                if (seatsAvailable > 0) {
                                                    group.setOccupiedSpace(group.getOccupiedSpace() + 1);
                                                    applicationForm.setAccepted(true);
                                                    application.setApplicationStatus(ApplicationStatusEnum.APPROVED);
                                                    return;
                                                }
                                            }
                                        });
                            });
                });

        persistApplicationsAfterDistribution(applications);

        applications.stream().forEach(application -> application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED));
    }
    
    @Transactional
    public void changeApplicationStatus(Long id, String status) {
    
    	Optional<Application> application = applicationDao.findById(id);
    	application.get().setApplicationStatus(ApplicationStatusEnum.valueOf(status));
    	applicationDao.save(application.get());
    	ApplicationAfterDistribution applicationDistribution = applicationAfterDistributionDao.findApplicationByApplicationId(id);
    	applicationDistribution.setStatus(ApplicationStatusEnum.valueOf(status));
    	applicationAfterDistributionDao.save(applicationDistribution);
    		
    }


    /**
     * @return Map where key is Integer(priority number) and value Long (id of kindergarten)
     */
    public Map<Integer, Long> parseApplicationMetadata(Application application) {
        Set<KindergartenApplicationForm> kindergartenApplications = application.getKindergartenApplicationForms();
        Map<Integer, Long> applicationToPriority = new ConcurrentHashMap<>();
        kindergartenApplications.stream()
                .forEach(item -> applicationToPriority.put(item.getPriority(), item.getKindergarten().getId()));

        return applicationToPriority;
    }


    /**
     * Sorts application by score. If score is equal, then sorts by child age. If age is equal
     * then sorts by child last name.
     */
    @Transactional
    public List<Application> getSortedApplications() {

        List<Application> applications = applicationDao.findByApplicationStatus(ApplicationStatusEnum.WAITING);
        applications.sort((o1, o2) -> {
            if (o1.getScore() == o2.getScore()) {
                int age1 = PersonService.countChildAge(o1.getChild().getPersonalCode());
                int age2 = PersonService.countChildAge(o2.getChild().getPersonalCode());
                if (age1 == age2) {
                    return o1.getChild().getLastName().compareTo(o2.getChild().getLastName());
                } else {
                    if (age1 < age2) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            } else if (o1.getScore() < o2.getScore()) {
                return 1;
            } else {
                return -1;
            }
        });

        return applications;
    }
    
    @Transactional
    public ApplicationsStatisticsDto getApplicationsStatistics() {
    	
    	int nrOfApplications = (int) applicationDao.count();
    	
    	AtomicInteger nrOfKindergartenSpots = new AtomicInteger(0);
    	List<Set<Group>> groups = kindergartenDao.findAll().stream().map(kindergarten -> kindergarten.getGroups()).collect(Collectors.toList());
    	groups.stream().flatMap(group -> group.stream()).forEach(group -> {
    		
    	    int nrOfPlaces = group.getChildrenCount();
    	    nrOfKindergartenSpots.getAndAdd(nrOfPlaces);
    				});
    	
    	int nrOfWaitingApplications = (int) applicationDao.findAll()
    			.stream().filter(application -> application.getApplicationStatus() == ApplicationStatusEnum.WAITING).count();
    	
    	int nrOfApprovedApplications = (int) applicationDao.findAll()
    			.stream().filter(application -> application.getApplicationStatus() == ApplicationStatusEnum.APPROVED).count();
    	 	
    	return new ApplicationsStatisticsDto(nrOfApplications, nrOfKindergartenSpots.get(), nrOfWaitingApplications, nrOfApprovedApplications);	
    }


    @Override
    public Page<ApplicationAfterDistribution> findAll(Pageable pageable) {
        return applicationAfterDistributionDao.findAll(pageable);
    }

    @Override
    public Page<ApplicationAfterDistribution> findAll(Pageable pageable, String searchText) {
        return applicationAfterDistributionDao.findAllApplications(pageable, searchText);
    }
}
