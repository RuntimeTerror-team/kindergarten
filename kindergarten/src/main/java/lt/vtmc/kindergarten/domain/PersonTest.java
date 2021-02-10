package lt.vtmc.kindergarten.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PersonTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
