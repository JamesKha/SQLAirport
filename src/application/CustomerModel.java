package application;

public class CustomerModel {
	String fName; 
	String lName; 
	int idNumber; 
	
	String inTime; 
	String outTime; 
	int tripNumber; 
	
	public void customerModel(String fName, String lName, int idNumber, String inTime, String outTime, int tripNumber) {
		this.fName = fName; 
		this.lName = lName; 
		this.idNumber = idNumber; 
		this.inTime = inTime; 
		this.outTime = outTime; 
		this.tripNumber = tripNumber; 
	}


    public String getInTime() {
		return inTime;
	}


	public void setInTime(String inTime) {
		this.inTime = inTime;
	}


	public String getOutTime() {
		return outTime;
	}


	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}


	public int getTripNumber() {
		return tripNumber;
	}


	public void setTripNumber(int tripNumber) {
		this.tripNumber = tripNumber;
	}


	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
}
