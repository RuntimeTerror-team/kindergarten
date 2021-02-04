package lt.vtmc.kindergarten.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "groups") // Must be groups as group is reserved name in h2
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private int childrenCount;

    @ManyToOne
    @JoinColumn(name = "kindergarten_id")
    private Kindergarten kindergarten;

    @ManyToOne
    @JoinColumn(name = "ageRange_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private AgeRange ageRange;

    public Group() {
    }

    public Kindergarten getKindergarten() {
        return kindergarten;
    }

    public void setKindergartenId(Kindergarten kindergarten) {
        this.kindergarten = kindergarten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public void setKindergarten(Kindergarten kindergarten) {
        this.kindergarten = kindergarten;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

}
