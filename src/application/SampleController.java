package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class SampleController {
	ObservableList<CustomerModel> customerList = FXCollections.observableArrayList(); 
	ObservableList<EmployeeModel> employeeList = FXCollections.observableArrayList(); 

	@FXML 
	TableView<CustomerModel> table; 
	
	@FXML 
	TableView<EmployeeModel> employeeTable; 
	
	@FXML
	TableColumn<CustomerModel, Integer> idNumber; 
	@FXML 
	TableColumn<CustomerModel, Integer> tripNumber;
	@FXML 
	TableColumn<CustomerModel, String> inTime; 
	@FXML 
	TableColumn<CustomerModel, String> outTime; 
	
	@FXML 
	TableColumn<EmployeeModel, String> employeeName; 
	@FXML 
	TableColumn<EmployeeModel, String> typeOfPersonnel;
	
	@FXML
	TableColumn<EmployeeModel, String> startShift; 
	
	@FXML
	TableColumn<EmployeeModel, String> endShift; 
	
	

	@FXML
     TextField firstName;

    @FXML
     TextField lastName;

    @FXML
     TextField idNumberInsert;

    @FXML
     DatePicker insertInboundDate;

    @FXML
     DatePicker insertOutboundDate;

    @FXML
     Button setInformationButton;

    @FXML
     Button findInformationButton;

    @FXML
     TextField identificationNumberDeleteField;

    @FXML
     Button identificationNumberDeleteButton;
    
    @FXML
    TextField staffIDField; 
    
    @FXML 
    Button findStaffButton; 
    
    Connection connection = null; 
    
    @FXML
    public void setInformation() {
 

    	String customerQuery = "insert customer (firstName, lastName, identificationNumber) values (?, ? , ?)"; 
    	String takesQuery = "insert takes values (?, ?)";
    	String planeTripQuery = "insert PlaneTrip values (?, ?, ?, ?)" ; 
    	

    	try {
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AirportSQL", "root", "Fireblood1"); 
    		PreparedStatement truncateStatement = connection.prepareStatement("truncate customer");
    		PreparedStatement truncateStatement2 = connection.prepareStatement("truncate takes");
    		PreparedStatement truncateStatement3 = connection.prepareStatement("truncate planetrip");
    		 
    		
    		ResultSet results = connection.createStatement().executeQuery("select * from airlineemployee"); 
    		results.first(); 
    		
    		while (results.next() ) {
    			EmployeeModel employee = new EmployeeModel();
    			employee.setAirlineName(results.getString("airlineName"));
    			employee.setStaffID(results.getInt("staffID"));
    			employee.setTypeOfPersonnel(results.getString("typeOfPersonnel"));
    			employee.setEmployeeName(results.getString("employeeName"));
    			employee.setStartShift(results.getString("startShift"));
    			employee.setEndShift(results.getString("endShift"));
    			
    			
    			employeeList.add(employee); 
    			
    		}

    		
    		employeeName.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeName"));
    		typeOfPersonnel.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("typeOfPersonnel"));
    		startShift.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("startShift"));
    		endShift.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("endShift"));
    		
    	
    		
    		PreparedStatement customerStatement = connection.prepareStatement(customerQuery);
    		PreparedStatement takesStatement = connection.prepareStatement(takesQuery);
    		PreparedStatement planeTripStatement = connection.prepareStatement(planeTripQuery); 
    				
    		customerStatement.setString(1, firstName.getText());
    		customerStatement.setString(2, lastName.getText());
    		customerStatement.setInt(3, Integer.valueOf(idNumberInsert.getText()));
    		
    		takesStatement.setInt(1, 3);
    		takesStatement.setInt(2, 1000);
    		
    		planeTripStatement.setString(1, "Generic Airlines");
    		planeTripStatement.setInt(2, 1000);
    		planeTripStatement.setString(3, insertInboundDate.getValue().toString());
    		planeTripStatement.setString(4, insertOutboundDate.getValue().toString());
    		
  
    		
    		truncateStatement.execute(); 
    		truncateStatement2.execute();
    		truncateStatement3.execute();
    		customerStatement.executeUpdate(); 
    		takesStatement.execute(); 
    		planeTripStatement.execute(); 
    		
    		
    		Statement myStmt = connection.createStatement(); 
    		ResultSet customerResultList = myStmt.executeQuery("select * from customer c, takes t, planeTrip p");
    		
    		while (customerResultList.next()) {
    			CustomerModel customer = new CustomerModel(); 
    			customer.setfName(customerResultList.getString("firstName"));
    			customer.setlName(customerResultList.getString("lastName"));
    			customer.setIdNumber(customerResultList.getInt("identificationNumber")); 
    			customer.setInTime(customerResultList.getString("timeInbound"));
    			customer.setOutTime(customerResultList.getString("timeOutbound"));
    			customer.setTripNumber(customerResultList.getInt("tripNumber"));
    			customerList.add(customer); 
    			
    		}
    	
    		
    		table.setItems(customerList);
    			
    		idNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
    		tripNumber.setCellValueFactory(new PropertyValueFactory<>("tripNumber"));
    		inTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
    		outTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void updateTable() {
    	tripNumber.setCellValueFactory(new PropertyValueFactory<>("tripNumber"));
		inTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
		outTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
    }

    
    @FXML
    public void findInformation(){
    	
    	String selectQuery = "select * from customer c, takes t, planeTrip p where c.identificationNumber = ?";
    	try { 
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AirportSQL", "root", "Fireblood1");
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setInt(1, Integer.valueOf(identificationNumberDeleteField.getText())); 

    		ResultSet result = selectStatement.executeQuery(); 
    		result.first(); 
			ResultBox.display(result.getString("firstName"), result.getString("firstName") + " " + result.getString("lastName") + " is flying with " + result.getString("airlineName") + " on " + result.getString("timeInbound") + " and arrives on " + result.getString("timeOutbound"));
    	
			updateTable(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    @FXML
    public void findStaffInformation() {
    	String selectQuery = "select * from airlineemployee where staffID = ?"; 
    	try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AirportSQL", "root", "");
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setInt(1, Integer.valueOf(staffIDField.getText()));
			ResultSet result = selectStatement.executeQuery(); 
    		result.first(); 
    		ResultBox.display(result.getString("staffID"), result.getString("employeeName") + ", the " + result.getString("typeofPersonnel") +", has a shift that starts at " + result.getString("StartShift") + " and ends at " + result.getString("EndShift")); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    @FXML
    public void deleteInformation(){
    	String deleteQuery = "delete from customer where identificationNumber = ?";
    	try { 
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, Integer.valueOf(identificationNumberDeleteField.getText())); 
			System.out.println(identificationNumberDeleteField.getText());
			int rows = deleteStatement.executeUpdate(); 
			System.out.println("Rows impacted: " + rows);
    		customerList.removeIf(customer -> customer.idNumber == Integer.valueOf(identificationNumberDeleteField.getText()) );
			
			updateTable(); 
    		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }

}

