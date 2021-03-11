package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import lt.vtmc.kindergarten.domain.HealthForm;
import lt.vtmc.kindergarten.dto.HealthFileResponse;
import lt.vtmc.kindergarten.message.ResponseMessage;
import lt.vtmc.kindergarten.service.HealthFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping(value = "/api/health-forms")
public class HealthFormController {

    @Autowired
    private HealthFormService storageService;

    @PostMapping("/{childId}")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long childId) {
        String message = "";
        try {
            storageService.store(file, childId);
            message = "Failas sėkmingai išsaugotas: " + file.getOriginalFilename();
            var returnObj = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

            if (returnObj == null) {
                message = "Netinkamas failo formatas: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
            return returnObj;
        } catch (Exception e) {
            message = "Failo išsaugoti nepavyko: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping
    @ApiOperation(value = "Get all health forms", notes = "Returns a list of all health forms")
    public ResponseEntity<List<HealthFileResponse>> getListFiles() {
        List<HealthFileResponse> files = storageService.getAllFiles();

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/family/{username}")
    @ApiOperation(value = "Get children health forms of a person", notes = "Returns a list of of health forms for children of a person by username")
    public ResponseEntity<List<HealthFileResponse>> getListFilesByUserFamily(@PathVariable final String username) {
        List<HealthFileResponse> files = storageService.getListFilesByUserFamily(username);

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    
    @GetMapping("/singleForm/{id}")
    @ApiOperation(value = "Get healthForm by childId", notes = "Returns a healthForm by child id")
    public ResponseEntity<HealthFileResponse> getHealthFormByChildId(@PathVariable final Long id) {
        HealthFileResponse response = storageService.getHealthFormByChildId(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get one file", notes = "Returns a file by id")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        HealthForm healthForm = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + healthForm.getName() + "\"")
                .body(healthForm.getData());
    }
}