import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;



public class UserDashboard extends JFrame {
    private JButton attendanceButton;
    private JButton transactionHistoryButton;
    private JButton availTrainerButton;
    private JButton zumbaButton;
    private JButton lockerButton;
    private JButton availableEquipmentButton;
    private JButton subscriptionPlanButton;
    private JButton editProfileButton;
    private JButton paymentButton;
    private JButton selectedButton;
    private JButton logoutButton;
    private JPanel contentPanel;
    private JPanel menuPanel;

    // Constructor
    public UserDashboard() {
        // Set up the frame
        setTitle("Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the menu panel (left side)
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.decode("#001840"));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(250, getHeight()));

        // Add space above the logo
        menuPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Create a panel to center the logo horizontally
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBackground(Color.decode("#001840"));

        // Add the logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("./GGGG.png"));
        Image scaledLogo = logoIcon.getImage().getScaledInstance(160, 60, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoPanel.add(logoLabel);

        menuPanel.add(logoPanel);

        // Add space below the logo
        menuPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Create a panel for menu items
        JPanel menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new GridLayout(10, 1, 5, 10));
        menuItemsPanel.setBackground(Color.decode("#001840"));

        // Add menu items
        attendanceButton = createMenuButton("Attendance");
        transactionHistoryButton = createMenuButton("Transaction History");
        availTrainerButton = createMenuButton("Avail Trainer");
        zumbaButton = createMenuButton("Zumba Class");
        lockerButton = createMenuButton("Avail Locker");
        availableEquipmentButton = createMenuButton("Available Equipment");
        subscriptionPlanButton = createMenuButton("Subscription Plan");
        editProfileButton = createMenuButton("Edit Profile");
        paymentButton = createMenuButton("Payment");
        logoutButton = createMenuButton("Logout");

        // Add buttons to the menu items panel
        menuItemsPanel.add(attendanceButton);
        menuItemsPanel.add(transactionHistoryButton);
        menuItemsPanel.add(availTrainerButton);
        menuItemsPanel.add(zumbaButton);
        menuItemsPanel.add(lockerButton);
        menuItemsPanel.add(availableEquipmentButton);
        menuItemsPanel.add(subscriptionPlanButton);
        menuItemsPanel.add(editProfileButton);
        menuItemsPanel.add(paymentButton);
        menuItemsPanel.add(logoutButton);

        menuPanel.add(menuItemsPanel);

        // Create the content panel (right side)
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for better centering

        // Create a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the GymGo Dashboard!");
        welcomeLabel.setFont(new Font("Dubai", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Center horizontally
        welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);  // Center vertically

        // Create another label for the additional text with manual line breaks using HTML
        JLabel additionalLabel = new JLabel("<html><div style='text-align: center;'>GymGo: Where Membership Meets Momentum!</div>"
                + "<div style='text-align: center;'><i>We're excited to have you here. Get ready to set new goals, and make every workout count.</i></div></html>");

        // Adjust the font size
        additionalLabel.setFont(new Font("Dubai", Font.PLAIN, 16)); // Adjust font size and remove bold

        // Set the label's alignment to center the entire block of text
        additionalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        additionalLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Set a preferred size for the label to make sure the text wraps nicely
        additionalLabel.setPreferredSize(new Dimension(590, 60));  // Adjust width/height as needed

        // GridBagConstraints for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy =  0;
        gbc.anchor = GridBagConstraints.CENTER; // Ensure everything is centered

        // Add the welcome label to the content panel
        contentPanel.add(welcomeLabel, gbc);

        // Adjust position of the additional label
        gbc.gridy = 1;  // Move it below the welcome message

        // Add it to the content panel
        contentPanel.add(additionalLabel, gbc);

        // Add the menu and content panels to the main frame
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
	
	 setVisible(true);
    }
    
    //BUTTONS (HOVER EFFECT, ICONS)
    private JButton createMenuButton(String item) {
        JButton button = new JButton(item);
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#102A71"));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);

        // Add icon to the button
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("./gym.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != selectedButton) {
                    button.setBackground(Color.decode("#F5C400"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != selectedButton) {
                    button.setBackground(Color.decode("#102A71"));
                }
            }
        });

