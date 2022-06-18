package A2;

public class Response_R {
	private int ReportNumber;
	private String PersonID;
	private String SexDescription;
	
	public Response_R(int ReportNumber, String PersonID, String SexDescription) {
		this.ReportNumber = ReportNumber;
		this.PersonID = PersonID;
		this.SexDescription = SexDescription;
	}
	
	public int getProjectNumber() {
		return ReportNumber;
	}
	
	public String getProjectName() {
		return PersonID;
	}
	
	public String getProjectDescription() {
		return SexDescription;
	}
	
}
