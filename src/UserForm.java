/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Reuven
 */
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UserForm extends JFrame {

	private JTextField firstNameField, lastNameField, phoneField, ageField, heightField, weightField, bmiField;
	private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;
	private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
	private ButtonGroup genderGroup;
	private JButton submitButton;

	public UserForm() {
    	// Frame setup
    	setTitle("Gym Membership Registration Form");
    	setSize(500, 550);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(new GridBagLayout());
    	getContentPane().setBackground(Color.decode("#001840"));

    	// Initialize components
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.insets = new Insets(10, 10, 10, 10);
    	gbc.fill = GridBagConstraints.HORIZONTAL;

    	// Title
    	JLabel titleLabel = new JLabel("Gym Membership Registration Form", JLabel.CENTER);
    	titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increased font size to 20 and kept it bold
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
    	String[] years = {"2000", "1999", "1998", "1997", "1996", "1995", "1994"};

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
    	submitButton = createRoundedButton("Submit");
    	gbc.gridy = 9;
    	gbc.gridx = 0;
    	gbc.gridwidth = 2;
    	add(submitButton, gbc);

    	

    	// Set the frame visible
    	setVisible(true);
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
	
	public JButton getSubmitBtn(){
	     return this.submitButton;
	}
	
	public String getName(){
	     String name = firstNameField.getText() + " " + lastNameField.getText();
	     return name;
	}
	
	public int getPhoneNo(){
	     return Integer.valueOf(phoneField.getText());
	}
	
	public int getAge(){
	     return Integer.valueOf(ageField.getText());
	}
	
	public getGender(){
	     genderGroup.getSelection().
	}

	public static void main(String[] args) {
    	SwingUtilities.invokeLater(UserForm::new);
	}
}
