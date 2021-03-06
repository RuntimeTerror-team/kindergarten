package lt.vtmc.kindergarten.controller;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.KindergartenApplication;
import lt.vtmc.kindergarten.dto.UserDetailsDto;
import lt.vtmc.kindergarten.dto.UserDtoFromAdmin;
import lt.vtmc.kindergarten.dto.UserDto;
import lt.vtmc.kindergarten.dto.UserValidateCommandDto;
import lt.vtmc.kindergarten.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get users", notes = "Returns registered users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user", notes = "Creates user with data")
    public void createUser(@ApiParam(value = "User Data", required = true) @Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        logger.info("User {} with role {} created at {}", userDto.getUsername(), userDto.getRole(), new Date());
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user", notes = "Returns user by username")
    public UserDto getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    // admin creating new user
    @RequestMapping(path = "/admin", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user from admin page", notes = "Creates user with data from admin page")
    public String createUserFromAdmin(@ApiParam(value = "User Data", required = true) @Valid @RequestBody UserDtoFromAdmin userDtoFromAdmin) {
        String user = userService.createUserFromAdmin(userDtoFromAdmin);
        logger.info("User for person {} {} with role {} created at {}", userDtoFromAdmin.getFirstName(), userDtoFromAdmin.getLastName(), userDtoFromAdmin.getRole(), new Date());
        return user;
    }

    @RequestMapping(path = "/{username}/details", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user details", notes = "Returns user details by username")
    public ResponseEntity<?> getUserDetails(@PathVariable String username) {
        Object userInfo = userService.getUserDetails(username);

        if (userInfo instanceof UserDetailsDto) {
            return new ResponseEntity(userInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userInfo, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Get user validation info", notes = "Returns validity data on person associated with user")
    @RequestMapping(method = RequestMethod.POST, value = "/{username}/validate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity validateUser(
            @ApiParam(value = "Username", required = true)
            @Valid
            @PathVariable String username
    ) {
        try {
            UserValidateCommandDto userValidationData = userService.getUserValidityData(username);
            return new ResponseEntity(userValidationData, HttpStatus.OK);
        } catch (RuntimeException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value ="Change user password")
    public void changePassword(@Valid @RequestBody UserDto userDto){
    	userService.changePassword(userDto);
        logger.info("User {} with role {} password changed at {}", userDto.getUsername(), userDto.getRole(), new Date());
    }
}
