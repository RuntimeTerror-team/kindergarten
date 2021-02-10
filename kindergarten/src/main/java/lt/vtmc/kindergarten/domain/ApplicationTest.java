package lt.vtmc.kindergarten.domain;

import javax.persistence.*;

@Entity
public class ApplicationTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne
//    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_person_id")
    private PersonTest childId;

//    @ManyToOne
//    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_person_id")
    private PersonTest parentId;

//    @ManyToOne
//    @JoinColumn(nullable = true)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_parent_person_id")
    private PersonTest secondParentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonTest getChildId() {
        return childId;
    }

    public void setChildId(PersonTest childId) {
        if(childId.getId() != parentId && childId.getId() != secondParentId ) {
            this.childId = childId;
        } else {

        }
    }

    public PersonTest getParentId() {
        return parentId;
    }

    public void setParentId(PersonTest parentId) {
//        parentId.addApplication(this);
        this.parentId = parentId;
    }

    public PersonTest getSecondParentId() {
        return secondParentId;
    }

    public void setSecondParentId(PersonTest secondParentId) {
//        secondParentId.addApplication(this);
        this.secondParentId = secondParentId;
    }
}
