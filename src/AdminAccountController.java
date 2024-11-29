import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.awt.event.*; // Importing AWT event classes
import java.awt.*; 
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class AdminAccountController {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "root";
     
     private AdminAccount adminAccount;
     
     private CardLayout cardLayout;
     private JPanel mainPanel;
     private JFrame frame;
     
     public AdminAccountController(AdminAccount adminAccount, JPanel mainpPanel, JFrame frame){
	  this.adminAccount = adminAccount;
	  this.mainPanel = mainpPanel;
	  this.frame = frame;
	  this.cardLayout = (CardLayout) mainPanel.getLayout();
	  
	  adminAccount.getUpdateButton().addActionListener(new UpdateBtnListener());
	  adminAccount.getBackButton().addActionListener((new BackBtnListener()));
	  adminAccount.getEnableButton().addActionListener(new EnableBtnListener());
	  adminAccount.getDisableButton().addActionListener(new DisableBtnListener());
	  adminAccount.getAddButton().addActionListener(new AddBtnListener());
	  
	  loadUserData();
     }
     
     class UpdateBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      updateUser();
	  }
     }
     
     class BackBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      cardLayout.show(mainPanel, "AdminPortal");
	  }
     }
     
     class EnableBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      enableUser();
	  }
     }
     
     class DisableBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      disableUser();
	  }
     }
     
     class AddBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      addUser();
	  }
     }
     
     private void loadUserData() {
        String query = "SELECT UserID, Type, Username, Name, PhoneNo, Age, Birthdate, Gender, Height, Weight, BMI, Status FROM Users WHERE Type = 'Admin'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Clear the existing table model
            adminAccount.getTableModel().setRowCount(0);
            adminAccount.getTableModel().setColumnCount(0);

            // Set column names in the table model
            adminAccount.getTableModel().addColumn("User  ID");
            adminAccount.getTableModel().addColumn("Type");
            adminAccount.getTableModel().addColumn("Username");
            adminAccount.getTableModel().addColumn("Name");
            adminAccount.getTableModel().addColumn("PhoneNo");
            adminAccount.getTableModel().addColumn("Age");
            adminAccount.getTableModel().addColumn("Birthdate");
            adminAccount.getTableModel().addColumn("Gender");
            adminAccount.getTableModel().addColumn("Height");
            adminAccount.getTableModel().addColumn("Weight");
            adminAccount.getTableModel().addColumn("BMI");
            adminAccount.getTableModel().addColumn("Status");

            // Populate the table model with data
            while (resultSet.next()) {
                Object[] rowData = new Object[12];
                rowData[0] = resultSet.getInt("UserID");
                rowData[1] = resultSet.getString("Type");
                rowData[2] = resultSet.getString("Username");
                rowData[3] = resultSet.getString("Name");
                rowData[4] = resultSet.getInt("PhoneNo");
                rowData[5] = resultSet.getInt("Age");
                rowData[6] = resultSet.getDate("Birthdate");
                rowData[7] = resultSet.getString("Gender");
                rowData[8] = resultSet.getDouble("Height");
                rowData[9] = resultSet.getDouble("Weight");
                rowData[10] = resultSet.getDouble("BMI");
                rowData[11] = resultSet.getString("Status");
                adminAccount.getTableModel().addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }
     }
     
     private void updateUser () {
        int selectedRow = adminAccount.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to update.");
            return;
        }

        int userId = (int) adminAccount.getTableModel().getValueAt(selectedRow, 0);
        String username = (String) adminAccount.getTableModel().getValueAt(selectedRow, 2);
        String name = (String) adminAccount.getTableModel().getValueAt(selectedRow, 3);
        String phoneNo = String.valueOf(adminAccount.getTableModel().getValueAt(selectedRow, 4));
        int age = (int) adminAccount.getTableModel().getValueAt(selectedRow, 5);
        String birthdate = adminAccount.getTableModel().getValueAt(selectedRow, 6).toString();
        String gender = (String) adminAccount.getTableModel().getValueAt(selectedRow, 7);
        double height = (double) adminAccount.getTableModel().getValueAt(selectedRow, 8);
        double weight = (double) adminAccount.getTableModel().getValueAt(selectedRow, 9);
        double bmi = (double) adminAccount.getTableModel().getValueAt(selectedRow, 10);
        String status = (String) adminAccount.getTableModel().getValueAt(selectedRow, 11);

        // Open a dialog to update user details
        JTextField usernameField = new JTextField(username);
        JTextField nameField = new JTextField(name);
        JTextField phoneNoField = new JTextField(phoneNo);
        JTextField ageField = new JTextField(String.valueOf(age));
        JTextField birthdateField = new JTextField(birthdate);
        JTextField genderField = new JTextField(gender);
        JTextField heightField = new JTextField(String.valueOf(height));
        JTextField weightField = new JTextField(String.valueOf(weight));
        JTextField bmiField = new JTextField(String.valueOf(bmi));
        JTextField statusField = new JTextField(status);

        Object[] message = {
            "Username:", usernameField,
            "Name:", nameField,
            "Phone No:", phoneNoField,
            "Age:", ageField,
            "Birthdate:", birthdateField,
            "Gender:", genderField,
            "Height:", heightField,
            "Weight:", weightField,
            "BMI:", bmiField,
            "Status:", statusField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Update User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String updateQuery = "UPDATE Users SET Username = ?, Name = ?, PhoneNo = ?, Age = ?, Birthdate = ?, Gender = ?, Height = ?, Weight = ?, BMI = ?, Status = ? WHERE UserID = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, usernameField.getText());
                preparedStatement.setString(2, nameField.getText());
                preparedStatement.setString(3, phoneNoField.getText());
                preparedStatement.setInt(4, Integer.parseInt(ageField.getText()));
                preparedStatement.setDate(5, Date.valueOf(birthdateField.getText()));
                preparedStatement.setString(6, genderField.getText());
                preparedStatement.setDouble(7, Double.parseDouble(heightField.getText()));
                preparedStatement.setDouble(8, Double.parseDouble(weightField.getText()));
                preparedStatement.setDouble(9, Double.parseDouble(bmiField.getText()));
                preparedStatement.setString(10, statusField.getText());
                preparedStatement.setInt(11, userId);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "User  updated successfully.");
                loadUserData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating user: " + e.getMessage());
            }
        }
    }
     
     private void enableUser  () {
        int selectedRow = adminAccount.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to enable.");
            return;
        }

        int userId = (int) adminAccount.getTableModel().getValueAt(selectedRow, 0);
        String updateQuery = "UPDATE Users SET Status = 'Enabled' WHERE UserID = ?";
        try (Connection connection = DriverManager .getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "User   enabled successfully.");
            loadUserData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error enabling user: " + e.getMessage());
        }
    }
     
     private void disableUser  () {
        int selectedRow = adminAccount.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to disable.");
            return;
        }

        int userId = (int) adminAccount.getTableModel().getValueAt(selectedRow, 0);
        String updateQuery = "UPDATE Users SET Status = 'Disabled' WHERE UserID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "User  disabled successfully.");
            loadUserData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error disabling user: " + e.getMessage());
        }
    }
     
     public static int generateID(){
	  Random rand = new Random();
	  return 100000 + rand.nextInt(900000);
     }
     
     private void addUser(){
	  JTextField usernameField = new JTextField();
	  JTextField passwordField = new JTextField();
	  JTextField nameField = new JTextField();
	  JTextField phoneNoField = new JTextField();
	  JTextField ageField = new JTextField();
	  JTextField birthdateField = new JTextField();
	  JTextField genderField = new JTextField();
	  JTextField heightField = new JTextField();
	  JTextField weightField = new JTextField();
	  JTextField bmiField = new JTextField();
	  JTextField statusField = new JTextField("Enabled");

	  Object[] message = {
	      "Username:", usernameField,
	       "Password", passwordField,
	      "Name:", nameField,
	      "Phone No:", phoneNoField,
	      "Status:", statusField
	  };

	  int option = JOptionPane.showConfirmDialog(null, message, "Add New User", JOptionPane.OK_CANCEL_OPTION);
	  if (option == JOptionPane.OK_OPTION) {
	      String insertQuery = "INSERT INTO Users (UserId, Type, Username, Password, Name, PhoneNo, Age, Birthdate, Gender, Height, Weight, BMI, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	      try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		   PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

		  // Set parameters for the prepared statement
		  preparedStatement.setInt(1, generateID()); 
		  preparedStatement.setString(2, "Admin"); 
		  preparedStatement.setString(3, usernameField.getText());
		  preparedStatement.setString(4, passwordField.getText()); // You can set a default password or prompt for it
		  preparedStatement.setString(5, nameField.getText());
		  preparedStatement.setString(6, phoneNoField.getText());
		  preparedStatement.setInt(7, 0);
		  preparedStatement.setDate(8, java.sql.Date.valueOf("2000-01-01"));
		  preparedStatement.setString(9, "Other");
		  preparedStatement.setDouble(10, 0);
		  preparedStatement.setDouble(11, 0);
		  preparedStatement.setDouble(12, 0);
		  preparedStatement.setString(13, statusField.getText());

		  // Execute the insert
		  preparedStatement.executeUpdate();
		  loadUserData();
		  JOptionPane.showMessageDialog(null, "User  added successfully.");
	      } catch (SQLException e) {
		  e.printStackTrace();
		  JOptionPane.showMessageDialog(null, "Error adding user: " + e.getMessage());
	      } catch (NumberFormatException e) {
		  JOptionPane.showMessageDialog(null, "Please enter valid numeric values for age, height, weight, and BMI.");
            }
        }
    }
}
