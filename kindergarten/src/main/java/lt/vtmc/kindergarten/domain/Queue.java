package lt.vtmc.kindergarten.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "queues")
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingDate;


    @Column(unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationClosingDate;

    @Column
    @Enumerated(EnumType.STRING)
    private QueueStatusEnum status;

    @OneToMany(mappedBy = "queue")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Application> applications = new HashSet<>();

    public Queue() {
    }


    public Date getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    public void setRegistrationClosingDate(Date registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public QueueStatusEnum getStatus() {
        return status;
    }

    public void setStatus(QueueStatusEnum status) {
        this.status = status;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
