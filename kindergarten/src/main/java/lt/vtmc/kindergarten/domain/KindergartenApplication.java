package lt.vtmc.kindergarten.domain;


import javax.persistence.*;

@Entity
public class KindergartenApplication {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "kindergarten")
    private Kindergarten kindergarten;

    @Column
    private int priority;

    @Column
    private boolean isSiblingInvolved;

    @Column
    private boolean isAccepted;

    public KindergartenApplication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Kindergarten getKindergarten() {
        return kindergarten;
    }

    public void setKindergarten(Kindergarten kindergarten) {
        this.kindergarten = kindergarten;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isSiblingInvolved() {
        return isSiblingInvolved;
    }

    public void setSiblingInvolved(boolean siblingInvolved) {
        isSiblingInvolved = siblingInvolved;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
