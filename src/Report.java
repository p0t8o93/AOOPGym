import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Report extends JPanel {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton attendanceButton;
    private JButton subscriptionButton;
    private JButton transactionsButton;
    private JButton backButton;
    private JButton equipfacilityButton;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Report() {
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
        attendanceButton = new JButton("View Attendance");
        subscriptionButton = new JButton("View Subscription");
        transactionsButton = new JButton("View Transactions");
	equipfacilityButton = new JButton("View Equipment/Facility Status");
	backButton = new JButton("Go Back");

	buttonPanel.add(backButton);
        buttonPanel.add(attendanceButton);
        buttonPanel.add(subscriptionButton);
        buttonPanel.add(transactionsButton);
	buttonPanel.add(equipfacilityButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    
    public JButton getAttendanceButton(){
	 return attendanceButton;
    }
    
    public JButton getSubscriptioneButton(){
	 return subscriptionButton;
    }
    
    public JButton getTransactionsButton(){
	 return transactionsButton;
    }
    
    public JButton getBackButton(){
	 return backButton;
    }
    
    public JButton getEquipFacilityButton(){
	 return equipfacilityButton;
    }
    
    public DefaultTableModel getTableModel(){
	 return tableModel;
    }
    
    public JTable getJTable(){
	 return userTable;
    }
    
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("User  Management System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600); // Set the size of the window

        // Create an instance of the UserManagementPanel
        Report attendance = new Report();
        
        // Add the panel to the frame
        frame.add(attendance);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}

class ReportPrompt extends JPanel {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton attendanceButton;
    private JButton subscriptionButton;
    private JButton transactionsButton;
    private JButton backButton;
    private JButton equipfacilityButton;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public ReportPrompt() {
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
        
    }
    
    public DefaultTableModel getTableModel(){
	 return tableModel;
    }
    
    public JTable getJTable(){
	 return userTable;
    }
}