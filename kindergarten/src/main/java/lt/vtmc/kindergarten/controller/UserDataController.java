package lt.vtmc.kindergarten.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lt.vtmc.kindergarten.dto.UserDataDto;
import lt.vtmc.kindergarten.service.UserDataService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping(value = "/api/user-data")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user data", notes = "Returns user zip file with user data by username")
    public void downloadUserData(@PathVariable String username, HttpServletResponse response) throws IOException {
        byte[] data = userDataService.getUserData(username);

        String fileName = "duomenys";

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentLength(data.length);
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".zip");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        InputStream inputStream = new ByteArrayInputStream(data);
        zipOutputStream.putNextEntry(new ZipEntry(fileName + ".json"));
        IOUtils.copy(inputStream, zipOutputStream);
        inputStream.close();
        zipOutputStream.closeEntry();
        zipOutputStream.close();

    }
}
