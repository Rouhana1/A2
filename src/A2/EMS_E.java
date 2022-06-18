package A2;

import java.sql.Date;

public class EMS_E {
	private int VehicleID;
	private String EMSname;
	private int EMSNUMBER;
	private Date Year;

	//We are defining this additional constructor, but it is not used for now
	public EMS_E(int VehicleID, String EMSname, int EMSNUMBER, Date Year) {
		this.VehicleID = VehicleID;
		this.EMSname = EMSname;
		this.EMSNUMBER = EMSNUMBER;
		this.Year = Year;
	}
	
	//We use this constructor instead since we only need the contractor number
	public EMS_E(int contractorNumber) {
		this.VehicleID = contractorNumber;
	}

	public int getNumber() {
		return VehicleID;
	}

	public String getName() {
		return EMSname;
	}

	public int getDailyRate() {
		return EMSNUMBER;
	}

	public Date getYearHired() {
		return Year;
	}

}
