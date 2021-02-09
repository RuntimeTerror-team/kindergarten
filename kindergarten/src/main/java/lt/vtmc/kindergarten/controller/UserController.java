package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.UserDtoFromAdmin;
import lt.vtmc.kindergarten.dto.UserDto;
import lt.vtmc.kindergarten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
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
        return userService.createUserFromAdmin(userDtoFromAdmin);
    }
}
