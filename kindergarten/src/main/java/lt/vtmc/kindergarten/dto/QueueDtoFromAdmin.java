package lt.vtmc.kindergarten.dto;

import java.util.Date;

public class QueueDtoFromAdmin {


    private Date openingDate;


    public QueueDtoFromAdmin() {
    }

    public QueueDtoFromAdmin(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

}
