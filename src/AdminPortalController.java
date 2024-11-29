import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.awt.event.*; // Importing AWT event classes
import java.awt.*; 
import javax.swing.*;

public class AdminPortalController {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "root";
     
     private CardLayout cardLayout;
     private JPanel mainPanel;
     private JFrame frame;
     
     AdminPortal adminPortal;
     
     public AdminPortalController(AdminPortal adminPortal, JPanel mainPanel, JFrame frame){
	  this.adminPortal = adminPortal;
	  this.mainPanel = mainPanel;
	  this.frame = frame;
	  this.cardLayout = (CardLayout) mainPanel.getLayout();
	  
	  this.adminPortal.getUserAccountButton().addActionListener(new UserAccountBtnListener());
	  this.adminPortal.getAdminAccountButton().addActionListener(new AdminAccountBtnListener());
     }
     
     class UserAccountBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
	      cardLayout.show(mainPanel, "UserAccount");
	  }
     }
     
     class AdminAccountBtnListener implements ActionListener {
	  public void actionPerformed(ActionEvent e){
	       cardLayout.show(mainPanel, "AdminAccount");
	  }
     }
}
