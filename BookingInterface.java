import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class BookingInterface extends JFrame {

    private JTextField bookingIdField;
    private JTextField roomIdField;
    private JTextField dateField;
    private JComboBox<String> timeComboBox;
    private JTextField contactPersonField;
    private JTextField contactEmailField;
    private JTextField contactPhoneField;

    private Map<String, String> bookingDetailsMap; // Map to store booking details
    private int nextBookingId; // Next available booking ID

    public BookingInterface() {
        setTitle("Booking Management");
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        bookingDetailsMap = new HashMap<>(); // Initialize the booking details map
        nextBookingId = 1; // Initialize the next booking ID

        // Panel to hold components
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components
        panel.add(new JLabel("Booking ID:"));
        bookingIdField = new JTextField();
        bookingIdField.setEditable(false); // Automatically generated, so disable editing
        panel.add(bookingIdField);

        panel.add(new JLabel("Room ID:"));
        roomIdField = new JTextField();
        panel.add(roomIdField);

        panel.add(new JLabel("Date (dd/mm/yyyy):"));
        dateField = new JTextField();
        panel.add(dateField);

        panel.add(new JLabel("Time:"));
        timeComboBox = new JComboBox<>(new String[]{"09:00 - 11:00", "11:00 - 13:00", "13:00 - 15:00",
                "15:00 - 17:00", "09:00 - 13:00 (Morning)", "13:00 - 17:00 (Afternoon)", "09:00 - 17:00 (All day)"});
        panel.add(timeComboBox);

        panel.add(new JLabel("Contact Person:"));
        contactPersonField = new JTextField();
        panel.add(contactPersonField);

        panel.add(new JLabel("Contact Email:"));
        contactEmailField = new JTextField();
        panel.add(contactEmailField);

        panel.add(new JLabel("Contact Phone (11 digits):"));
        contactPhoneField = new JTextField();
        panel.add(contactPhoneField);

        JButton addButton = new JButton("Add Booking");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBooking();
            }
        });
        panel.add(addButton);

        JButton viewButton = new JButton("View Booking");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBooking();
            }
        });
        panel.add(viewButton);

        JButton deleteButton = new JButton("Delete Booking");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBooking();
            }
        });
        panel.add(deleteButton);


        // Add panel to frame
        add(panel);

        // Set initial booking ID
        bookingIdField.setText(String.valueOf(nextBookingId));
    }

    private void addBooking() {
        String roomIdText = roomIdField.getText();
        String date = dateField.getText();
        String contactPerson = contactPersonField.getText();
        String contactEmail = contactEmailField.getText();
        String contactPhone = contactPhoneField.getText();

        // Validate inputs
        if (!validateRoomId(roomIdText) || !validateDate(date) || !validateContactPerson(contactPerson) ||
                !validateContactEmail(contactEmail) || !validateContactPhone(contactPhone)) {
            return;
        }

        int roomId = Integer.parseInt(roomIdText);
        String bookingId = String.valueOf(nextBookingId);
        String time = (String) timeComboBox.getSelectedItem();

        // Construct booking details summary
        StringBuilder summary = new StringBuilder();
        summary.append("Booking ID: ").append(bookingId).append("\n");
        summary.append("Room ID: ").append(roomIdText).append("\n");
        summary.append("Date: ").append(date).append("\n");
        summary.append("Time: ").append(time).append("\n");
        summary.append("Contact Person: ").append(contactPerson).append("\n");
        summary.append("Contact Email: ").append(contactEmail).append("\n");
        summary.append("Contact Phone: ").append(contactPhone).append("\n");

        // Show booking confirmation dialog
        int confirmOption = JOptionPane.showConfirmDialog(this, "Please confirm the booking details:\n\n" + summary.toString(),
                "Confirm Booking Details", JOptionPane.YES_NO_OPTION);
        if (confirmOption == JOptionPane.YES_OPTION) {
            // Store booking details in the map
            bookingDetailsMap.put(bookingId, summary.toString());

            // Increment the next booking ID
            nextBookingId++;

            // Show success message
            JOptionPane.showMessageDialog(this, "Booking added successfully. Booking ID: " + bookingId, "Success", JOptionPane.INFORMATION_MESSAGE);

            // Update booking ID field
            bookingIdField.setText(String.valueOf(nextBookingId));

            // Clear input fields
            clearFields();
        }
    }


    // Further validate inputs
    private boolean validateRoomId(String roomIdText) {
        if (roomIdText.isEmpty()) {
            showErrorDialog("Room ID cannot be empty."); // Room ID cannot be empty
            return false;
        }
        try {
            int roomId = Integer.parseInt(roomIdText);
            if (roomId <= 0) {
                showErrorDialog("Room ID must be a positive integer."); // Room ID must be a positive integer
                return false;
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Room ID must be an integer."); // Room ID must be an integer.
            return false;
        }
        return true;
    }

    private boolean validateDate(String date) {
        if (date.isEmpty()) {
            showErrorDialog("Date cannot be empty."); // Date cannot be empty
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(date);
            // Date format is valid
        } catch (ParseException e) {
            showErrorDialog("Date format must be dd/mm/yyyy.");
            return false;
        }
        return true;
    }

    private boolean validateContactPerson(String contactPerson) {
        if (contactPerson.isEmpty()) {
            showErrorDialog("Contact Person cannot be empty."); // Contact Person cannot be empty
            return false;
        }
        if (!contactPerson.matches("[a-zA-Z]+")) {
            showErrorDialog("Please enter a valid Contact Person.");
            // Please enter a valid Contact Person
            return false;
        }
        return true;
    }

    private boolean validateContactEmail(String contactEmail) {
        if (contactEmail.isEmpty()) {
            showErrorDialog("Contact Email cannot be empty."); // Contact Email cannot be empty
            return false;
        }
        if (!isValidEmail(contactEmail)) {
            showErrorDialog("Please enter a valid email address.");
            return false;
        }
        return true;
    }

    private boolean validateContactPhone(String contactPhone) {
        if (contactPhone.isEmpty()) {
            showErrorDialog("Contact Phone cannot be empty."); // Contact Phone cannot be empty
            return false;
        }
        if (!contactPhone.matches("\\d{11}")) {
            showErrorDialog("Contact Phone must be exactly 11 digits long.");
            // Contact Phone must be exactly 11 digits long
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // Must be a valid email entered
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void viewBooking() {
        String bookingId = JOptionPane.showInputDialog(this, "Enter Booking ID:");
        if (bookingId == null) {
            return; // User canceled input dialog
        }
        String bookingDetails = bookingDetailsMap.get(bookingId);
        if (bookingDetails == null) {
            showErrorDialog("Booking with ID '" + bookingId + "' not found.");
        } else {
            JOptionPane.showMessageDialog(this, bookingDetails, "Booking Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteBooking() {
        String bookingId = JOptionPane.showInputDialog(this, "Enter Booking ID:");
        if (bookingId == null) {
            return; // User canceled input dialog
        }
        String bookingDetails = bookingDetailsMap.get(bookingId);
        if (bookingDetails == null) {
            showErrorDialog("Booking with ID '" + bookingId + "' not found.");
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete booking with ID: " + bookingId + "?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                bookingDetailsMap.remove(bookingId);
                JOptionPane.showMessageDialog(this, "Booking deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void clearFields() {
        roomIdField.setText("");
        dateField.setText("");
        contactPersonField.setText("");
        contactEmailField.setText("");
        contactPhoneField.setText("");
    } // Clears fields for a new entry

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BookingInterface().setVisible(true);
            }
        });
    }
}
