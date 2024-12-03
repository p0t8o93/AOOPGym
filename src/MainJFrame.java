// MainJFrame.java

import java.awt.*;
import javax.swing.*;

public class MainJFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainJFrame() {
        // Set up the main frame
        setTitle("Gym Membership Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create the panels
        Login loginPanel = new Login();
        Register registerPanel = new Register();
	Register2 registerPanel2 = new Register2();
	AdminPortal adminPanel = new AdminPortal();
	UserAccount useraccPanel = new UserAccount();
	AdminAccount adminaccPanel = new AdminAccount();
	FacilitiesAndOthers facAndOthers = new FacilitiesAndOthers();
	Report report = new Report();

        // Create the controller
        LoginController loginCtrl = new LoginController(loginPanel, registerPanel, mainPanel, this);
	RegisterController registerCtrl = new RegisterController(registerPanel, registerPanel2, mainPanel, this);
	AdminPortalController adminCtrl = new AdminPortalController(adminPanel, mainPanel, this);
	UserAccountController useraccCtrl = new UserAccountController(useraccPanel, mainPanel, this);
	AdminAccountController adminaccCtrl = new AdminAccountController(adminaccPanel, mainPanel, this);
	FacilitiesAndOthersController facAndOthersCtrl = new FacilitiesAndOthersController(facAndOthers, mainPanel, this);
	ReportController reportCtrl = new ReportController(report, mainPanel, this);
        

	
        // Add panels to the main panel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(registerPanel, "Register");
	mainPanel.add(registerPanel2, "Register2");
	mainPanel.add(adminPanel, "AdminPortal");
	mainPanel.add(useraccPanel, "UserAccount");
	mainPanel.add(adminaccPanel, "AdminAccount");
	mainPanel.add(facAndOthers, "FacilitiesAndOthers");
	mainPanel.add(report, "Report");

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainJFrame::new);
    }
}