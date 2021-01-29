package lt.vtmc.kindergarten.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ageRanges")
public class AgeRange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int ageMin;

    private int ageMax;

    @OneToMany(mappedBy = "ageRange")
    private Set<Group> groups = new HashSet<>();

    public AgeRange() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
