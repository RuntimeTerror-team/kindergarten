package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Child;
import lt.vtmc.kindergarten.domain.User;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ApplicationDto {

   @NotNull
   private Long id;

   private Set<User> users = new HashSet<User>();

   private Long childId;

   private Date date;



//    private Set<KindergartenApplication> kindergartenApplications = new HashSet<>();


   private Child child;

   private boolean isAdopted;

   private boolean isMultiChild;

   private boolean isGuardianStudent;

   private boolean isGuardianDisabled;

   public ApplicationDto() {
   }
}
