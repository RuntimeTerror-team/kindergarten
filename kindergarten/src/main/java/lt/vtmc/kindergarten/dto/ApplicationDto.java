package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.KindergartenApplication;
import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.Child;
import lt.vtmc.kindergarten.domain.User;
import lt.vtmc.kindergarten.domain.UserApplication;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ApplicationDto {

   @NotNull
   private Long id;

//   @NotNull
//   private Set<UserApplication> users = new HashSet<UserApplication>();

   @NotNull
   private Long childId;


   private Date date;



//    private Set<KindergartenApplication> kindergartenApplications = new HashSet<>();


//   @NotNull
//   private Child child;


   private boolean isAdopted;

   private boolean isMultiChild;

   private boolean isGuardianStudent;

   private boolean isGuardianDisabled;


   public ApplicationDto() {
   }


   public ApplicationDto(Application application) {
      this.id = application.getId();
//      this.users = application.getUsers();
      this.childId = application.getChildId();
      this.date = application.getDate();
//      this.child = application.getChild();
      this.isAdopted = application.isAdopted();
      this.isMultiChild = application.isMultiChild();
      this.isGuardianStudent = application.isGuardianStudent();
      this.isGuardianDisabled = application.isGuardianDisabled();


   }


   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

//   public Set<UserApplication> getUsers() {
//      return users;
//   }
//
//   public void setUsers(Set<UserApplication> users) {
//      this.users = users;
//   }

   public Long getChildId() {
      return childId;
   }

   public void setChildId(Long childId) {
      this.childId = childId;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }
//
//   public Child getChild() {
//      return child;
//   }
//
//   public void setChild(Child child) {
//      this.child = child;
//   }

   public boolean isAdopted() {
      return isAdopted;
   }

   public void setAdopted(boolean adopted) {
      isAdopted = adopted;
   }

   public boolean isMultiChild() {
      return isMultiChild;
   }

   public void setMultiChild(boolean multiChild) {
      isMultiChild = multiChild;
   }

   public boolean isGuardianStudent() {
      return isGuardianStudent;
   }

   public void setGuardianStudent(boolean guardianStudent) {
      isGuardianStudent = guardianStudent;
   }

   public boolean isGuardianDisabled() {
      return isGuardianDisabled;
   }

   public void setGuardianDisabled(boolean guardianDisabled) {
      isGuardianDisabled = guardianDisabled;
   }
}
