package lt.vtmc.kindergarten.User.controller;

import io.swagger.annotations.ApiOperation;
import lt.vtmc.kindergarten.User.service.UserFromService;
import lt.vtmc.kindergarten.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user", notes = "Returns user by username")
    public UserInfo getUser(UserInfo userInfo) {
        String username = userInfo.getUsername();
        UserFromService user = userService.getUser(username);
        return new UserInfo(
                username,
                user.getFirstName(),
                user.getLastName(),
                user.getPersonalCode(),
                user.getPassword(),
                user.getRole()
        );
    }
}
