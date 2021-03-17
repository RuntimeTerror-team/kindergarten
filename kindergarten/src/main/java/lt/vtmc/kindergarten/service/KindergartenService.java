package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.ApplicationDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.Kindergarten;
import lt.vtmc.kindergarten.domain.KindergartenApplicationForm;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import lt.vtmc.kindergarten.dto.KindergartenInfoDto;
import lt.vtmc.kindergarten.dto.MostPopularKindergartensDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class KindergartenService {

    @Autowired
    private KindergartenDao kindergartenDao;
    
    @Autowired
    private ApplicationDao applicationDao;

    @Transactional(readOnly = true)
    public List<KindergartenInfoDto> getKindergartens(){
        List<Kindergarten> kindergartens = kindergartenDao.findAll();
        List<KindergartenInfoDto> kindergartenList= kindergartens.stream().map(kindergarten -> new KindergartenInfoDto(kindergarten)).collect(Collectors.toList());
        return kindergartenList;
    }

    @Transactional(readOnly = true)
    public KindergartenInfoDto getKindergarten(Long id){
        Kindergarten kindergarten = kindergartenDao.getOne(id);
        return new KindergartenInfoDto(kindergarten);
    }

    @Transactional
    public void addKindergarten(@Valid KindergartenDto kindergartenDto){
        Kindergarten kindergarten = new Kindergarten();

        kindergarten.setTitle(kindergartenDto.getTitle());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setPhoneNumber(kindergartenDto.getPhoneNumber());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setCity(kindergartenDto.getCity());
        kindergarten.setPostalCode(kindergartenDto.getPostalCode());
        kindergarten.setEmail(kindergartenDto.getEmail());
        kindergarten.setCompanyCode(kindergartenDto.getCompanyCode());
        kindergarten.setDistrict(kindergartenDto.getDistrict());
        kindergarten.setWebsite(kindergartenDto.getWebsite());

        kindergartenDao.save(kindergarten);
    }


    @Transactional
    public void updateKindergarten(Long id,@Valid KindergartenDto kindergartenDto){
        Kindergarten kindergarten = kindergartenDao.getOne(id);

        kindergarten.setTitle(kindergartenDto.getTitle());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setPhoneNumber(kindergartenDto.getPhoneNumber());
        kindergarten.setAddress(kindergartenDto.getAddress());
        kindergarten.setCity(kindergartenDto.getCity());
        kindergarten.setPostalCode(kindergartenDto.getPostalCode());
        kindergarten.setEmail(kindergartenDto.getEmail());
        kindergarten.setCompanyCode(kindergartenDto.getCompanyCode());
        kindergarten.setDistrict(kindergartenDto.getDistrict());
        kindergarten.setWebsite(kindergartenDto.getWebsite());

        kindergartenDao.save(kindergarten);
    }

    @Transactional(readOnly = true)
    public Kindergarten getKindergartenByCompanyCode(String companyCode) {
        try {
            return kindergartenDao.findByCompanyCode(companyCode);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    public MostPopularKindergartensDto getMostPopularKindergartens() {
       
    	List<Long> kindergartensIds = kindergartenDao.findAll().stream().map(kindergarten -> kindergarten.getId()).collect(Collectors.toList());
    	
    	List<Set<KindergartenApplicationForm>> kindergartensForms = applicationDao.findAll()
    			.stream().map(kindergarten -> kindergarten.getKindergartenApplicationForms()).collect(Collectors.toList());
    	List<Long> kindergartensIdsFromApplications = kindergartensForms.stream().
    	flatMap(list -> list.stream().map(form -> form.getKindergarten().getId())).collect(Collectors.toList());
    	kindergartensIdsFromApplications.addAll(kindergartensIds);
    		   	
    	Map<Object, Long> map = kindergartensIdsFromApplications.stream().collect(Collectors.groupingBy(nr -> nr, Collectors.counting()));		
    	Map<Long, Long> newMap = new LinkedHashMap(map);
    	Map<Long, Long> finalMap = new LinkedHashMap();
    	newMap.forEach((Long k, Long v) -> finalMap.put(k, v - 1) );
    	newMap = sortByValue(finalMap);
    		
    	ArrayList<Long> idsArray = new ArrayList<Long>(newMap.keySet());
    	Collections.reverse(idsArray);
    	
    	Optional<Kindergarten> firstKindergarten = kindergartenDao.findById(idsArray.get(0));
    	Optional<Kindergarten> secondKindergarten = kindergartenDao.findById(idsArray.get(1));
    	Optional<Kindergarten> thirdKindergarten = kindergartenDao.findById(idsArray.get(2));
    	Optional<Kindergarten> fourthKindergarten = kindergartenDao.findById(idsArray.get(3));
    	Optional<Kindergarten> fifthKindergarten = kindergartenDao.findById(idsArray.get(4));
    	Optional<Kindergarten> fromEndFirstKindergarten = kindergartenDao.findById(idsArray.get(idsArray.size()-1));
    	Optional<Kindergarten> fromEndSecondKindergarten = kindergartenDao.findById(idsArray.get(idsArray.size()-2));
    	Optional<Kindergarten> fromEndThirdKindergarten = kindergartenDao.findById(idsArray.get(idsArray.size()-3));
    	Optional<Kindergarten> fromEndFourthKindergarten = kindergartenDao.findById(idsArray.get(idsArray.size()-4));
    	Optional<Kindergarten> fromEndFifthKindergarten = kindergartenDao.findById(idsArray.get(idsArray.size()-5));
   
    	return new MostPopularKindergartensDto(firstKindergarten.get().getTitle(), secondKindergarten.get().getTitle()
    	  ,thirdKindergarten.get().getTitle() , fourthKindergarten.get().getTitle(), fifthKindergarten.get().getTitle(),
    	  fromEndFirstKindergarten.get().getTitle(), fromEndSecondKindergarten.get().getTitle(),fromEndThirdKindergarten.get().getTitle(),
    	  fromEndFourthKindergarten.get().getTitle(), fromEndFifthKindergarten.get().getTitle(), firstKindergarten.get().getAddress(),
    	  secondKindergarten.get().getAddress(), thirdKindergarten.get().getAddress(), fourthKindergarten.get().getAddress(),
    	  fifthKindergarten.get().getAddress(), fromEndFirstKindergarten.get().getAddress(), fromEndSecondKindergarten.get().getAddress(),
    	  fromEndThirdKindergarten.get().getAddress(), fromEndFourthKindergarten.get().getAddress(), fromEndFifthKindergarten.get().getAddress());
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
