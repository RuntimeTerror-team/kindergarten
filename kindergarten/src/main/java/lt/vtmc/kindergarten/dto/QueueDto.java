package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Queue;
import lt.vtmc.kindergarten.domain.QueueStatusEnum;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class QueueDto {

    @NotNull
    private Date openingDate;

    @NotNull
    private Date closingDate;

    @Column
    private QueueStatusEnum status;

    public QueueDto() {
    }

    public QueueDto(@Valid Queue queue) {
        this.openingDate = queue.getOpeningDate();
        this.closingDate = queue.getClosingDate();
        this.status = queue.getStatus();
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
}
