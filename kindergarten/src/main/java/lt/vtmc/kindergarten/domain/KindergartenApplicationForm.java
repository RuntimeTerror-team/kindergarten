package lt.vtmc.kindergarten.domain;


import javax.persistence.*;

@Entity
@Table(name = "kindergarten_application")
public class KindergartenApplicationForm {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application")
    private ApplicationTest application;

    @ManyToOne
    @JoinColumn(name = "kindergarten")
    private Kindergarten kindergarten;

    @Column
    private Integer priority;

    @Column
    private boolean isAccepted;

    public KindergartenApplicationForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationTest getApplication() {
        return application;
    }

    public void setApplication(ApplicationTest application) {
        this.application = application;
    }

    public Kindergarten getKindergarten() {
        return kindergarten;
    }

    public void setKindergarten(Kindergarten kindergarten) {
        this.kindergarten = kindergarten;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
