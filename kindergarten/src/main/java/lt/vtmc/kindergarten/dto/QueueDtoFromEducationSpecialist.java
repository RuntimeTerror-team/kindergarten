package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Queue;

import java.util.Date;

public class QueueDtoFromEducationSpecialist {


    private Date closingDate;

    private Date registrationClosingDate;

    public QueueDtoFromEducationSpecialist() {
    }

    public QueueDtoFromEducationSpecialist(Queue queue) {
        this.closingDate = queue.getClosingDate();
        this.closingDate = queue.getRegistrationClosingDate();
    }

    public Date getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    public void setRegistrationClosingDate(Date registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }


    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

}