    //BUTTON NAVIGATION LOGIC
    button.addActionListener(e -> {
    if (selectedButton != null) {
        selectedButton.setBackground(Color.decode("#102A71"));
        resetArrow(selectedButton);
    }

    selectedButton = button;
    button.setBackground(Color.decode("#F5C400"));
    addArrow(button);

    // Update content panel based on selection
    if ("Attendance".equals(item)) {
        //showAttendancePanel();
    } else if ("Avail Trainer".equals(item)) {
        //showAvailTrainerPanel(); // Add this case
    } else if ("Zumba Class".equals(item)) {
        //showZumbaClassPanel(); // Add this case
    } else if ("Avail Locker".equals(item)) {
         //showAvailLockerPanel(); // Add this case for "Avail Locker"
    } else {
        contentPanel.removeAll();
        JLabel label = new JLabel("You selected: " + item);
        label.setFont(new Font("Dubai", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
});
        return button;
    }

    //ATTENDANCE
    public JPanel getcontentPanel(){
	 return contentPanel;
    }
    
    public JButton getSelectedButton(){
	 return selectedButton;
    }
    
    private void showAttendancePanel() {
    contentPanel.removeAll();

    // Set background color for the content panel
    contentPanel.setBackground(Color.decode("#FFFDF0"));

    // User information panel
    JPanel userInfoPanel = new JPanel();
    userInfoPanel.setLayout(new GridLayout(2, 3, 10, 10));
    userInfoPanel.setBackground(Color.decode("#102A71"));
    userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Add user information labels with separate font customization for label and value
    userInfoPanel.add(createUserInfoPanel("NAME:", "John Doe", new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("AGE:", "25", new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("GENDER:", "Male", new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("HEIGHT:", "175 cm", new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("WEIGHT:", "70 kg", new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("BMI:", "22.9", new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));


    // Table for attendance
    String[] columnNames = {"DATE", "Time-In", "Current Subscription", "Time-Out"};
    Object[][] data = {
        {"2024-11-01", "8:00 AM", "Monthly", "10:00 AM"},
        {"2024-11-02", "8:30 AM", "Monthly", "10:30 AM"},
        {"2024-11-03", "9:00 AM", "Monthly", "11:00 AM"}
    };

    JTable attendanceTable = new JTable(new DefaultTableModel(data, columnNames)) {
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component comp = super.prepareRenderer(renderer, row, column);
            if (!isRowSelected(row)) {
                comp.setBackground(row % 2 == 0 ? Color.decode("#FFFDF0") : Color.decode("#FFFDF0"));
                comp.setForeground(Color.decode("#001840"));
            } else {
                comp.setBackground(Color.decode("#F5C400"));
                comp.setForeground(Color.WHITE);
            }
            return comp;
        }
    };

        // Centering the content of the table cells
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    // Apply center alignment to all columns
    for (int i = 0; i < attendanceTable.getColumnCount(); i++) {
        attendanceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    
    // Customize table header
    JTableHeader header = attendanceTable.getTableHeader();
    header.setBackground(Color.decode("#F5C400"));
    header.setForeground(Color.decode("#001840"));
    header.setFont(new Font("Dubai Medium", Font.BOLD, 15));  // Set table header font

    // Customize table cell font
    attendanceTable.setFont(new Font("Dubai", Font.PLAIN, 13));
    
    // Set custom column widths (e.g., setting specific column widths)
    attendanceTable.getColumnModel().getColumn(0).setPreferredWidth(140);  // "DATE" column width
    attendanceTable.getColumnModel().getColumn(1).setPreferredWidth(100);  // "Time-In" column width
    attendanceTable.getColumnModel().getColumn(2).setPreferredWidth(200);  // "Current Subscription" column width
    attendanceTable.getColumnModel().getColumn(3).setPreferredWidth(100);  // "Time-Out" column width

    // Set row height
    attendanceTable.setRowHeight(40);  // Set a fixed height for all rows

    JScrollPane tableScrollPane = new JScrollPane(attendanceTable);

    // Combine user info and table
    contentPanel.setLayout(new BorderLayout());
    contentPanel.add(userInfoPanel, BorderLayout.NORTH);
    contentPanel.add(tableScrollPane, BorderLayout.CENTER);

    contentPanel.revalidate();
    contentPanel.repaint();
}
    //ATTENDANCE'S USER INFORMATION
    private JPanel createUserInfoPanel(String labelText, String valueText, Font labelFont, Font valueFont) {
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel.setOpaque(false);  // Ensure the panel is transparent

    // Create and customize the label
    JLabel label = new JLabel(labelText);
    label.setFont(labelFont);
    label.setForeground(Color.WHITE);  // Set label text color to white
    label.setOpaque(false);  // Make the label's background transparent

    // Create and customize the value label
    JLabel valueLabel = new JLabel(valueText);
    valueLabel.setFont(valueFont);
    valueLabel.setForeground(Color.WHITE);  // Set value text color to white
    valueLabel.setOpaque(false);  // Make the value label's background transparent

    // Add both to the panel
    panel.add(label);
    panel.add(valueLabel);

    return panel;
}

    
    //AVAIL TRAINER
    private void showAvailTrainerPanel() {
    // Clear the content panel
    contentPanel.removeAll();
    contentPanel.setBackground(Color.decode("#FFFDF0")); // Set background color

    // Title inside a rectangle with background color #001840
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(Color.decode("#102A71")); // Set background color for the rectangle
    titlePanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 80)); // Set height of the rectangle
    titlePanel.setLayout(new BorderLayout()); // Use BorderLayout to center the label

    JLabel titleLabel = new JLabel("Available Trainers and Schedules");
    titleLabel.setFont(new Font("Dubai", Font.BOLD, 25));
    titleLabel.setForeground(Color.WHITE); // Set text color to white
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text in the rectangle

    titlePanel.add(titleLabel, BorderLayout.CENTER); // Add title label to the center of the panel

    // Updated trainer list with 3 more trainers
    String[] trainers = {"Trainer A", "Trainer B", "Trainer C", "Trainer D", "Trainer E", "Trainer F"};
    String[][] schedules = {
        {"Monday: 9 AM - 12 PM", "Wednesday: 2 PM - 5 PM"},
        {"Tuesday: 10 AM - 1 PM", "Thursday: 3 PM - 6 PM"},
        {"Friday: 11 AM - 2 PM", "Saturday: 4 PM - 7 PM"},
        {"Monday: 8 AM - 12 PM", "Wednesday: 1 PM - 4 PM"},
        {"Tuesday: 9 AM - 12 PM", "Thursday: 2 PM - 5 PM"},
        {"Friday: 1 PM - 4 PM", "Saturday: 3 PM - 6 PM"}
    };

    // Panel to contain the trainers
    JPanel trainerPanel = new JPanel();
    trainerPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control over component sizing
    trainerPanel.setBackground(Color.decode("#FFFDF0")); // Match background color
    trainerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding inside the panel

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0; // Start at the first column
    gbc.gridy = 0; // Start at the first row
    gbc.insets = new Insets(10, 0, 10, 0); // Add space between trainer items

    ButtonGroup trainerGroup = new ButtonGroup(); // Group for radio buttons
    JRadioButton[] trainerButtons = new JRadioButton[trainers.length];

    for (int i = 0; i < trainers.length; i++) {
        JPanel trainerItemPanel = new JPanel();
        trainerItemPanel.setLayout(new BorderLayout(10, 10)); // Add spacing between components
        trainerItemPanel.setBackground(Color.decode("#FFDC5F")); // Trainer item background color
        trainerItemPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#001840"), 2)); // Border
        trainerItemPanel.setPreferredSize(new Dimension(570, 120)); // Keep box size intact

        // Trainer image
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("./gym.png"));
            Image scaledImage = imageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Scale image
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel.setText("No Image");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Trainer name (radio button)
        JRadioButton trainerButton = new JRadioButton(trainers[i]);
        trainerButton.setFont(new Font("Dubai", Font.BOLD, 17));
        trainerButton.setForeground(Color.decode("#001840"));
        trainerButton.setBackground(Color.decode("#FFDC5F"));
        trainerGroup.add(trainerButton); // Add to button group
        trainerButtons[i] = trainerButton;

        // Trainer schedule (text area)
        JTextArea scheduleArea = new JTextArea(String.join("\n", schedules[i]));
        scheduleArea.setFont(new Font("Dubai", Font.PLAIN, 15));
        scheduleArea.setForeground(Color.decode("#001840"));
        scheduleArea.setBackground(Color.decode("#FFDC5F"));
        scheduleArea.setEditable(false); // Non-editable
        scheduleArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding

        // Combine components
        JPanel trainerInfoPanel = new JPanel(new BorderLayout());
        trainerInfoPanel.setBackground(Color.decode("#FFDC5F")); // Match box background
        trainerInfoPanel.add(trainerButton, BorderLayout.NORTH); // Add trainer name
        trainerInfoPanel.add(scheduleArea, BorderLayout.CENTER); // Add schedule

        trainerItemPanel.add(imageLabel, BorderLayout.EAST); // Add image to the right
        trainerItemPanel.add(trainerInfoPanel, BorderLayout.CENTER); // Add trainer info to the center

        trainerPanel.add(trainerItemPanel, gbc); // Add the trainer box to the main panel

        // Move to the next row
        gbc.gridy++;
    }

    // Save button
    JButton saveButton = new JButton("Save Selection");
    saveButton.setFont(new Font("Dubai", Font.BOLD, 16)); // Adjust font size
    saveButton.setBackground(Color.decode("#F5C400")); // Button background color
    saveButton.setForeground(Color.decode("#001840")); // Button text color
    saveButton.setFocusPainted(false); // Remove focus painting
    saveButton.setBorder(BorderFactory.createLineBorder(Color.decode("#001840"), 2)); // Button border
    saveButton.setPreferredSize(new Dimension(150, 30)); // Set button size

// Action listener for saving selection
saveButton.addActionListener(e -> {
    String selectedTrainer = null;
    for (JRadioButton button : trainerButtons) {
        if (button.isSelected()) {
            selectedTrainer = button.getText(); // Get selected trainer
            break;
        }
    }

    // Show message dialog
    JOptionPane optionPane;
    if (selectedTrainer != null) {
        optionPane = new JOptionPane(
            "You selected " + selectedTrainer + ". \nGo to 'Payment' module to proceed with the payment",
            JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        optionPane = new JOptionPane(
            "Please select a trainer before saving.",
            JOptionPane.WARNING_MESSAGE
        );
    }

    // Get the dialog and the "OK" button
    JDialog dialog = optionPane.createDialog(contentPanel, selectedTrainer != null ? "Selection Saved" : "No Selection");
    JButton okButton = (JButton) dialog.getRootPane().getDefaultButton(); // Get the default OK button

    // Customize the "OK" button
    okButton.setBackground(Color.decode("#FFFDF0")); // Set background color to light cream
    okButton.setBorder(BorderFactory.createEmptyBorder()); // Remove border
    okButton.setPreferredSize(new Dimension(40, 22)); // Increase the button size (width x height)

    // Show the dialog
    dialog.setVisible(true);
});



    // Panel to center and align the button
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align the button
    buttonPanel.setBackground(Color.decode("#FFFDF0")); // Match background color
    buttonPanel.add(saveButton); // Add button to panel

    // Combine title, trainer panel, and button
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout(-1, -1)); // Add spacing
    mainPanel.setBackground(Color.decode("#FFFDF0"));
    mainPanel.add(titlePanel, BorderLayout.NORTH); // Add the title panel at the top
    JScrollPane scrollPane = new JScrollPane(trainerPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scrollbar
    mainPanel.add(scrollPane, BorderLayout.CENTER); // Add trainer list with scroll
    mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel at the bottom

    // Add everything to the content panel
    contentPanel.add(mainPanel, BorderLayout.CENTER);
    contentPanel.revalidate(); // Refresh content panel
    contentPanel.repaint();

    // Scroll to the top
    SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
}

    //ZUMBA CLASS
    private void showZumbaClassPanel() {
    // Clear the content panel
    contentPanel.removeAll();
    contentPanel.setBackground(Color.decode("#FFFDF0")); // Set background color

    // Title label inside a rectangle with background color #001840
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(Color.decode("#102A72")); // Set background color for the rectangle
    titlePanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 80)); // Set height of the rectangle
    titlePanel.setLayout(new BorderLayout()); // Use BorderLayout to center the label

    JLabel titleLabel = new JLabel("Available Zumba Classes and Schedules");
    titleLabel.setFont(new Font("Dubai", Font.BOLD, 25));
    titleLabel.setForeground(Color.WHITE); // Set text color to white
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text in the rectangle

    titlePanel.add(titleLabel, BorderLayout.CENTER); // Add title label to the center of the panel

    // Zumba class list with 3 more classes
    String[] zumbaClasses = {"Zin Momay", "Zin Monay", "Instructor Ula", "Fitness Rey", "Instructor Mey", "Instructor Jay"};
    String[][] schedules = {
        {"Monday: 9 AM - 10 AM", "Wednesday: 5 PM - 6 PM"},
        {"Tuesday: 10 AM - 11 AM", "Thursday: 4 PM - 5 PM"},
        {"Friday: 11 AM - 12 PM", "Saturday: 3 PM - 4 PM"},
        {"Monday: 8 AM - 9 AM", "Wednesday: 6 PM - 7 PM"},
        {"Tuesday: 9 AM - 10 AM", "Thursday: 2 PM - 3 PM"},
        {"Friday: 1 PM - 2 PM", "Saturday: 5 PM - 6 PM"}
    };

    // Panel to contain the Zumba classes
    JPanel zumbaClassPanel = new JPanel();
    zumbaClassPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control over component sizing
    zumbaClassPanel.setBackground(Color.decode("#FFFDF0")); // Match background color
    zumbaClassPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding inside the panel

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0; // Start at the first column
    gbc.gridy = 0; // Start at the first row
    gbc.insets = new Insets(10, 0, 10, 0); // Add space between Zumba items

    ButtonGroup zumbaGroup = new ButtonGroup(); // Group for radio buttons
    JRadioButton[] zumbaButtons = new JRadioButton[zumbaClasses.length];

    for (int i = 0; i < zumbaClasses.length; i++) {
        JPanel zumbaItemPanel = new JPanel();
        zumbaItemPanel.setLayout(new BorderLayout(10, 10)); // Add spacing between components
        zumbaItemPanel.setBackground(Color.decode("#FFDC5F")); // Zumba item background color
        zumbaItemPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#001840"), 2)); // Border
        zumbaItemPanel.setPreferredSize(new Dimension(570, 120)); // Keep box size intact

        // Zumba class image
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("./gym.png"));
            Image scaledImage = imageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Scale image
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel.setText("No Image");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Zumba class name (radio button)
        JRadioButton zumbaButton = new JRadioButton(zumbaClasses[i]);
        zumbaButton.setFont(new Font("Dubai", Font.BOLD, 17));
        zumbaButton.setForeground(Color.decode("#001840"));
        zumbaButton.setBackground(Color.decode("#FFDC5F"));
        zumbaGroup.add(zumbaButton); // Add to button group
        zumbaButtons[i] = zumbaButton;

        // Zumba class schedule (text area)
        JTextArea scheduleArea = new JTextArea(String.join("\n", schedules[i]));
        scheduleArea.setFont(new Font("Dubai", Font.PLAIN, 15));
        scheduleArea.setForeground(Color.decode("#001840"));
        scheduleArea.setBackground(Color.decode("#FFDC5F"));
        scheduleArea.setEditable(false); // Non-editable
        scheduleArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding

        // Combine components
        JPanel zumbaInfoPanel = new JPanel(new BorderLayout());
        zumbaInfoPanel.setBackground(Color.decode("#FFDC5F")); // Match box background
        zumbaInfoPanel.add(zumbaButton, BorderLayout.NORTH); // Add Zumba class name
        zumbaInfoPanel.add(scheduleArea, BorderLayout.CENTER); // Add schedule

        zumbaItemPanel.add(imageLabel, BorderLayout.EAST); // Add image to the right
        zumbaItemPanel.add(zumbaInfoPanel, BorderLayout.CENTER); // Add Zumba info to the center

        zumbaClassPanel.add(zumbaItemPanel, gbc); // Add the Zumba class box to the main panel

        // Move to the next row
        gbc.gridy++;
    }

    // Save button
    JButton saveButton = new JButton("Save Selection");
    saveButton.setFont(new Font("Dubai", Font.BOLD, 16)); // Adjust font size
    saveButton.setBackground(Color.decode("#F5C400")); // Button background color
    saveButton.setForeground(Color.decode("#001840")); // Button text color
    saveButton.setFocusPainted(false); // Remove focus painting
    saveButton.setBorder(BorderFactory.createLineBorder(Color.decode("#001840"), 2)); // Button border
    saveButton.setPreferredSize(new Dimension(150, 30)); // Set button size

    
// Action listener for saving selection
saveButton.addActionListener(e -> {
    String selectedTrainer = null;
    for (JRadioButton button : zumbaButtons) {
        if (button.isSelected()) {
            selectedTrainer = button.getText(); // Get selected trainer
            break;
        }
    }

    // Show message dialog
    JOptionPane optionPane;
    if (selectedTrainer != null) {
        optionPane = new JOptionPane(
            "You selected " + selectedTrainer + ". \nGo to 'Payment' module to proceed with the payment",
            JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        optionPane = new JOptionPane(
            "Please select a trainer before saving.",
            JOptionPane.WARNING_MESSAGE
        );
    }

    // Get the dialog and the "OK" button
    JDialog dialog = optionPane.createDialog(contentPanel, selectedTrainer != null ? "Selection Saved" : "No Selection");
    JButton okButton = (JButton) dialog.getRootPane().getDefaultButton(); // Get the default OK button

    // Customize the "OK" button
    okButton.setBackground(Color.decode("#FFFDF0")); // Set background color to light cream
    okButton.setBorder(BorderFactory.createEmptyBorder()); // Remove border
    okButton.setPreferredSize(new Dimension(40, 22)); // Increase the button size (width x height)

    // Show the dialog
    dialog.setVisible(true);
});

    // Panel to center and align the button
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align the button
    buttonPanel.setBackground(Color.decode("#FFFDF0")); // Match background color
    buttonPanel.add(saveButton); // Add button to panel

    // Combine title, Zumba class panel, and button
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout(-1, -1)); // Add spacing
    mainPanel.setBackground(Color.decode("#FFFDF0"));
    mainPanel.add(titlePanel, BorderLayout.NORTH); // Add the title panel at the top
    JScrollPane scrollPane = new JScrollPane(zumbaClassPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scrollbar
    mainPanel.add(scrollPane, BorderLayout.CENTER); // Add Zumba class list with scroll
    mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel at the bottom

    // Add everything to the content panel
    contentPanel.add(mainPanel, BorderLayout.CENTER);
    contentPanel.revalidate(); // Refresh content panel
    contentPanel.repaint();

    // Scroll to the top
    SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
}

    //AVAIL LOCKER
    private void showAvailLockerPanel() {
    contentPanel.removeAll();
    contentPanel.setBackground(Color.decode("#FFFDF0"));

    // Title inside a rectangle with background color #001840
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(Color.decode("#102A71"));
    titlePanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 80));
    titlePanel.setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("Available Lockers");
    titleLabel.setFont(new Font("Dubai", Font.BOLD, 25));
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

    titlePanel.add(titleLabel, BorderLayout.CENTER);

    // Locker grid panel
    JPanel lockerGridPanel = new JPanel();
    lockerGridPanel.setLayout(new GridLayout(5, 5, 10, 10));  // 5x5 grid of lockers
    lockerGridPanel.setBackground(Color.decode("#FFFDF0"));

    // Locker buttons
    JButton[] lockerButtons = new JButton[25];
    for (int i = 0; i < 25; i++) {
        JButton lockerButton = new JButton("Locker " + (i + 1));
        lockerButton.setBackground(Color.decode("#FFDC5F"));
        lockerButton.setFont(new Font("Dubai", Font.PLAIN, 14));
        lockerButton.setForeground(Color.decode("#001840"));
        lockerButton.setFocusPainted(false);
        lockerButton.setBorder(BorderFactory.createLineBorder(Color.decode("#001840"), 2));

        // Add action listener to locker button
        int lockerIndex = i;
        lockerButton.addActionListener(e -> {
            // Reset all locker button backgrounds
            for (JButton btn : lockerButtons) {
                btn.setBackground(Color.decode("#FFDC5F"));
            }
            lockerButton.setBackground(Color.decode("#F5C400"));  // Highlight selected locker
        });

        lockerButtons[i] = lockerButton;
        lockerGridPanel.add(lockerButton);
    }

    // Save button (initially disabled)
    JButton saveButton = new JButton("Save");
    saveButton.setFont(new Font("Dubai", Font.BOLD, 16));
    saveButton.setBackground(Color.decode("#F5C400"));
    saveButton.setForeground(Color.decode("#001840"));
    saveButton.setFocusPainted(false);
    saveButton.setBorder(BorderFactory.createLineBorder(Color.decode("#001840"), 2));

    // Customize the size of the save button
    int buttonWidth = 150;  // Customize width
    int buttonHeight = 40;  // Customize height
    saveButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

    // Action listener for Save button
    saveButton.addActionListener(e -> {
        boolean lockerSelected = false;
        for (JButton btn : lockerButtons) {
            if (btn.getBackground().equals(Color.decode("#F5C400"))) {
                lockerSelected = true;
                break;
            }
        }
        
        JOptionPane optionPane;
        if (!lockerSelected) {
            optionPane = new JOptionPane(
                "Please select a locker before proceeding.",
                JOptionPane.WARNING_MESSAGE
            );
        } else {
            optionPane = new JOptionPane(
                "Locker reserved. Proceed to Payment.",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        // Create the dialog
        JDialog dialog = optionPane.createDialog(contentPanel, lockerSelected ? "Locker Reserved" : "Locker Not Selected");

        // Get the default OK button and customize it
        JButton okButton = (JButton) dialog.getRootPane().getDefaultButton(); // Get the default OK button

        // Customize the "OK" button
        okButton.setBackground(Color.decode("#FFFDF0")); // Light cream background
        okButton.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        okButton.setPreferredSize(new Dimension(40, 22)); // Customize size (width x height)

        // Show the dialog
        dialog.setVisible(true);
    });

    // Reset button
    JButton resetButton = new JButton("Reset");
    resetButton.setFont(new Font("Dubai", Font.BOLD, 16));
    resetButton.setBackground(Color.decode("#FFDC5F"));
    resetButton.setForeground(Color.decode("#001840"));
    resetButton.setFocusPainted(false);
    resetButton.setBorder(BorderFactory.createLineBorder(Color.decode("#001840"), 2));

    // Customize the size of the reset button
    resetButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

    // Action listener for Reset button
    resetButton.addActionListener(e -> {
        // Reset all locker button backgrounds
        for (JButton btn : lockerButtons) {
            btn.setBackground(Color.decode("#FFDC5F"));
        }
    });

    // Panel to center and align the buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBackground(Color.decode("#FFFDF0"));
    buttonPanel.add(saveButton);
    buttonPanel.add(resetButton);  // Add the reset button

    // Combine title, locker grid, and button
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(Color.decode("#FFFDF0"));
    mainPanel.add(titlePanel, BorderLayout.NORTH);
    JScrollPane scrollPane = new JScrollPane(lockerGridPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    mainPanel.add(scrollPane, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    contentPanel.add(mainPanel, BorderLayout.CENTER);
    contentPanel.revalidate();
    contentPanel.repaint();
}

    
    //method ui for the left panel
    //arrow image when clicked
    private void addArrow(JButton button) {
        button.setLayout(new BorderLayout());
        ImageIcon arrowIcon = new ImageIcon(getClass().getResource("./arrow.png"));
        Image scaledArrow = arrowIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        JLabel arrowLabel = new JLabel(new ImageIcon(scaledArrow));
        button.add(arrowLabel, BorderLayout.EAST);
    }
    //arrow reset when not clicked
    private void resetArrow(JButton button) {
        button.setLayout(null);
        button.removeAll();
        button.revalidate();
        button.repaint();
    }
    
    
    public JButton getAttendanceButton() {
    return attendanceButton;
     }

     public JButton getTransactionHistoryButton() {
	 return transactionHistoryButton;
     }

     public JButton getAvailTrainerButton() {
	 return availTrainerButton;
     }

     public JButton getZumbaButton() {
	 return zumbaButton;
     }

     public JButton getLockerButton() {
	 return lockerButton;
     }

     public JButton getAvailableEquipmentButton() {
	 return availableEquipmentButton;
     }

     public JButton getSubscriptionPlanButton() {
	 return subscriptionPlanButton;
     }

     public JButton getEditProfileButton() {
	 return editProfileButton;
     }

     public JButton getPaymentButton() {
	 return paymentButton;
     }
    
    //main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDashboard frame = new UserDashboard();
            frame.setVisible(true);
        });
    }
}