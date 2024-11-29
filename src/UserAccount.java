import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserAccount extends JPanel {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton updateButton;
    private JButton disableButton;
    private JButton enableButton;
    private JButton backButton;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public UserAccount() {
        setLayout(new BorderLayout());
        initializeComponents();
        //sloadUserData();
    }

    private void initializeComponents() {
        // Table model and JTable
        tableModel = new DefaultTableModel();
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Update User");
        disableButton = new JButton("Disable User");
        enableButton = new JButton("Enable User");
	backButton = new JButton("Go Back");

	buttonPanel.add(backButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(disableButton);
        buttonPanel.add(enableButton);
        add(buttonPanel, BorderLayout.SOUTH);

        

        
    }

    
    public JButton getUpdateButton(){
	 return updateButton;
    }
    
    public JButton getDisableButton(){
	 return disableButton;
    }
    
    public JButton getEnableButton(){
	 return enableButton;
    }
    
    public DefaultTableModel getTableModel(){
	 return tableModel;
    }
    
    public JButton getBackButton(){
	 return backButton;
    }
    
    public JTable getJTable(){
	 return userTable;
    }
    
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("User  Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the size of the window

        // Create an instance of the UserManagementPanel
        UserAccount userManagementPanel = new UserAccount();
        
        // Add the panel to the frame
        frame.add(userManagementPanel);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}