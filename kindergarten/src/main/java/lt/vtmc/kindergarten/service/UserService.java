package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.UserDao;
import lt.vtmc.kindergarten.domain.Role;
import lt.vtmc.kindergarten.domain.RoleType;
import lt.vtmc.kindergarten.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
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
    }
}
