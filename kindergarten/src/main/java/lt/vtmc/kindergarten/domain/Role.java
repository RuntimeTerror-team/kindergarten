package lt.vtmc.kindergarten.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="role")
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleType type;

    @OneToMany(mappedBy="role")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(RoleType type) {
        this.type = type;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

}
