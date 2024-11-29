import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

public class Login extends JPanel {
    private JButton login, register;
    private JLabel titleLabel, loginUser , loginPass, logoLabel;
    private JTextField loginUserText;
    private JPasswordField loginPassText;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        setSize(400, 300); // Rectangular size
        setLayout(null);
        setBackground(new Color(0xF5C400)); // Yellow background

        // Add the logo with adjustable size
        String logoPath = "src/gym.png"; // Update this to the correct path
        File logoFile = new File(logoPath);
        if (!logoFile.exists()) {
            System.out.println("Logo file not found at: " + logoPath);
        } else {
            try {
                ImageIcon logoIcon = new ImageIcon(logoPath);
                BufferedImage logoImage = ImageIO.read(logoFile);

                // Adjust the size of the logo, keeping the aspect ratio intact
                int targetWidth = 70; // Set your target width here
                int targetHeight = (logoImage.getHeight() * targetWidth) / logoImage.getWidth(); // Maintain aspect ratio

                // Scale the image to the target size
                Image scaledImage = logoImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                logoIcon = new ImageIcon(scaledImage);

                logoLabel = new JLabel(logoIcon);

                // Adjust the position of the logo
                int xPosition = 80; // Adjust the X position of the logo
                int yPosition = 15; // Adjust the Y position of the logo
                logoLabel.setBounds(xPosition, yPosition, targetWidth, targetHeight);
                add(logoLabel);

            } catch (Exception e) {
                System.out.println("Error loading logo: " + e.getMessage());
            }
        }

        // Title
        titleLabel = new JLabel("Login");
        titleLabel.setBounds(170, 30, 200, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Larger and bold
        titleLabel.setForeground(new Color(0x102A71)); // Dark blue text
        add(titleLabel);

        // Username label and text field
        loginUser  = new JLabel("Username:");
        loginUser .setBounds(40, 100, 100, 25);
        loginUser .setForeground(new Color(0x001840)); // Navy text
        add(loginUser );

        loginUserText = new JTextField();
        loginUserText.setBounds(140, 100, 210, 25);
        add(loginUserText);

        // Password label and text field
        loginPass = new JLabel("Password:");
        loginPass.setBounds(40, 140, 100, 25);
        loginPass.setForeground(new Color(0x001840)); // Navy text
        add(loginPass);

        loginPassText = new JPasswordField();
        loginPassText.setBounds(140, 140, 210, 25);
        add(loginPassText);

        // Submit button
        login = createRoundedButton("Submit", new Color(0x001840), Color.WHITE);
        login.setBounds(40, 185, 140, 30);
        add(login);

        // Hover button with dynamic text
        register = createRoundedButton("No account?", new Color(0xFFDC5F), new Color(0x001840));
        register.setBounds(200, 185, 150, 30);
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                register.setText("Register");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                register.setText("No account?");
            }
        });
        add(register);
    }

    // Helper method to create rounded buttons
    private JButton createRoundedButton(String text, Color backgroundColor, Color foregroundColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(backgroundColor);
                g2.fillRoundRect(0,  0, getWidth(), getHeight(), 20, 20);
                g2.setColor(foregroundColor);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
                g2.drawString(getText(), x, y);
                g2.dispose();
            }

            @Override
            public void setBorder(Border border) {
                // Prevent setting a border
            }
        };
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Arial", Font.BOLD, 11));
        return button;
    }

    public String getUsername() {
        return loginUserText.getText();
    }

    public String getPassword() {
        return String.valueOf(loginPassText.getPassword());
    }

    public JButton getLoginBtn() {
        return login;
    }

    public JButton getRegisterBtn() {
        return register;
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the Login
        JFrame frame = new JFrame("GymGo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame

        // Create an instance of Login and add it to the frame
        Login loginPanel = new Login();
        frame.add(loginPanel);
        //frame.setVisible(true);
    }
}