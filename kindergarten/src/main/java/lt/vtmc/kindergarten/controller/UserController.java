package lt.vtmc.kindergarten.controller;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.domain.User;
import lt.vtmc.kindergarten.dto.PermissionForESDto;
import lt.vtmc.kindergarten.dto.UserDetailsDto;
import lt.vtmc.kindergarten.dto.UserDtoFromAdmin;
import lt.vtmc.kindergarten.dto.UserDto;
import lt.vtmc.kindergarten.dto.UserValidateCommandDto;
import lt.vtmc.kindergarten.dto.UsernameDto;
import lt.vtmc.kindergarten.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private Marker userEvent = MarkerFactory.getMarker("AUDIT_EVENT");
    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get users", notes = "Returns registered users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/search/all")
    @ApiOperation(value = "Get users", notes = "Returns registered users")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get users", notes = "Returns registered users")
    @RequestMapping(method = RequestMethod.GET, value = "/search/{searchText}")
    public ResponseEntity<Page<User>> findAllUsers(Pageable pageable, @PathVariable final String searchText) {

        return new ResponseEntity(userService.findAll(pageable, searchText), HttpStatus.OK);
    }

//   /* TODO - check if in use */
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation(value = "Create user", notes = "Creates user with data")
//    public void createUser(@ApiParam(value = "User Data", required = true) @Valid @RequestBody UserDto userDto) {
//        userService.createUser(userDto);
//        logger.info(userEvent, "Sukurtas vartotojas. Vartotojo vardas: {}. Vartotojo rolė: {} Sukūrimo laikas {}", userDto.getUsername(), userDto.getRole(), new Date());
//    }
////
////    /* TODO - check if in use */
//    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Get user", notes = "Returns user by username")
//    public UserDto getUser(@PathVariable String username) {
//        return userService.getUser(username);
//    }

    @RequestMapping(path = "/admin", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user from admin page", notes = "Creates user with data from admin page")
    public String createUserFromAdmin(@ApiParam(value = "User Data", required = true) @Valid @RequestBody UserDtoFromAdmin userDtoFromAdmin) {
        String user = userService.createUserFromAdmin(userDtoFromAdmin);
        logger.info(userEvent, "Administratorius sukūrė vartotoją {} {}. Vartotojo rolė: {}. Sukūrimo laikas: {}", userDtoFromAdmin.getFirstName(), userDtoFromAdmin.getLastName(), userDtoFromAdmin.getRole(), new Date());
        return user;
    }

    @PreAuthorize("hasRole('GUARDIAN')")
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

//    /* TODO - check if in use */
//    @ApiOperation(value = "Get user validation info", notes = "Returns validity data on person associated with user")
//    @RequestMapping(method = RequestMethod.POST, value = "/{username}/validate")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity validateUser(
//            @ApiParam(value = "Username", required = true)
//            @Valid
//            @PathVariable String username
//    ) {
//        try {
//            UserValidateCommandDto userValidationData = userService.getUserValidityData(username);
//            return new ResponseEntity(userValidationData, HttpStatus.OK);
//        } catch (RuntimeException exception) {
//            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PreAuthorize("hasAnyRole('EDUCATION_SPECIALIST', 'GUARDIAN')")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value ="Change user password")
    public void changePassword(@Valid @RequestBody UserDto userDto){
    	userService.changePassword(userDto);
        logger.info(userEvent,"Vartotojas {} Vartotojo rolė: {} Pakeitė slaptažodį. Įvykio laikas: {}", userDto.getUsername(), userDto.getRole(), new Date());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value ="Restore original password", notes = "Restores user's original password")
    @RequestMapping(method = RequestMethod.POST, value = "/restore")
    @ResponseStatus(HttpStatus.OK)
    public void restoreOriginalPassword(@Valid @RequestBody final UsernameDto usernameDto){
    	userService.restoreOriginalPassword(usernameDto);
        logger.info(userEvent,"Vartotojas {} pakeitė slaptažodį į pradinį. Įvykio laikas: {}", usernameDto.getUsername(), new Date());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ES/permission")
    @ApiOperation(value = "set ES permission", notes = "set ES permission to edit applications status")
    @ResponseStatus(HttpStatus.OK)
    public void setESPermission(@Valid @RequestBody PermissionForESDto permission) {
    	userService.setESPermision(permission);
        if(permission.getIsAllowed()){
            logger.info(userEvent,"Administratorius suteikė leidimą prašymų sąrašo redagavimui švietimo specialistui. Įvykio laikas: {}", new Date());
        } else {
            logger.info(userEvent,"Administratorius atšaukė leidimą prašymų sąrašo redagavimui švietimo specialistui. Įvykio laikas: {}", new Date());
        }
    }

    @PreAuthorize("hasAnyRole('EDUCATION_SPECIALIST', 'ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/ES/permission")
    @ApiOperation(value = "get ES permission status", notes = "gets ES permissions status")
    @ResponseStatus(HttpStatus.OK)
    public boolean getESPermissionStatus() {
    	return userService.getESPermisionStatus();
    }
}
