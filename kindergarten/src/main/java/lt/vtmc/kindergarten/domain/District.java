package lt.vtmc.kindergarten.domain;


import javax.persistence.*;

@Entity
@Table(name = "district")
public class District {

    @Id
    @Column(unique = true)
    private String title;

    public District() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
