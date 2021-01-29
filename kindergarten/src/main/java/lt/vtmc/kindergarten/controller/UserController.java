package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.vtmc.kindergarten.dto.AnswerAboutCreatedUser;
import lt.vtmc.kindergarten.dto.UserDtoFromAdmin;
import lt.vtmc.kindergarten.dto.UserInfo;
import lt.vtmc.kindergarten.dto.UserFromService;
import lt.vtmc.kindergarten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get users", notes = "Returns registered users")
    public List<UserInfo> getUsers() {
        return userService.getUsers()
                .stream()
                .map(ufs -> new UserInfo(
                        ufs.getUsername(),
                        ufs.getFirstName(),
                        ufs.getLastName(),
                        ufs.getPersonalCode(),
                        ufs.getPassword(),
                        ufs.getRole()))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user", notes = "Creates user with data")
    public void createUser(@ApiParam(value = "User Data", required = true) @Valid @RequestBody UserInfo userInfo) {
        userService.createUser(new UserFromService(
                userInfo.getUsername(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPersonalCode(),
                userInfo.getPassword(),
                userInfo.getRole()
        ));
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user", notes = "Returns user by username")
    public UserInfo getUser(@PathVariable String username) {
        UserFromService user = userService.getUser(username);
        return new UserInfo(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPersonalCode(),
                user.getPassword(),
                user.getRole()
        );
    }

    // admin creating new user
    @RequestMapping(path = "/admin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user from admin page", notes = "Creates user with data from admin page")
    public String createUserFromAdmin(@ApiParam(value = "User Data", required = true) @Valid @RequestBody UserDtoFromAdmin userDtoFromAdmin) {
        return userService.createUserFromAdmin(userDtoFromAdmin);
    }

    /*
    //       TODO: leave for security and modify if needed
    @RequestMapping(path = "/role", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user", notes = "Returns user by username")
    public Role getRole(String username, String password) {
        return userService.getRole(username, password);
    }
     */
}
