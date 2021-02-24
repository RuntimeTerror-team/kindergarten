package lt.vtmc.kindergarten.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lt.vtmc.kindergarten.dao.HealthFormRepository;
import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.domain.HealthForm;
import lt.vtmc.kindergarten.domain.Person;
import lt.vtmc.kindergarten.dto.HealthFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class HealthFormService {
    @Autowired
    private HealthFormRepository healthFormRepository;
    @Autowired
    private PersonDao personDao;


    public HealthForm store(MultipartFile file, Long childId) throws IOException {
        HealthForm healthForm = healthFormRepository.getHealthFormByChildId(childId);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (healthForm == null) {
            healthForm = new HealthForm(fileName, file.getContentType(), file.getBytes());
            Person child = personDao.getOne(childId);
            healthForm.setChild(child);
        } else {
            healthForm.setName(fileName);
            healthForm.setType(file.getContentType());
            healthForm.setData(file.getBytes());
        }

        healthForm.setDate(java.sql.Date.valueOf(LocalDate.now()));
        
        return healthFormRepository.save(healthForm);
    }

    public HealthForm getFile(String id) {
        return healthFormRepository.findById(id).get();
    }

    public List<HealthFileResponse> getAllFiles() {
       return healthFormRepository
                .findAll()
                .stream()
                .map(dbFile -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/health-forms/")
                            .path(dbFile.getId())
                            .toUriString();

                    return new HealthFileResponse(
                            dbFile.getName(),
                            fileDownloadUri,
                            dbFile.getType(),
                            dbFile.getData().length);
                }).collect(Collectors.toList());
    }
}