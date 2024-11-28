import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JButton loginButton;
    private static JButton cancelButton;

    public Login() {
        // Set the title and default close operation
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window

        // Create the components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // 3 rows, 2 columns
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);

        // Add the panel to the frame
        add(panel);

        // Make the frame visible
        //setVisible(true);
    }
    
    public static String getUsername(){
	 return Login.usernameField.getText();
    }
    
    public static String getPassword(){
	 return String.valueOf(Login.passwordField.getPassword());
    }
    
    public static JButton getLoginBtn(){
	 return Login.loginButton;
    }

    public static void main(String[] args) {
        // Create an instance of the LoginPage
        SwingUtilities.invokeLater(() -> new Login());
    }
}