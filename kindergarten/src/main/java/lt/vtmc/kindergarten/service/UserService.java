package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.domain.Role;
import lt.vtmc.kindergarten.domain.RoleType;
import lt.vtmc.kindergarten.domain.User;
import lt.vtmc.kindergarten.dto.UserDtoFromAdmin;
import lt.vtmc.kindergarten.dto.UserFromService;
import lt.vtmc.kindergarten.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;


    @Transactional(readOnly = true)
    public List<UserFromService> getUsers() {
        return userDao.findAll()
                .stream()
                .map(user -> new UserFromService(
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPersonalCode(),
                        user.getPassword(),
                        user.getRole().getType().toString()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createUser(UserFromService userFromService) {
        String hashedPassword = getHash(userFromService.getPassword().getBytes(), "SHA-256");
        Role role = new Role();


        if (userDao.findByUsername(userFromService.getUsername()) == null) {
            User newUser = new User(
                    userFromService.getUsername(),
                    userFromService.getFirstName(),
                    userFromService.getLastName(),
                    userFromService.getPersonalCode(),
                    hashedPassword
            );

            if (userFromService.getRole().equals("ADMIN")) {
                if (userDao.findByRole(new Role(RoleType.ADMIN)) == null) {
                    role.setType(RoleType.ADMIN);
                    newUser.setRole(role);
                    role.addUser(newUser);
                    userDao.save(newUser);
                }
            } else if (userFromService.getRole().equals("EDUCATION_SPECIALIST")) {
                if (userDao.findByRole(new Role(RoleType.EDUCATION_SPECIALIST)) == null) {
                    role.setType(RoleType.EDUCATION_SPECIALIST);
                    newUser.setRole(role);
                    role.addUser(newUser);
                    userDao.save(newUser);
                }
            } else {
                role.setType(RoleType.GUARDIAN);
                newUser.setRole(role);
                role.addUser(newUser);
                userDao.save(newUser);
            }
        }
    }

    private String getHash(byte[] inputBytes, String algorithm) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } catch (Exception e) {

        }
        return hashValue;
    }

    @Transactional(readOnly = true)
    public UserFromService getUser(String username) {
        User user = userDao.findByUsername(username);
        return new UserFromService(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPersonalCode(),
                user.getPassword(),
                user.getRole().getType().toString()
        );
    }

    @Transactional
    public String createUserFromAdmin(UserDtoFromAdmin userDtoFromAdmin) {

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

            if (userDao.findByUsername(possibleUsername) == null) {
                String hashedPassword = getHash(possibleUsername.getBytes(), "SHA-256");
                Role finalRole = new Role();

                User newUser = new User(
                        possibleUsername,
                        goodFirstName,
                        goodLastName,
                        null,
                        hashedPassword
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

    private String createEducationSpecialist(String fName, String lName) {
        String eduSpecUsername = "ŠvietimoSpecialistas1";
        String hashedPassword = getHash(eduSpecUsername.getBytes(), "SHA-256");
        Role finalRole = new Role();

        if (userDao.findByRole(new Role(RoleType.EDUCATION_SPECIALIST)) == null) {
            User eduSpec = new User(
                    eduSpecUsername,
                    fName,
                    lName,
                    null,
                    hashedPassword
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
                user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(
                        new String[]{"ROLE_" + user.getRole().getType()}));
    }

    private User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
