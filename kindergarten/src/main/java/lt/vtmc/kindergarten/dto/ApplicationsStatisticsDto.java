package lt.vtmc.kindergarten.dto;

public class ApplicationsStatisticsDto {
	
	private int nrOfApplications;
	private int nrOfKindergartenSpots;
	private int nrOfWaitingApplications;
	private int nrOfApprovedApplications;
	
	public ApplicationsStatisticsDto(int nrOfApplications, int nrOfKindergartenSpots, int nrOfWaitingApplications,
			int nrOfApprovedApplications) {
		this.nrOfApplications = nrOfApplications;
		this.nrOfKindergartenSpots = nrOfKindergartenSpots;
		this.nrOfWaitingApplications = nrOfWaitingApplications;
		this.nrOfApprovedApplications = nrOfApprovedApplications;
	}

	public int getNrOfApplications() {
		return nrOfApplications;
	}

	public void setNrOfApplications(int nrOfApplications) {
		this.nrOfApplications = nrOfApplications;
	}

	public int getNrOfKindergartenSpots() {
		return nrOfKindergartenSpots;
	}

	public void setNrOfKindergartenSpots(int nrOfKindergartenSpots) {
		this.nrOfKindergartenSpots = nrOfKindergartenSpots;
	}

	public int getNrOfWaitingApplications() {
		return nrOfWaitingApplications;
	}

	public void setNrOfWaitingApplications(int nrOfWaitingApplications) {
		this.nrOfWaitingApplications = nrOfWaitingApplications;
	}

	public int getNrOfApprovedApplications() {
		return nrOfApprovedApplications;
	}

	public void setNrOfApprovedApplications(int nrOfApprovedApplications) {
		this.nrOfApprovedApplications = nrOfApprovedApplications;
	}
	
	
	
	

}
