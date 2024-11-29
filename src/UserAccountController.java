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

public class UserAccountController {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "root";
     
     private UserAccount userAccount;
     
     private CardLayout cardLayout;
     private JPanel mainPanel;
     private JFrame frame;
     
     public UserAccountController(UserAccount usrAccount, JPanel mainpPanel, JFrame frame){
	  this.userAccount = usrAccount;
	  this.mainPanel = mainpPanel;
	  this.frame = frame;
	  this.cardLayout = (CardLayout) mainPanel.getLayout();
	  
	  userAccount.getUpdateButton().addActionListener(new UpdateBtnListener());
	  userAccount.getBackButton().addActionListener((new BackBtnListener()));
	  userAccount.getEnableButton().addActionListener(new EnableBtnListener());
	  userAccount.getDisableButton().addActionListener(new DisableBtnListener());
	  
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
     
     private void loadUserData() {
        String query = "SELECT UserID, Type, Username, Name, PhoneNo, Age, Birthdate, Gender, Height, Weight, BMI, Status FROM Users WHERE Type = 'User'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Clear the existing table model
            userAccount.getTableModel().setRowCount(0);
            userAccount.getTableModel().setColumnCount(0);

            // Set column names in the table model
            userAccount.getTableModel().addColumn("User  ID");
            userAccount.getTableModel().addColumn("Type");
            userAccount.getTableModel().addColumn("Username");
            userAccount.getTableModel().addColumn("Name");
            userAccount.getTableModel().addColumn("PhoneNo");
            userAccount.getTableModel().addColumn("Age");
            userAccount.getTableModel().addColumn("Birthdate");
            userAccount.getTableModel().addColumn("Gender");
            userAccount.getTableModel().addColumn("Height");
            userAccount.getTableModel().addColumn("Weight");
            userAccount.getTableModel().addColumn("BMI");
            userAccount.getTableModel().addColumn("Status");

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
                userAccount.getTableModel().addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }
     }
     
     private void updateUser () {
        int selectedRow = userAccount.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to update.");
            return;
        }

        int userId = (int) userAccount.getTableModel().getValueAt(selectedRow, 0);
        String username = (String) userAccount.getTableModel().getValueAt(selectedRow, 2);
        String name = (String) userAccount.getTableModel().getValueAt(selectedRow, 3);
        String phoneNo = String.valueOf(userAccount.getTableModel().getValueAt(selectedRow, 4));
        int age = (int) userAccount.getTableModel().getValueAt(selectedRow, 5);
        String birthdate = userAccount.getTableModel().getValueAt(selectedRow, 6).toString();
        String gender = (String) userAccount.getTableModel().getValueAt(selectedRow, 7);
        double height = (double) userAccount.getTableModel().getValueAt(selectedRow, 8);
        double weight = (double) userAccount.getTableModel().getValueAt(selectedRow, 9);
        double bmi = (double) userAccount.getTableModel().getValueAt(selectedRow, 10);
        String status = (String) userAccount.getTableModel().getValueAt(selectedRow, 11);

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
        int selectedRow = userAccount.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to enable.");
            return;
        }

        int userId = (int) userAccount.getTableModel().getValueAt(selectedRow, 0);
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
        int selectedRow = userAccount.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to disable.");
            return;
        }

        int userId = (int) userAccount.getTableModel().getValueAt(selectedRow, 0);
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
}
