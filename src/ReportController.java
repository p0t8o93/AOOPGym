import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.*;
import java.awt.*; 
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class ReportController {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "root";
     
     private Report report;
     
     private CardLayout cardLayout;
     private JPanel mainPanel;
     private JFrame frame;
     
     public ReportController(Report report, JPanel mainpPanel, JFrame frame){
	  this.report = report;
	  this.mainPanel = mainpPanel;
	  this.frame = frame;
	  this.cardLayout = (CardLayout) mainPanel.getLayout();
	  
	  report.getAttendanceButton().addActionListener(new AttendanceBtnListener());
	  report.getBackButton().addActionListener((new BackBtnListener()));
	  report.getSubscriptioneButton().addActionListener(new SubscriptionBtnListener());
	  report.getTransactionsButton().addActionListener(new TransactionsBtnListener());
	  report.getEquipFacilityButton().addActionListener(new EquipFacilityBtnListener());
	  
	  loadUserData();
     }
     
     class AttendanceBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      viewAttendance();
	  }
     }
     
     class BackBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      cardLayout.show(mainPanel, "AdminPortal");
	  }
     }
     
     class SubscriptionBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      viewSubscription();
	  }
     }
     
     class TransactionsBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      viewTransactions();
	  }
     }
     
     class EquipFacilityBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	     viewEquipFacility();
	  }
     }
     
     private void loadUserData() {
        String query = "SELECT UserID, Type, Username, Name, PhoneNo, Age, Birthdate, Gender, Height, Weight, BMI, Status FROM Users;";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Clear the existing table model
            report.getTableModel().setRowCount(0);
            report.getTableModel().setColumnCount(0);

            // Set column names in the table model
            report.getTableModel().addColumn("User  ID");
            report.getTableModel().addColumn("Type");
            report.getTableModel().addColumn("Username");
            report.getTableModel().addColumn("Name");
            report.getTableModel().addColumn("PhoneNo");
            report.getTableModel().addColumn("Age");
            report.getTableModel().addColumn("Birthdate");
            report.getTableModel().addColumn("Gender");
            report.getTableModel().addColumn("Height");
            report.getTableModel().addColumn("Weight");
            report.getTableModel().addColumn("BMI");
            report.getTableModel().addColumn("Status");

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
                report.getTableModel().addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }
     }
     
     private void viewAttendance(){
	  JFrame frame = new JFrame("Report");
	  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  frame.setSize(150, 200); // Set the size of the window

	  // Create an instance of the UserManagementPanel
	  ReportPrompt attendance = new ReportPrompt();

	  // Add the panel to the frame
	  frame.add(attendance);

	  // Set the frame to be visible
	  frame.setLocationRelativeTo(null);
	  frame.setVisible(true);
	  
	  int selectedRow = report.getJTable().getSelectedRow();
	  if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to view");
            return;
	  }
	 int UserID = (int) report.getTableModel().getValueAt(selectedRow, 0);
	 
	 String query = "SELECT * FROM Attendance WHERE UserID = " + UserID + ";";
	 try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {

            // Clear the existing table model
            attendance.getTableModel().setRowCount(0);
            attendance.getTableModel().setColumnCount(0);

            // Set column names in the table model
            attendance.getTableModel().addColumn("Date");
            attendance.getTableModel().addColumn("UserID");
            

            // Populate the table model with data
            while (resultSet.next()) {
                Object[] rowData = new Object[12];
                rowData[0] = resultSet.getDate("Date");
                rowData[1] = resultSet.getInt("UserID");
                attendance.getTableModel().addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }
     }
     
     private void viewSubscription(){
	  int selectedRow = report.getJTable().getSelectedRow();
	  if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to view");
            return;
	  }
	  int UserID = (int) report.getTableModel().getValueAt(selectedRow, 0);
	  
	  String query = "SELECT UserID, GymPlan, Locker FROM Subscriptions WHERE UserID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the UserID parameter
            preparedStatement.setInt(1, UserID);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Build the message to show in JOptionPane
            if (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String gymPlan = resultSet.getString("GymPlan");
                String locker = resultSet.getString("Locker");

                String message = String.format("User  ID: %d\nGym Plan: %s\nLocker: %s", userID, gymPlan, locker);
                JOptionPane.showMessageDialog(null, message, "Subscription Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No subscription found for User ID: " + UserID, "No Record", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving subscription data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
     }
     
     private void viewTransactions(){
	  JFrame frame = new JFrame("Report");
	  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  frame.setSize(400, 325); // Set the size of the window

	  // Create an instance of the UserManagementPanel
	  ReportPrompt attendance = new ReportPrompt();

	  // Add the panel to the frame
	  frame.add(attendance);

	  // Set the frame to be visible
	  frame.setLocationRelativeTo(null);
	  frame.setVisible(true);
	  
	  int selectedRow = report.getJTable().getSelectedRow();
	  if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to view");
            return;
	  }
	 int UserID = (int) report.getTableModel().getValueAt(selectedRow, 0);
	 
	 String query = "SELECT * FROM Transactions WHERE UserID = " + UserID + ";";
	 try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {

            // Clear the existing table model
            attendance.getTableModel().setRowCount(0);
            attendance.getTableModel().setColumnCount(0);

            // Set column names in the table model
            attendance.getTableModel().addColumn("TransactionID");
            attendance.getTableModel().addColumn("UserID");
	    attendance.getTableModel().addColumn("Date");
	    attendance.getTableModel().addColumn("Amount");
	    attendance.getTableModel().addColumn("Description");
            

            // Populate the table model with data
            while (resultSet.next()) {
                Object[] rowData = new Object[12];
                rowData[0] = resultSet.getInt("TransactionID");
                rowData[1] = resultSet.getInt("UserID");
		rowData[2] = resultSet.getDate("Date");
		rowData[3] = resultSet.getInt("Amount");
		rowData[4] = resultSet.getString("Description");
                attendance.getTableModel().addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }
     }
     
     private void viewEquipFacility(){
	  JFrame frame = new JFrame("Report");
	  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  frame.setSize(500, 325); // Set the size of the window

	  // Create an instance of the UserManagementPanel
	  ReportPrompt attendance = new ReportPrompt();

	  // Add the panel to the frame
	  frame.add(attendance);

	  // Set the frame to be visible
	  frame.setLocationRelativeTo(null);
	  frame.setVisible(true);
	 
	 String query = "SELECT * FROM FacilitiesAndOthers;";
	 try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {

            // Clear the existing table model
            attendance.getTableModel().setRowCount(0);
            attendance.getTableModel().setColumnCount(0);

            // Set column names in the table model
            attendance.getTableModel().addColumn("Type");
            attendance.getTableModel().addColumn("Name");
	    attendance.getTableModel().addColumn("Status");
	    attendance.getTableModel().addColumn("Enabled");

            // Populate the table model with data
            while (resultSet.next()) {
                Object[] rowData = new Object[12];
                rowData[0] = resultSet.getString("Type");
                rowData[1] = resultSet.getString("Name");
		rowData[2] = resultSet.getString("Status");
		rowData[3] = resultSet.getString("Enabled");
                attendance.getTableModel().addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }
     }
}
