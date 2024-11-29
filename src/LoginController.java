import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.awt.event.*; // Importing AWT event classes
import java.awt.*; 
import javax.swing.*;


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
		    if (isAdmin(username, password)){
			 frame.setSize(800, 600);
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
        }
    }
     
     // Login Authentication
     public boolean LoginAuthentication(String username, String password) {
	  String sql = "SELECT * FROM Users WHERE BINARY username = ? AND password = ?";

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
     
     public static void addAdmin(String username, String password){
	  String userID = String.valueOf(generateID());
	  String sql = "INSERT INTO users (userID, type, username, password) VALUES (? ,'administrator' , ?, ?)";

	  try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	       PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	      // Set the parameters for the prepared statement
	      preparedStatement.setString(1, userID);
	      preparedStatement.setString(2, username);
	      preparedStatement.setString(3, password);
	      
	      

	  } catch (Exception e) {
	      e.printStackTrace(); // Handle exceptions appropriately in production code
	  }
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
	     e.printStackTrace(); // Handle exceptions appropriately in production code
	  }
	  
	  return isAdmin;
     }
     
     public static void main(String[] args) {
	  //Controller ctrl = new Controller(user, login);
	  disableFacilityOthers("Trainer", "Gab");
     }
     
     
}

