package application;

public class EmployeeModel {
String airlineName; 
String employeeName; 
int staffID;
String typeOfPersonnel; 
String startShift;
String endShift; 

public void EmployeeModel(String airlineName, String employeeName, int staffID, String typeOfPersonnel, String startShift, String endShift){
	this.airlineName = airlineName; 
	this.employeeName = employeeName; 
	this.staffID = staffID; 
	this.typeOfPersonnel = typeOfPersonnel; 
	this.startShift = startShift; 
	this.endShift = endShift; 
}

public String getAirlineName() {
	return airlineName;
}

public void setAirlineName(String airlineName) {
	this.airlineName = airlineName;
}

public String getEmployeeName() {
	return employeeName;
}

public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}

public int getStaffID() {
	return staffID;
}

public void setStaffID(int staffID) {
	this.staffID = staffID;
}

public String getTypeOfPersonnel() {
	return typeOfPersonnel;
}

public void setTypeOfPersonnel(String typeOfPersonnel) {
	this.typeOfPersonnel = typeOfPersonnel;
}

public String getStartShift() {
	return startShift;
}

public void setStartShift(String startShift) {
	this.startShift = startShift;
}

public String getEndShift() {
	return endShift;
}

public void setEndShift(String endShift) {
	this.endShift = endShift;
}
}
