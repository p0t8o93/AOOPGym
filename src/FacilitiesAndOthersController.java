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

public class FacilitiesAndOthersController {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "root";
     
     private FacilitiesAndOthers facilitiesAndOthers;
     
     private CardLayout cardLayout;
     private JPanel mainPanel;
     private JFrame frame;
     
     public FacilitiesAndOthersController(FacilitiesAndOthers facilitiesAndOthers, JPanel mainpPanel, JFrame frame){
	  this.facilitiesAndOthers = facilitiesAndOthers;
	  this.mainPanel = mainpPanel;
	  this.frame = frame;
	  this.cardLayout = (CardLayout) mainPanel.getLayout();
	  
	  facilitiesAndOthers.getUpdateButton().addActionListener(new UpdateBtnListener());
	  facilitiesAndOthers.getBackButton().addActionListener((new BackBtnListener()));
	  facilitiesAndOthers.getEnableButton().addActionListener(new EnableBtnListener());
	  facilitiesAndOthers.getDisableButton().addActionListener(new DisableBtnListener());
	  facilitiesAndOthers.getAddButton().addActionListener(new AddBtnListener());
	  
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
        String query = "SELECT ID, Type, Name, Status, Enabled FROM FacilitiesAndOthers";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Clear the existing table model
            facilitiesAndOthers.getTableModel().setRowCount(0);
            facilitiesAndOthers.getTableModel().setColumnCount(0);

            // Set column names in the table model
            facilitiesAndOthers.getTableModel().addColumn("ID");
            facilitiesAndOthers.getTableModel().addColumn("Type");
            facilitiesAndOthers.getTableModel().addColumn("Name");
            facilitiesAndOthers.getTableModel().addColumn("Status");
            facilitiesAndOthers.getTableModel().addColumn("Enabled");
            
            // Populate the table model with data
            while (resultSet.next()) {
                Object[] rowData = new Object[5];
                rowData[0] = resultSet.getInt("ID");
                rowData[1] = resultSet.getString("Type");
                rowData[2] = resultSet.getString("Name");
                rowData[3] = resultSet.getString("Status");
		rowData[4] = resultSet.getString("Enabled");
                facilitiesAndOthers.getTableModel().addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }
     }
     
     private void updateUser () {
        int selectedRow = facilitiesAndOthers.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to update.");
            return;
        }

        int Id = (int) facilitiesAndOthers.getTableModel().getValueAt(selectedRow, 0);
        String Type = (String) facilitiesAndOthers.getTableModel().getValueAt(selectedRow, 1);
        String name = (String) facilitiesAndOthers.getTableModel().getValueAt(selectedRow, 2);
        String Status = String.valueOf(facilitiesAndOthers.getTableModel().getValueAt(selectedRow, 3));
        String Enabled = String.valueOf(facilitiesAndOthers.getTableModel().getValueAt(selectedRow, 4));
        

        // Open a dialog to update user details
        JTextField IdField = new JTextField(String.valueOf(Id));
        JTextField TypeField = new JTextField(Type);
        JTextField nameField = new JTextField(name);
	JTextField StatusField = new JTextField(Status);
        JTextField EnabledField = new JTextField(Enabled);
  
        Object[] message = {
            "ID:", IdField,
            "Type:", TypeField,
            "Name:", nameField,
	    "Status:",StatusField,
            "Enabled:", EnabledField,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Update User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String updateQuery = "UPDATE FacilitiesAndOthers SET ID = ?, Name = ?, Type = ?, Name = ?, Status = ? WHERE UserID = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, Integer.parseInt(IdField.getText()));
                preparedStatement.setString(2, TypeField.getText());
                preparedStatement.setString(3, nameField.getText());
                preparedStatement.setString(4, EnabledField.getText());
		preparedStatement.setInt(5, Integer.parseInt(IdField.getText()));
                
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated successfully.");
                loadUserData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating: " + e.getMessage());
            }
        }
    }
     
     private void enableUser  () {
        int selectedRow = facilitiesAndOthers.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to enable.");
            return;
        }

        int Id = (int) facilitiesAndOthers.getTableModel().getValueAt(selectedRow, 0);
        String updateQuery = "UPDATE FacilitiesAndOthers SET Enabled = 'Enabled' WHERE ID = ?";
        try (Connection connection = DriverManager .getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "User   enabled successfully.");
            loadUserData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error enabling user: " + e.getMessage());
        }
    }
     
     private void disableUser  () {
        int selectedRow = facilitiesAndOthers.getJTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to disable.");
            return;
        }

        int userId = (int) facilitiesAndOthers.getTableModel().getValueAt(selectedRow, 0);
        String updateQuery = "UPDATE FacilitiesAndOthers SET Enabled = 'Disabled' WHERE ID = ?";
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
     
     private void addUser(){
	  JTextField TypeField = new JTextField();
	  JTextField NameField = new JTextField();
	  JTextField StatusField = new JTextField("Operational");
	  JTextField EnabledField = new JTextField("Enabled");

	 Object[] message = {
            "Type:", TypeField,
            "Name:", NameField,
	    "Status:",StatusField,
            "Enabled:", EnabledField,
        };

	  int option = JOptionPane.showConfirmDialog(null, message, "Add New User", JOptionPane.OK_CANCEL_OPTION);
	  if (option == JOptionPane.OK_OPTION) {
	      String insertQuery = "INSERT INTO FacilitiesAndOthers (Type, Name, Status, Enabled) VALUES (?,?,?,?)";
	      try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		   PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

		  // Set parameters for the prepared statement
		  preparedStatement.setString(1, TypeField.getText()); 
		  preparedStatement.setString(2, NameField.getText()); 
		  preparedStatement.setString(3, StatusField.getText());
		  preparedStatement.setString(4, EnabledField.getText()); // You can set a default password or prompt for it
		 
		  // Execute the insert
		  preparedStatement.executeUpdate();
		  loadUserData();
		  JOptionPane.showMessageDialog(null, "Added successfully.");
	      } catch (SQLException e) {
		  e.printStackTrace();
		  JOptionPane.showMessageDialog(null, "Error adding: " + e.getMessage());
	      }
        }
    }
}
