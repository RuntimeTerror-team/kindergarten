package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.PermissionForESDao;
import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.domain.PermissionForES;
import lt.vtmc.kindergarten.domain.Person;
import lt.vtmc.kindergarten.domain.Role;
import lt.vtmc.kindergarten.domain.RoleType;
import lt.vtmc.kindergarten.domain.User;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class UserService implements UserDetailsService, PagingLimit<User> {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PersonDao personDao;
    
    @Autowired
    private PermissionForESDao permissionForESDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        return userDao.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRole().getType().toString()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createUser(@Valid UserDto userDto) {
        if (userDao.findUserByUsername(userDto.getUsername()) == null) {
            User newUser = new User(
                    userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword())
            );

            if (userDto.getRole().equals("ADMIN")) {
                if (userDao.findByRole(new Role(RoleType.ADMIN)) == null) {
                    Role adminRole = new Role(RoleType.ADMIN);
                    newUser.setRole(adminRole);
                    adminRole.addUser(newUser);
                    userDao.save(newUser);
                }
            } else if (userDto
                    .getRole().equals("EDUCATION_SPECIALIST")) {
                if (userDao.findByRole(new Role(RoleType.EDUCATION_SPECIALIST)) == null) {
                    Role eduSpecRole = new Role(RoleType.EDUCATION_SPECIALIST);
                    newUser.setRole(eduSpecRole);
                    eduSpecRole.addUser(newUser);
                    userDao.save(newUser);
                }
            } else {
                Role guardianRole = new Role(RoleType.GUARDIAN);
                newUser.setRole(guardianRole);
                guardianRole.addUser(newUser);
                userDao.save(newUser);
            }
        }
    }

    @Transactional(readOnly = true)
    public UserDto getUser(String username) {
        User user = userDao.findUserByUsername(username);
        return new UserDto(
                user.getUsername(),
                user.getPassword(),
                user.getRole().getType().toString()
        );
    }

    @Transactional
    public String createUserFromAdmin(@Valid UserDtoFromAdmin userDtoFromAdmin) {

        String roleFromAdmin = userDtoFromAdmin.getRole();

        if (roleFromAdmin.equals("EDUCATION_SPECIALIST")) {
            return createEducationSpecialist(userDtoFromAdmin.getFirstName(), userDtoFromAdmin.getLastName());
        } else {
            return createGuardian(userDtoFromAdmin.getFirstName(), userDtoFromAdmin.getLastName());
        }
    }

    @Transactional(readOnly = true)
    public UserValidateCommandDto getUserValidityData(String username){
        UserValidateCommandDto userValidationData = new UserValidateCommandDto();
        userValidationData.setUsername(username);
        userValidationData = checkIfAsociatedPersonExists(userValidationData,username);
        return userValidationData;
    }

    private UserValidateCommandDto checkIfAsociatedPersonExists(UserValidateCommandDto userValidation,String username) {
        User user = userDao.findUserByUsername(username);

        if(user != null) {
            Person person = personDao.findByUser(user);
            if(person!=null){
                userValidation.setAssociatedPersonExists(true);
                return userValidation;
            } else {
                userValidation.setAssociatedPersonExists(false);
                return userValidation;
            }
        } else {
            throw new RuntimeException("User doesn't exist");
        }
    }

    @Transactional(readOnly = true)
    public Object getUserDetails(String username) {
        Optional user = userDao.findById(username);
        Person person = null;

        if (user.isPresent()) {
            person = personDao.findByUser((User) user.get());
        }

        if(person == null){
            return "No person associated with user: " + username;
        }

        return new UserDetailsDto(person, username);
    }
    
    @Transactional
    public void changePassword(UserDto userDto) {
    	
    	User user = userDao.findByUsername(userDto.getUsername());
    	
    	if(user != null) {
    		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    	}
    }
    
    @Transactional
    public void restoreOriginalPassword(UsernameDto usernameDto) {
    	
    	User user = userDao.findByUsername(usernameDto.getUsername());
    	
    	if(user != null) {
    		user.setPassword(passwordEncoder.encode(usernameDto.getUsername()));
    	}
    }

    private String assembleUsername(String firstName, String lastName) {
        return checkUsernameLength(sanitizeNameToPascalCase(firstName)+sanitizeNameToPascalCase(lastName));
    }

    public String createGuardian(String firstName, String lastName) {

        int defaultNum = 1;

        String username = assembleUsername(firstName, lastName);

        while (true) {

            String possibleUsername = username + defaultNum;

            if (possibleUsername.length() > 30) {
                possibleUsername = username.substring(0, username.length() - 1) + defaultNum;
            }

            if (userDao.findByUsername(possibleUsername) == null) {
                String encodedPassword = passwordEncoder.encode(possibleUsername);
                Role finalRole = new Role();

                User newUser = new User(
                        possibleUsername,
                        encodedPassword
                );

                finalRole.setType(RoleType.GUARDIAN);
                newUser.setRole(finalRole);
                finalRole.addUser(newUser);
                userDao.save(newUser);

                return possibleUsername;

            } else {
                defaultNum++;
            }
        }
    }

    private String checkUsernameLength(String usernameToCheck) {
        if (usernameToCheck.length() < 7) {
            while ((usernameToCheck).length() < 7) {
                usernameToCheck += 0;
            }
        }

        if (usernameToCheck.length() > 29) {
            while ((usernameToCheck).length() > 29) {
                usernameToCheck = usernameToCheck.substring(0, usernameToCheck.length() - 1);
            }
        }

        return usernameToCheck;
    }

    private String createEducationSpecialist(String firstName, String lastName) {
        //FIXME String eduSpecUsername = assembleUsername(firstName, lastName);
        String eduSpecUsername = "ŠvietimoSpecialistas1";
        String encodedPassword = passwordEncoder.encode(eduSpecUsername);
        Role finalRole = new Role();

        if (userDao.findByRole(new Role(RoleType.EDUCATION_SPECIALIST)) == null) {
            User eduSpec = new User(
                    eduSpecUsername,
                    encodedPassword
            );

            finalRole.setType(RoleType.EDUCATION_SPECIALIST);
            eduSpec.setRole(finalRole);
            finalRole.addUser(eduSpec);
            userDao.save(eduSpec);

            return eduSpecUsername;
        }
        return "Švietimo specialistas jau egzistuoja. Prisijungimo vardas: " + eduSpecUsername;
    }

    // Security

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username + " not found.");

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(
                        new String[]{"ROLE_" + user.getRole().getType()}));
    }

    private User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    private String sanitizeNameToPascalCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
    
    @Transactional
    public void setESPermision(PermissionForESDto permissionForESDto) {
    	Optional<PermissionForES> permission = permissionForESDao.findById(1L);
    	permission.get().setIsAllowed(permissionForESDto.getIsAllowed());
    }
    
    @Transactional(readOnly = true)
    public boolean getESPermisionStatus() {
    	Optional<PermissionForES> permission = permissionForESDao.findById(1L);
    	return permission.get().getIsAllowed();
    	
    }


    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public Page<User> findAll(Pageable pageable, String searchText) {
        return userDao.findAllUsers(pageable, searchText);
    }
}
