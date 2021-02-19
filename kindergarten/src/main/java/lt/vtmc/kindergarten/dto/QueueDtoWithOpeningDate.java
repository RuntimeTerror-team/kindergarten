package lt.vtmc.kindergarten.dto;

import java.util.Date;

public class QueueDtoWithOpeningDate {


    private Date openingDate;


    public QueueDtoWithOpeningDate() {
    }

    public QueueDtoWithOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

}
