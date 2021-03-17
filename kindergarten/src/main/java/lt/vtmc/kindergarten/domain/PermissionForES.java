package lt.vtmc.kindergarten.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "permissionsForES")
public class PermissionForES {

    @Id
    private Long id;

    @NotNull
    private boolean isAllowed;

    public PermissionForES() {
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = 1L;
    }

   public boolean getIsAllowed() {
	   return isAllowed;
   }
   
   public void setIsAllowed(boolean isAllowed) {
	   this.isAllowed = isAllowed;
   }
}

