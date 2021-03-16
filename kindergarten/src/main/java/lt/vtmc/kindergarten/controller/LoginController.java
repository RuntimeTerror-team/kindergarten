package lt.vtmc.kindergarten.controller;

import io.swagger.annotations.ApiOperation;
import lt.vtmc.kindergarten.dto.UserDetailsDto;
import lt.vtmc.kindergarten.service.PersonService;
import lt.vtmc.kindergarten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('GUARDIAN', 'EDUCATION_SPECIALIST')")
    @ApiOperation(value="Get a username of the user that is logged in",notes ="Returns a username of the user that is logged in")
    @RequestMapping(path = "/api/loggedUsername", method = RequestMethod.GET)
    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return "not logged";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GUARDIAN', 'EDUCATION_SPECIALIST')")
    @ApiOperation(value="Get a role of the user that is logged in",notes ="Returns a role of the user that is logged in")
    @RequestMapping(path = "/api/loggedRole", method = RequestMethod.GET)
    public String getLoggedInRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String role = authentication.getAuthorities().toArray()[0].toString();
            return role;
        }
        return "no role";
    }

    @PreAuthorize("hasRole('GUARDIAN')")
    @ApiOperation(value="Get information if a user that is logged in has user details",notes ="Returns information if a user that is logged in has user details")
    @RequestMapping(path = "/api/loggedWithDetails", method = RequestMethod.GET)
    public Boolean hasPersonDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Object userInfo = userService.getUserDetails(currentUserName);
        return userInfo instanceof UserDetailsDto;
    }
}