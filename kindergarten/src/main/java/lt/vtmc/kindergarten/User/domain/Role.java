package lt.vtmc.kindergarten.User.domain;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private RoleType type;
//
//    @OneToMany(mappedBy = "user")
//    private List<User> users;
//
    public Role() {
    }
//
//    public Role(lt.vtmc.kindergarten.User.domain.RoleType type) {
//        this.type = type;
//    }
}
