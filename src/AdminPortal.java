import javax.swing.*;
import java.awt.*;

public class AdminPortal extends JPanel {
     
     private JButton facilitiesButton;
     private JButton userAccountButton;
     private JButton adminAccountButton;
     private JButton reportButton;

    public AdminPortal() {
        // Set layout for the main panel
        setLayout(new BorderLayout());

        // Primary color palette
        Color primaryColor = Color.decode("#001840");
        Color buttonColor = Color.decode("#f5c400");
        Color accentColor = Color.decode("#102A71");

        // Top panel with rounded rectangle container
        JPanel topPanel = new JPanel();
        topPanel.setBackground(primaryColor); // Background color for spacing
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Center-align the rounded rectangle container

        // Rounded rectangle container for logo and welcome label
        JPanel roundedContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw rounded rectangle
                g2d.setColor(Color.decode("#102A71")); // Color for the rectangle
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded edges (30 radius)
            }
        };
        roundedContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); // Align contents inside
        roundedContainer.setOpaque(false); // Make the panel background transparent
        roundedContainer.setPreferredSize(new Dimension(700, 120)); // Set size of the container

        // Logo label
        JLabel logoLabel = new JLabel();
        try {
            // Load the logo image
            ImageIcon logoIcon = new ImageIcon(AdminPortal.class.getResource("./GGGG.png"));
            Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 64, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledLogo));
        } catch (Exception e) {
            System.err.println("Logo not found or failed to load: ./GGGG.png");
            e.printStackTrace();
        }

        // Welcome label
        JLabel welcomeLabel = new JLabel("                         Admin Portal");
        welcomeLabel.setForeground(Color.decode("#f5c400")); // Set text color to #f5c400
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Add logo and welcome label to the rounded container
        roundedContainer.add(logoLabel);
        roundedContainer.add(welcomeLabel);

        // Add the rounded container to the top panel
        topPanel.add(roundedContainer);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBackground(primaryColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Buttons with icons
        facilitiesButton = createButtonWithIcon("Facilities and Others", "./gym.png", buttonColor, accentColor);
        userAccountButton = createButtonWithIcon("User  Account", "./gym.png", buttonColor, accentColor);
        adminAccountButton = createButtonWithIcon("Admin Account", "./gym.png", buttonColor, accentColor);
        reportButton = createButtonWithIcon("Report", "./gym.png", buttonColor, accentColor);

        buttonPanel.add(facilitiesButton);
        buttonPanel.add(userAccountButton);
        buttonPanel.add(adminAccountButton);
        buttonPanel.add(reportButton);

        // Add panels to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButtonWithIcon(String text, String iconPath, Color background, Color foreground) {
        JButton button = new JButton(text);

        try {
            // Load image using ClassLoader
            ImageIcon icon = new ImageIcon(AdminPortal.class.getResource(iconPath));
            Image scaledIcon = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledIcon));
        } catch (Exception e) {
            System.err.println("Icon not found or failed to load: " + iconPath);
            e.printStackTrace();
        }

        // Set button properties
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(background));

        // Align text and icon
        button.setHorizontalTextPosition (SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        return button;
    }
    
    public JButton getUserAccountButton(){
	 return userAccountButton;
    }
    
    public JButton getAdminAccountButton(){
	 return adminAccountButton;
    }
    
    public JButton getFacAndOthersButton(){
	 return facilitiesButton;
    }
    
    public JButton getReportButton(){
	 return reportButton;
    }

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Admin Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Add the AdminPortalPanel to the frame
        AdminPortal adminPortalPanel = new AdminPortal();
        frame.add(adminPortalPanel);
        
        frame.setVisible(true);
    }
}