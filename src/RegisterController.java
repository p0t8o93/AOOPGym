import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.awt.event.*; // Importing AWT event classes
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RegisterController {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "root";
     
     private Register register;
     private Register2 register2;
     
     private CardLayout cardLayout;
     private JPanel mainPanel;
     private JFrame frame;
     private String name;
     private int phoneNo;
     private int age;
     private String birthdate;
     private String gender;
     private double height;
     private double weight;
     private double bmi;
     
     public RegisterController(Register register, Register2 register2 ,JPanel mainPanel, JFrame frame){
	  this.register = register;
	  this.register2 = register2;
	  
	  this.mainPanel = mainPanel;
	  this.cardLayout = (CardLayout) mainPanel.getLayout();
	  this.frame = frame;
	  
	  this.register.getNextBtn().addActionListener(new NextBtnListener());
	  this.register2.getRegisterButton().addActionListener(new RegisterBtnListener());
     }
     
     class NextBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){
	       name = register.getName();
	       phoneNo = register.getPhoneNo();
	       age = register.getAge();
	       gender = register.getGender();
	       height = register.getHeightReg();
	       weight = register.getWeight();
	       bmi = register.getBMI();
	       birthdate = register.getBirthdate();
	       System.out.println(birthdate);
	       
	       cardLayout.show(mainPanel, "Register2");
	       frame.setSize(500, 550);
	  }
     }
     
     public static int generateID(){
	  Random rand = new Random();
	  return 100000 + rand.nextInt(900000);
     }
     
     class RegisterBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){
	       String username = register2.getUsername();
	       String password = register2.getPassword();
	       
	       registerUser(username, password, name, phoneNo, age, birthdate, gender, height, weight, bmi);
	       JOptionPane.showMessageDialog(null, "Succesfully Registered", "Message", JOptionPane.INFORMATION_MESSAGE);
	       cardLayout.show(mainPanel, "Login");
	  }
     }
     
     // Registering
     public void registerUser (String username, String password, String name, int phoneNo, int age, String birthdate, String gender, double height, double weight, double bmi) {
    int userID = register2.getUserID();
    String sql = "INSERT INTO Users (UserID, Type, Username, Password, Name, PhoneNo, Age, Birthdate, Gender, Height, Weight, BMI) VALUES (?, 'User ', ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        // Set the parameters for the prepared statement
        preparedStatement.setInt(1, userID); // Assuming userID is an int
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, name);
        preparedStatement.setInt(5, phoneNo);
        preparedStatement.setInt(6, age);
        
        // Convert the birthdate String to java.sql.Date
        java.sql.Date sqlDate = java.sql.Date.valueOf(birthdate);
        preparedStatement.setDate(7, sqlDate);
        
        preparedStatement.setString(8, gender);
        preparedStatement.setDouble(9, height);
        preparedStatement.setDouble(10, weight);
        preparedStatement.setDouble(11, bmi);

        // Execute the insert
        preparedStatement.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
