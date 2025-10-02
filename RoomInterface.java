import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class RoomInterface extends JFrame {

    private JTextField roomIdField;
    private JTextField roomNumberField;
    private JTextField floorField;
    private JTextField capacityField;
    private JComboBox<String> roomTypeComboBox;

    private JLabel additionalLabel1;
    private JTextField additionalField1;

    private JLabel additionalLabel2;
    private JTextField additionalField2;

    private Map<String, String> roomDetailsMap; // Map to store room details

    public RoomInterface() {
        setTitle("Room Management");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Dispose only the current window

        roomDetailsMap = new HashMap<>(); // Initialize the room details map

        // Panel to hold components
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components
        panel.add(new JLabel("Room ID:"));
        roomIdField = new JTextField();
        panel.add(roomIdField);

        panel.add(new JLabel("Room Number:"));
        roomNumberField = new JTextField();
        panel.add(roomNumberField);

        panel.add(new JLabel("Floor:"));
        floorField = new JTextField();
        panel.add(floorField);

        panel.add(new JLabel("Capacity:"));
        capacityField = new JTextField();
        panel.add(capacityField);

        panel.add(new JLabel("Room Type:"));
        roomTypeComboBox = new JComboBox<>(new String[]{"Lab Room", "Lecture Hall"});
        panel.add(roomTypeComboBox);

        additionalLabel1 = new JLabel("No. of PCs:");
        additionalField1 = new JTextField();
        panel.add(additionalLabel1);
        panel.add(additionalField1);

        additionalLabel2 = new JLabel("Operating System:");
        additionalField2 = new JTextField();
        panel.add(additionalLabel2);
        panel.add(additionalField2);

        // Hide additional fields initially
        additionalLabel1.setVisible(false);
        additionalField1.setVisible(false);
        additionalLabel2.setVisible(false);
        additionalField2.setVisible(false);

        // Listen for changes in room type selection
        roomTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAdditionalFields();
            }
        });

        JButton addButton = new JButton("Add Room");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoom();
            }
        });
        panel.add(addButton);

        // View Room button
        JButton viewButton = new JButton("View Room");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRoom();
            }
        });
        panel.add(viewButton);

        // Add panel to frame
        add(panel);
    }

    private void updateAdditionalFields() {
        // Show additional fields based on selected room type
        String selectedType = (String) roomTypeComboBox.getSelectedItem();
        if (selectedType.equals("Lab Room")) {
            additionalLabel1.setText("No. of PCs:");
            additionalLabel1.setVisible(true);
            additionalField1.setVisible(true);

            additionalLabel2.setText("Operating System:");
            additionalLabel2.setVisible(true);
            additionalField2.setVisible(true);
        } else if (selectedType.equals("Lecture Hall")) {
            additionalLabel1.setText("Microphone Type:");
            additionalLabel1.setVisible(true);
            additionalField1.setVisible(true);

            additionalLabel2.setText("Tiered Seating (Y/N):");
            additionalLabel2.setVisible(true);
            additionalField2.setVisible(true);
        } else {
            // Hide additional fields for other room types
            additionalLabel1.setVisible(false);
            additionalField1.setVisible(false);
            additionalLabel2.setVisible(false);
            additionalField2.setVisible(false);
        }
    }

    private boolean isPositiveInteger(String str) {
        try {
            int value = Integer.parseInt(str);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidOperatingSystem(String os) {
        // Check if the string contains only letters and numbers
        return os.matches("[a-zA-Z0-9]+");
    }

    private boolean isValidMicrophoneType(String microphoneType) {
        // Check if the string contains only letters and numbers
        return microphoneType.matches("[a-zA-Z0-9]+");
    }

    private void addRoom() {
        // Get user inputs
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        String roomIdStr = roomIdField.getText();
        String roomNumberStr = roomNumberField.getText();
        String floor = floorField.getText();
        String capacityStr = capacityField.getText();
        String additionalInfo1 = additionalField1.getText();
        String additionalInfo2 = additionalField2.getText();

        // Validate inputs
        boolean isValid = true;
        if (roomIdStr.isEmpty() || !isPositiveInteger(roomIdStr)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Room ID.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return; // Stop execution if Room ID is invalid
        }

        if (roomNumberStr.isEmpty() || !isPositiveInteger(roomNumberStr)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Room Number.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return; // Stop execution if Room Number is invalid
        }

        if (floor.isEmpty() || !isPositiveInteger(floor)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Floor.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return; // Stop execution if Floor is empty or not a positive integer
        }

        if (capacityStr.isEmpty() || !isPositiveInteger(capacityStr)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Capacity.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return; // Stop execution if Capacity is invalid
        }

        // Validate additional fields for Lab Room
        if (roomType.equals("Lab Room")) {
            if (additionalInfo1.isEmpty() || !isPositiveInteger(additionalInfo1)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for No. of PCs.", "Error", JOptionPane.ERROR_MESSAGE);
                isValid = false;
                return; // Stop execution if No. of PCs is invalid
            }
            if (additionalInfo2.isEmpty() || !isValidOperatingSystem(additionalInfo2)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Operating System.", "Error", JOptionPane.ERROR_MESSAGE);
                isValid = false;
                return; // Stop execution if Operating System is invalid
            }
        }


        // Validate additional fields for Lecture Hall
        if (roomType.equals("Lecture Hall")) {
            if (additionalInfo1.isEmpty() || !isValidMicrophoneType(additionalInfo1)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Microphone Type.", "Error", JOptionPane.ERROR_MESSAGE);
                isValid = false;
                return; // Stop execution if Microphone Type is invalid
            }
            if (additionalInfo2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Tiered Seating information.", "Error", JOptionPane.ERROR_MESSAGE);
                isValid = false;
                return; // Stop execution if Tiered Seating is empty
            } else if (!additionalInfo2.equalsIgnoreCase("Y") && !additionalInfo2.equalsIgnoreCase("N")) {
                JOptionPane.showMessageDialog(this, "Tiered Seating must be either 'Y' or 'N'.", "Error", JOptionPane.ERROR_MESSAGE);
                isValid = false;
                return; // Stop execution if Tiered Seating is invalid
            }
        }

        // Display summary and confirm details
        String summary = "Room Type: " + roomType + "\n" +
                "Room ID: " + roomIdStr + "\n" +
                "Room Number: " + roomNumberStr + "\n" +
                "Floor: " + floor + "\n" +
                "Capacity: " + capacityStr + "\n";
        if (roomType.equals("Lab Room")) {
            summary += "No. of PCs: " + additionalInfo1 + "\n" +
                    "Operating System: " + additionalInfo2 + "\n";
        } else if (roomType.equals("Lecture Hall")) {
            summary += "Microphone Type: " + additionalInfo1 + "\n" +
                    "Tiered Seating: " + additionalInfo2 + "\n";
        }
        int confirmOption = JOptionPane.showConfirmDialog(this, "Please confirm the details:\n\n" + summary, "Confirm Room Details", JOptionPane.YES_NO_OPTION);
        if (confirmOption == JOptionPane.YES_OPTION) {
            // Store room details in the map
            roomDetailsMap.put(roomIdStr, summary);

            JOptionPane.showMessageDialog(this, "Room added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    // Helper method to check if a string represents an integer
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private void clearFields() {
        roomIdField.setText("");
        roomNumberField.setText("");
        floorField.setText("");
        capacityField.setText("");
        additionalField1.setText("");
        additionalField2.setText("");
    }

    private void viewRoom() {
        // Prompt the user to enter a Room ID
        String roomId = JOptionPane.showInputDialog(this, "Enter Room ID:");

        // Check if the user canceled or left the input blank
        if (roomId == null || roomId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Room ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the entered Room ID exists in the map
        String roomDetails = roomDetailsMap.get(roomId);
        if (roomDetails == null) {
            JOptionPane.showMessageDialog(this, "Room with ID '" + roomId + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, roomDetails, "Room Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RoomInterface().setVisible(true);
            }
        });
    }
}
