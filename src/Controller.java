import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.awt.event.*; // Importing AWT event classes


public class Controller {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "root";
     
     private User user;
     private Login login;
     private UserForm userform
     
     // Constructor
     public Controller(Login login, UserForm userform){
	  this.user = user;
	  this.login = login;
	  this.userform = userform;
	  
	  // Section for Login View functionality
	  Login.getLoginBtn().addActionListener(new LoginBtnListener());
	  
	  // Userform view functionality
	  userform.getSubmitBtn().addActionListener(new SubmitBtnListener());
	  
     }
     
     // Login.java Login Button functionality
     class LoginBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	       String username = Login.getUsername();
	       String password = Login.getPassword();
	       if (LoginAuthentication(username, password)){
		    System.out.println("Successfully logged in as " + username);
	       } else {
		    System.out.println("Error");
	       }
	  }
     }
     
     class SubmitBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){
	       
	  }
     }
     
     // Login Authentication
     public static boolean LoginAuthentication(String username, String password) {
	  String sql = "SELECT * FROM users WHERE BINARY username = ? AND password = ?";

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
     
     // userID Generator
     public static int generateID(){
	  Random rand = new Random();
	  return 100000 + rand.nextInt(900000);
     }
     
     // Registering
     public static void registerUser(String username, String password){
	  String userID = String.valueOf(generateID());
	  String sql = "INSERT INTO users (userID, type, username, password) VALUES (? ,'user' , ?, ?)";

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
     
     public static void main(String[] args) {
	  //Controller ctrl = new Controller(user, login);
	  disableFacilityOthers("Trainer", "Gab");
     }
     
     
}

