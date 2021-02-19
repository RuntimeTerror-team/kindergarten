package lt.vtmc.kindergarten.dto;

import java.util.Date;

public class QueueDtoClosingDate {

    private Date closingDate;

    public QueueDtoClosingDate() {
    }

    public QueueDtoClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }
}
