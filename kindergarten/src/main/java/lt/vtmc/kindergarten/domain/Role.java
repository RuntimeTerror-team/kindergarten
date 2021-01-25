package lt.vtmc.kindergarten.domain;

import org.hibernate.tuple.entity.EntityMetamodel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="role")
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany(mappedBy="role")
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    public RoleType getType() {
        return roleType;
    }

    public void setType(RoleType roleType) {
        this.roleType = roleType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
