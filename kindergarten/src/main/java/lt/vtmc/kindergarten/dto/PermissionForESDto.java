package lt.vtmc.kindergarten.dto;

public class PermissionForESDto {
	
	private boolean isAllowed;
	
	
	public PermissionForESDto() {
		
	}
	
	public PermissionForESDto(boolean isAllowd) {
		this.isAllowed = isAllowd;
	}
	
	public boolean getIsAllowed() {
		   return isAllowed;
	   }
	   
     public void setIsAllowed(boolean isAllowed) {
		   this.isAllowed = isAllowed;
	   }

}
