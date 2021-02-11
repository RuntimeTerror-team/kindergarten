package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.domain.Role;
import lt.vtmc.kindergarten.domain.RoleType;
import lt.vtmc.kindergarten.domain.User;
import lt.vtmc.kindergarten.dto.UserDto;
import lt.vtmc.kindergarten.dto.UserDtoFromAdmin;
import lt.vtmc.kindergarten.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class UserService /*implements UserDetailsService*/ {
    @Autowired
    private UserDao userDao;


    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        return userDao.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getUsername(),
//                        user.getFirstName(),
//                        user.getLastName(),
//                        user.getPersonalCode(),
                        user.getPassword(),
                        user.getRole().getType().toString()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createUser(@Valid UserDto userDto) {
        if (userDao.findUserByUsername(userDto.getUsername()) == null) {
            User newUser = new User(
                    userDto.getUsername(),
//                    userDto.getFirstName(),
//                    userDto.getLastName(),
//                    userDto.getPersonalCode(),
                    userDto.getPassword()
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
//                user.getFirstName(),
//                user.getLastName(),
//                user.getPersonalCode(),
                user.getPassword(),
                user.getRole().getType().toString()
        );
    }

    @Transactional
    public String createUserFromAdmin(@Valid UserDtoFromAdmin userDtoFromAdmin) {

        String roleFromAdmin = userDtoFromAdmin.getRole();

        String goodFirstName = userDtoFromAdmin.getFirstName().substring(0, 1).toUpperCase() + userDtoFromAdmin.getFirstName().substring(1).toLowerCase();
        String goodLastName = userDtoFromAdmin.getLastName().substring(0, 1).toUpperCase() + userDtoFromAdmin.getLastName().substring(1).toLowerCase();


        if (roleFromAdmin.equals("EDUCATION_SPECIALIST")) {
            return createEducationSpecialist(goodFirstName, goodLastName);
        } else {
            return createGuardian(goodFirstName, goodLastName);
        }
    }

    private String createGuardian(String goodFirstName, String goodLastName) {

        int defaultNum = 1;

        String fullNameUsername = goodFirstName + goodLastName;

        String goodUsername = checkUsernameLength(fullNameUsername);

        while (true) {

            String possibleUsername = goodUsername + defaultNum;

            if (possibleUsername.length() > 30) {
                possibleUsername = goodUsername.substring(0, goodUsername.length() - 1) + defaultNum;
            }

            if (userDao.findUserByUsername(possibleUsername) == null) {
                User newUser = new User(
                        possibleUsername,
//                        goodFirstName,
//                        goodLastName,
//                        null,
                        possibleUsername
                );

                Role finalRole = new Role(RoleType.GUARDIAN);
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

    private String createEducationSpecialist(String fName, String lName) {
        String eduSpecUsername = "ŠvietimoSpecialistas1";

        if (userDao.findByRole(new Role(RoleType.EDUCATION_SPECIALIST)) == null) {
            User eduSpec = new User(
                    eduSpecUsername,
//                    fName,
//                    lName,
//                    null,
                    eduSpecUsername
            );

            Role finalRole = new Role(RoleType.EDUCATION_SPECIALIST);
            eduSpec.setRole(finalRole);
            finalRole.addUser(eduSpec);
            userDao.save(eduSpec);

            return eduSpecUsername;
        }
        return "Švietimo specialistas jau egzistuoja. Prisijungimo vardas: " + eduSpecUsername;
    }

    /*
    //       TODO: leave for security and modify if needed
    public Role getRole(String username, String password) {
        User user = userDao.findUserByUsername(username);
        if (user.getPassword().equals(password)) {
             user.getRole().getType().toString();
        }
        return null;
    }
      */

   /* Temporarily disable all security functions
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    // FIXME uncomment once user creation is implemented
        //        User user = findById(id);
//
//        if(user==null){
//            throw new UsernameNotFoundException("User not found");
//        }
        return new org.springframework.security.core.userdetails.User(
                "admin",
                "admin",
                AuthorityUtils.createAuthorityList(
                        new String[] {
                                "ROLE_ADMIN"
                        }
                )
        );
//        return new org.springframework.security.core.userdetails.User(
//                user.getId(),
//                user.getPassword(),
//                AuthorityUtils.createAuthorityList(
//                        new String[] {
//                            "ROLE_" + user.getRole()
//                        }
//                )
//        );

    };


    @Transactional(readOnly=true)
    public User findById(String id){
        Optional user = userDao.findById(id);
        return (User) user.get();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/
}
