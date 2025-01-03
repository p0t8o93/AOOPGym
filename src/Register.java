import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Random;

public class Register extends JPanel {

    private JTextField firstNameField, lastNameField, phoneField, ageField, heightField, weightField, bmiField;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private ButtonGroup genderGroup;
    private JButton nextButton;

    public Register() {
        // Panel setup
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#001840"));

        // Initialize components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Gym Membership Registration Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.decode("#F5C400"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Customer Name
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(createLabel("Full Name*:"), gbc);

        JPanel namePanel = new JPanel(new GridLayout(1, 2, 5, 5));
        namePanel.setBackground(Color.decode("#001840"));
        firstNameField = createRoundedTextField("Enter First Name");
        lastNameField = createRoundedTextField("Enter Last Name");
        namePanel.add(firstNameField);
        namePanel.add(lastNameField);
        gbc.gridx = 1;
        add(namePanel, gbc);

        // Customer Phone Number
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(createLabel("Phone Number*:"), gbc);

        phoneField = createRoundedTextField("Enter Phone Number");
        gbc.gridx = 1;
        add(phoneField, gbc);

        // Age
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(createLabel("Age*"), gbc);

        ageField = createRoundedTextField("Enter Age");
        gbc.gridx = 1;
        add(ageField, gbc);

        // Birthdate
        gbc.gridy = 4;
        gbc.gridx = 0;
        add(createLabel("Birthdate*:"), gbc);

        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] years = new String[31];
	for(int i = 0; i < years.length; i++){
	     years[i] = String.valueOf(2024 - i);
	}

        dayComboBox = new JComboBox<>(days);
        monthComboBox = new JComboBox<>(months);
        yearComboBox = new JComboBox<>(years);

        JPanel birthdatePanel = new JPanel(new GridLayout(1, 3, 5, 5));
        birthdatePanel.setBackground(Color.decode("#001840"));
        birthdatePanel.add(dayComboBox);
        birthdatePanel.add(monthComboBox);
        birthdatePanel.add(yearComboBox);
        gbc.gridx = 1;
        add(birthdatePanel, gbc);

        // Gender
        gbc.gridy = 5;
        gbc.gridx = 0;
        add(createLabel("Gender*"), gbc);

        JPanel genderPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        genderPanel.setBackground(Color.decode("#001840"));
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setActionCommand("Male");

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setActionCommand("Female");

        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setActionCommand("Other");

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);
        gbc.gridx = 1;
        add(genderPanel, gbc);

        // Height
        gbc.gridy = 6;
        gbc.gridx = 0;
        add(createLabel("Height (cm)*:"), gbc);

        heightField = createRoundedTextField("Enter Height");
        gbc.gridx = 1;
        add(heightField, gbc);

        // Weight
        gbc.gridy = 7;
        gbc.gridx = 0;
        add(createLabel("Weight (kg)*:"), gbc);

        weightField = createRoundedTextField("Enter Weight");
        gbc.gridx = 1;
        add(weightField, gbc);

        // BMI
        gbc.gridy = 8;
        gbc.gridx = 0;
        add(createLabel("BMI:"), gbc);

        bmiField = createRoundedTextField("BMI will be calculated");
        bmiField.setEditable(false);
        gbc.gridx = 1;
        add(bmiField, gbc);

        // Add listeners for BMI calculation
        heightField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                calculateBMI();
            }

            public void removeUpdate(DocumentEvent e) {
                calculateBMI();
            }

            public void changedUpdate(DocumentEvent e) {
                calculateBMI();
            }
        });

        weightField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                calculateBMI();
            }

            public void removeUpdate(DocumentEvent e) {
                calculateBMI();
            }

            public void changedUpdate(DocumentEvent e) {
                calculateBMI();
            }
        });

        // Submit Button
        nextButton = createRoundedButton("Next");
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(nextButton, gbc);
    }

    private void calculateBMI() {
        try {
            double height = Double.parseDouble(heightField.getText()) / 100; // Convert cm to meters
            double weight = Double.parseDouble(weightField.getText());
            double bmi = weight / (height * height); // BMI formula
            bmiField.setText(String.format("%.2f", bmi));
        } catch (NumberFormatException e) {
            bmiField.setText("");
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#F5C400"));
        return label;
    }

    private JTextField createRoundedTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.decode("#001840"));
        button.setBackground(Color.decode("#F5C400"));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setFocusPainted(false);
        return button;
    }

    public JButton getNextBtn() {
        return this.nextButton;
    }

    public String getName() {
        return firstNameField.getText() + " " + lastNameField.getText();
    }

    public int getPhoneNo() {
        return Integer.parseInt(phoneField.getText());
    }

    public int getAge() {
        return Integer.parseInt(ageField.getText());
    }

    public String getBirthdate() {
        String day = String.format("%02d", dayComboBox.getSelectedIndex() + 1);
        String month = String.format("%02d", monthComboBox.getSelectedIndex() + 1);
        String year = (String) yearComboBox.getSelectedItem();
        return year + "-" + month + "-" + day;
    }

    public String getGender() {
        return genderGroup.getSelection().getActionCommand();
    }

    public double getHeightReg() {
        return Double.parseDouble(heightField.getText());
    }

    public double getWeight() {
        return Double.parseDouble(weightField.getText());
    }

    public double getBMI() {
        return Double.parseDouble (bmiField.getText());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gym Membership Registration");
        Register registerPanel = new Register();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 550);
        frame.add(registerPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class Register2 extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private int UserID;

    public Register2() {
        // Set up the panel
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#001840"));

        // Initialize components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Label and Field
	UserID = generateID();
	JLabel userIDLabel = new JLabel("UserID: " + String.valueOf(UserID));
        userIDLabel.setForeground(Color.decode("#F5C400"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userIDLabel, gbc);
	
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.decode("#F5C400"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.decode("#F5C400"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Submit Button
        submitButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        add(submitButton, gbc);

        
    }
    public static int generateID(){
	  Random rand = new Random();
	  return 100000 + rand.nextInt(900000);
     }
    
    public String getUsername(){
	 return usernameField.getText();
    }
    
    public String getPassword(){
	 return String.valueOf(passwordField.getPassword());
    }
    
    public JButton getRegisterButton(){
	 return submitButton;
    }
    
    public int getUserID(){
	 return UserID;
    }
}