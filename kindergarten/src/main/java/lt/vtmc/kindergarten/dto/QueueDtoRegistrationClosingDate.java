package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Queue;

import java.util.Date;

public class QueueDtoRegistrationClosingDate {

    private Date registrationClosingDate;

    public QueueDtoRegistrationClosingDate() {
    }

    public QueueDtoRegistrationClosingDate(Queue queue) {
        this.registrationClosingDate = queue.getRegistrationClosingDate();
    }

    public Date getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    public void setRegistrationClosingDate(Date registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

}
