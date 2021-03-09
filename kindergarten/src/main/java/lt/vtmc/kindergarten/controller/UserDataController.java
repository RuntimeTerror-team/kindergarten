package lt.vtmc.kindergarten.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lt.vtmc.kindergarten.dto.UserDataDto;
import lt.vtmc.kindergarten.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@RestController
@RequestMapping(value = "/api/user-data")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user data", notes = "Returns user zipped file with user data by username")
    public ResponseEntity<byte[]> downloadUserData(@PathVariable String username) throws JsonProcessingException {
        UserDataDto udd = userDataService.getUserData(username);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(udd);
        byte[] data = json.getBytes(StandardCharsets.UTF_8);

        String fileName = "duomenys.json";

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(data.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return new ResponseEntity<>(data, respHeaders, HttpStatus.OK);
    }
}
