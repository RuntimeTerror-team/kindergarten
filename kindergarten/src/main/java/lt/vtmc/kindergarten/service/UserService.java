package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.domain.Role;
import lt.vtmc.kindergarten.domain.RoleType;
import lt.vtmc.kindergarten.domain.User;
import lt.vtmc.kindergarten.dto.UserFromService;
import lt.vtmc.kindergarten.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService /*implements UserDetailsService*/ {
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

        if (userDao.findUserByUsername(userFromService.getUsername()) == null) {
            User newUser = new User(
                    userFromService.getUsername(),
                    userFromService.getFirstName(),
                    userFromService.getLastName(),
                    userFromService.getPersonalCode(),
                    userFromService.getPassword()
            );

            switch (userFromService.getRole()) {
                case "ADMIN":
                   Role adminRole = new Role(RoleType.ADMIN);
                   newUser.setRole(adminRole);
                   adminRole.addUser(newUser);

                   userDao.save(newUser);
            }
        }
    }

    @Transactional(readOnly = true)
    public UserFromService getUser(String username) {
        User user = userDao.findUserByUsername(username);
        return new UserFromService(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPersonalCode(),
                user.getPassword(),
                user.getRole().getType().toString()
        );
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
