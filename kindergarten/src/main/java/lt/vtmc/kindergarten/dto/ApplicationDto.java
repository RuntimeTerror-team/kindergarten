package lt.vtmc.kindergarten.dto;

import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.ApplicationStatusEnum;
import lt.vtmc.kindergarten.domain.KindergartenApplicationForm;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class ApplicationDto {

   private String username;

   private Long childId;

   private Date date;

   private Set<KindergartenApplicationForm> kindergartenApplications;

   private int score;

   private boolean isAdopted;

   private boolean isMultiChild;

   private boolean isGuardianStudent;

   private boolean isGuardianDisabled;

   private ApplicationStatusEnum applicationStatusEnum;

   public ApplicationDto() {
   }

   public ApplicationDto(Application  application, String username) {
      this.username= username;
      this.childId = application.getChild().getId();
      this.date=application.getDate();
      this.kindergartenApplications=application.getKindergartenApplications();
      this.score=application.getScore();
      this.isAdopted=application.isAdopted();
      this.isMultiChild=application.isMultiChild();
      this.isGuardianStudent=application.isGuardianStudent();
      this.isGuardianStudent=application.isGuardianDisabled();
      this.applicationStatusEnum=application.getApplicationStatus();
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

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

   public Set<KindergartenApplicationForm> getKindergartenApplications() {
      return kindergartenApplications;
   }

   public void setKindergartenApplications(Set<KindergartenApplicationForm> kindergartenApplications) {
      this.kindergartenApplications = kindergartenApplications;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

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

   public ApplicationStatusEnum getApplicationStatusEnum() {
      return applicationStatusEnum;
   }

   public void setApplicationStatusEnum(ApplicationStatusEnum applicationStatusEnum) {
      this.applicationStatusEnum = applicationStatusEnum;
   }
}
