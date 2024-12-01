import com.mysql.cj.jdbc.DatabaseMetaData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.awt.event.*; // Importing AWT event classes
import java.awt.*; 
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginController {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "root";
     
     private User user;
     private Login login;
     private Register register;
     
     private CardLayout cardLayout;
     private JPanel mainPanel;
     private JFrame frame;
     
     // Constructor
     public LoginController(Login login, Register register, JPanel mainPanel, JFrame frame){
	  
	  this.login = login;
	  this.register = register;
	  
	  this.mainPanel = mainPanel;
	  this.cardLayout = (CardLayout) mainPanel.getLayout();
	  this.frame = frame;
	  
	  // Section for Login View functionality
	  this.login.getLoginBtn().addActionListener(new LoginBtnListener());
	  this.login.getRegisterBtn().addActionListener(new RegisterBtnListener());
	  
     }
     
     // Login.java Login Button functionality
     class LoginBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	       String username = login.getUsername();
	       String password = login.getPassword();
	       if (LoginAuthentication(username, password)){
		    addAttendance(username, password);
		    if (isAdmin(username, password)){
			 frame.setSize(800, 600);
			 frame.setLocationRelativeTo(null);
			 cardLayout.show(mainPanel, "AdminPortal");
		    }
	       } else {
		    System.out.println("Error");
	       }
	  }
     }
     
     class RegisterBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Switch to the registration panel
            cardLayout.show(mainPanel, "Register");
            frame.setSize(500, 550);
	    frame.setLocationRelativeTo(null);
        }
    }
     
     // Login Authentication
     public boolean LoginAuthentication(String username, String password) {
	  String sql = "SELECT * FROM Users WHERE BINARY username = ? AND password = ? AND Status = 'Enabled'";

	  try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	       PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	     // Set the parameters
	     preparedStatement.setString(1, username);
	     preparedStatement.setString(2, password);


	     // Execute the query
	     ResultSet resultSet = preparedStatement.executeQuery();

	     // Check if a user exists with the provided credentials
	     boolean result = resultSet.next();
	     return result;

	 } catch (Exception e) {
	     e.printStackTrace(); // Handle exceptions appropriately in production code
	     return false;
	  }
     }
     
     public static int generateID(){
	  Random rand = new Random();
	  return 100000 + rand.nextInt(900000);
     }
     
     public static void addFacilityOthers(String type, String name){
	  String sql = "INSERT INTO facilitiesAndOThers (status,type, name) VALUES ('enabled',?,?)";
	  
	 try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	       PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	      // Set the parameters for the prepared statement
	      preparedStatement.setString(1, type);
	      preparedStatement.setString(2, name);
	      
	      

	  } catch (Exception e) {
	      e.printStackTrace(); // Handle exceptions appropriately in production code
	  }
     }
     
     public static void disableFacilityOthers(String type, String name){
	  String sql = "UPDATE facilitiesAndOThers SET status = 'disabled' WHERE status = 'enabled' AND type = ? AND name = ?;";
	  
	 try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	       PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	      // Set the parameters for the prepared statement
	      preparedStatement.setString(1, type);
	      preparedStatement.setString(2, name);
	      
	      

	  } catch (Exception e) {
	      e.printStackTrace(); // Handle exceptions appropriately in production code
	  }
     }
     
     public static void enableFacilityOthers(String type, String name){
	  String sql = "UPDATE facilitiesAndOThers SET status = 'enabled' WHERE status = 'disabled' AND type = ? AND name = ?;";
	  
	 try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	       PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	      // Set the parameters for the prepared statement
	      preparedStatement.setString(1, type);
	      preparedStatement.setString(2, name);
	      
	      

	  } catch (Exception e) {
	      e.printStackTrace(); // Handle exceptions appropriately in production code
	  }
     }
     
     
     public boolean isAdmin(String username, String password){
	  String sql = "SELECT * FROM Users WHERE BINARY username = ? AND password = ?";
	  boolean isAdmin = false;

	  try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	       PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	     // Set the parameters
	     preparedStatement.setString(1, username);
	     preparedStatement.setString(2, password);


	     // Execute the query
	     ResultSet resultSet = preparedStatement.executeQuery();
	     
	     if (resultSet.next()){
		  isAdmin = "Admin".equals(resultSet.getString("Type"));
	     }
	 } catch (Exception e) {
	     e.printStackTrace();
	  }
	  
	  return isAdmin;
     }
     
     // Method for creating the tables needed for the system
     public void createTables(){
	  String userTable ="CREATE TABLE Users (UserID INT PRIMARY KEY,Type ENUM('User', 'Admin') NOT NULL,Username VARCHAR(255) NOT NULL,Password VARCHAR(255) NOT NULL,Name VARCHAR(100) NOT NULL,PhoneNo INT NOT NULL,Age INT NOT NULL,Birthdate DATE NOT NULL,Gender ENUM('Male', 'Female', 'Other') NOT NULL,Height DOUBLE NOT NULL,Weight DOUBLE NOT NULL,BMI DOUBLE,Status ENUM('Enabled', 'Disabled') NOT NULL);";
	  
	  if (doesTableExist("Users")){
	  } else {
	       try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		       PreparedStatement preparedStatement = connection.prepareStatement(userTable)) {
		    // Execute the query
		    ResultSet resultSet = preparedStatement.executeQuery();
	       } catch (Exception e) {
		    e.printStackTrace();
	       }
	  }
	       
     }
     
     public static boolean doesTableExist(String tableName) {
	  boolean exists = false;

	  try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	      DatabaseMetaData metaData = (DatabaseMetaData) connection.getMetaData();
	      // Check if the table exists in the current database
	      try (ResultSet resultSet = metaData.getTables(null, null, tableName, null)) {
		  exists = resultSet.next(); // If there is at least one result, the table exists
	      }
	  } catch (SQLException e) {
	      e.printStackTrace();
	  }

	  return exists;
      }
     
     public String getCurrentDate(){
	  // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Define the date format for MySQL
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Format the date and return it as a string
        return currentDate.format(formatter);
     }
     
     public void addAttendance(String username, String password){
	  Integer userID = null;
        
        // SQL query to get UserID based on username and password
        String sql = "SELECT UserID FROM Users WHERE Username = ? AND Password = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            
            // Check if a result was returned
            if (rs.next()) {
                userID = rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
	
	sql = "INSERT INTO Attendance (Date, UserID) VALUES (?, ?)";
	try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the parameters
	    java.sql.Date sqlDate = java.sql.Date.valueOf(getCurrentDate());
            pstmt.setDate(1, sqlDate);
            pstmt.setInt(2, userID);
            
            // Execute the query
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
     }
     
     public static void main(String[] args) {
	  //Controller ctrl = new Controller(user, login);
	  boolean check = doesTableExist("Users");
	  System.out.println(check);
     }
     
     
}

