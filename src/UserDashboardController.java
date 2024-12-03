
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;

/**
 *
 * @author Reuven
 */
public class UserDashboardController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    
    private UserDashboard userdashboard;
    private JPanel contentPanel;
    
    private int id;

    public UserDashboardController(UserDashboard userdashboard, int id) {
        this.userdashboard = userdashboard;
        this.contentPanel = userdashboard.getcontentPanel();
        this.id = id;

        userdashboard.getAttendanceButton().addActionListener(new AttendanceBtnListener());
        userdashboard.getTransactionHistoryButton().addActionListener(new TransactionHistoryBtnListener());
        userdashboard.getAvailTrainerButton().addActionListener(new AvailTrainerBtnListener());
        userdashboard.getZumbaButton().addActionListener(new ZumbaBtnListener());
        userdashboard.getLockerButton().addActionListener(new LockerBtnListener());
        userdashboard.getAvailableEquipmentButton().addActionListener(new EquipmentBtnListener());
        userdashboard.getSubscriptionPlanButton().addActionListener(new SubscriptionBtnListener());
        userdashboard.getEditProfileButton().addActionListener(new EditProfileBtnListener());
        userdashboard.getPaymentButton().addActionListener(new PaymentBtnListener());
    }

    class AttendanceBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    class TransactionHistoryBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    class AvailTrainerBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    class ZumbaBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    class LockerBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    class EquipmentBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    class SubscriptionBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    class EditProfileBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    class PaymentBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            showAttendancePanel(contentPanel);
        }
    }

    private void showAttendancePanel(JPanel contentPanel) {
    contentPanel.removeAll();

    // Set background color for the content panel
    contentPanel.setBackground(Color.decode("#FFFDF0"));

    // User information panel
    JPanel userInfoPanel = new JPanel();
    userInfoPanel.setLayout(new GridLayout(2, 3, 10, 10));
    userInfoPanel.setBackground(Color.decode("#102A71"));
    userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Add user information labels with separate font customization for label and value
    String userQuery = "SELECT Name, Age, Gender, Height, Weight, BMI FROM Users WHERE UserID = ?";

    String name = "";
    int age = 0;
    String gender = "";
    double height = 0.0;
    double weight = 0.0;
    double bmi = 0.0;

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(userQuery)) {

        pstmt.setInt(1, id); // Set the UserID parameter
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            name = rs.getString("Name");
            age = rs.getInt("Age");
            gender = rs.getString("Gender");
            height = rs.getDouble("Height");
            weight = rs.getDouble("Weight");
            bmi = rs.getDouble("BMI");
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }

    // Add user information labels with retrieved data
    userInfoPanel.add(createUserInfoPanel("NAME:", name, new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("AGE:", String.valueOf(age), new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("GENDER:", gender, new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("HEIGHT:", height + " cm", new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("WEIGHT:", weight + " kg", new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));
    userInfoPanel.add(createUserInfoPanel("BMI:", String.valueOf(bmi), new Font("Dubai", Font.PLAIN, 17), new Font("Dubai", Font.BOLD, 17)));

    // Query to fetch attendance records, ordered by Date in descending order
    String query = "SELECT Date, time_in, time_out FROM Attendance WHERE UserID = ? ORDER BY Date DESC, time_in DESC;";

    // Prepare data for the table
    DefaultTableModel tableModel = new DefaultTableModel(new String[]{"DATE", "Time-In", "Time-Out"}, 0);

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setInt(1, id); // Set the UserID parameter
        ResultSet rs = pstmt.executeQuery();

        // Populate the table model with data from the ResultSet
        while (rs.next()) {
            Object[] row = {
                rs.getDate("Date"),
                rs.getTime("time_in"),
                rs.getTime("time_out")
            };
            tableModel.addRow(row);
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }

    // Table for attendance
    JTable attendanceTable = new JTable(tableModel) {
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
    centerRenderer.setHorizontalAlignment(SwingConstants .CENTER);

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

    // Set custom column widths
    attendanceTable.getColumnModel().getColumn(0).setPreferredWidth(140);  // "DATE" column width
    attendanceTable.getColumnModel().getColumn(1).setPreferredWidth(100);  // "Time-In" column width
    attendanceTable.getColumnModel().getColumn(2).setPreferredWidth(100);  // "Time-Out" column width

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
    
    public static void main(String[] args) {
	  UserDashboard userdashboard = new UserDashboard();
	  //UserDashboardController userctrl = new UserDashboardController(userdashboard);
     }
}
