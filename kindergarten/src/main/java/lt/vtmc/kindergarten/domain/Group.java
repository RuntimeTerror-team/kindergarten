package lt.vtmc.kindergarten.domain;


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
    private int ageMin;

    @Column
    private int ageMax;

    @Column
    private int childrenCount;


    @ManyToOne
    @JoinColumn(name = "kindergarten_id")
    private Kindergarten kindergarten;

    public Group() {
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

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Kindergarten getKindergarten() {
        return kindergarten;
    }

    public void setKindergartenId(Kindergarten kindergarten) {
        this.kindergarten = kindergarten;
    }
}
