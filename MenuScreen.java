import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuScreen extends JFrame {

    private static final int MENU_SCREEN_WIDTH = 600;
    private static final int MENU_SCREEN_HEIGHT = 400;

    public MenuScreen() {
        setTitle("Room Booking System - Menu");
        setSize(MENU_SCREEN_WIDTH, MENU_SCREEN_HEIGHT);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Menu screen content
        JButton roomButton = new JButton("Room");
        JButton bookingButton = new JButton("Booking");
        JButton exitButton = new JButton("Exit");
        JButton helpButton = new JButton("Help");

        // Set button fonts
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        roomButton.setFont(buttonFont);
        bookingButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        helpButton.setFont(buttonFont);

        // Set button dimensions
        Dimension buttonDimension = new Dimension(200, 50);
        roomButton.setPreferredSize(buttonDimension);
        bookingButton.setPreferredSize(buttonDimension);
        exitButton.setPreferredSize(buttonDimension);
        helpButton.setPreferredSize(buttonDimension);

        // Set button action listeners
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRoomManagement();
            }
        });

        bookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBookingManagement();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitSystem();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHelp();
            }
        });

        // Layout setup
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(roomButton);
        buttonPanel.add(bookingButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void openRoomManagement() {
        // Open the room management interface
        RoomInterface roomInterface = new RoomInterface();
        roomInterface.setVisible(true);
        roomInterface.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the opened window
    }

    private void openBookingManagement() {
        // Open the booking management interface
        BookingInterface bookingInterface = new BookingInterface();
        bookingInterface.setVisible(true);
        bookingInterface.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the opened window
    }

    private void displayHelp() {
        // Display help information
        JOptionPane.showMessageDialog(this, """
                Room Management:

                To add a new room, click on the "Room Management" button and then select "Add Room".
                Enter the required details such as Room ID, Room Number, Floor, Capacity, and Room Type.
                If adding a Lab Room, provide additional information about the number of PCs and the operating system.
                If adding a Lecture Hall, provide additional information about the microphone type and tiered seating.
                Click "Confirm" to add the room to the system.
                
                View a Room:
                
                To view details of a room, click on the "Room" button and then select "View Room".
                Enter the Room ID of the room you want to view. The system will display the details of the room.

                Booking Management:

                To make a new booking, click on the "Booking" button and then select "Add Booking".
                Enter the Booking ID, Room ID, Date, Time, Contact Person, Contact Email, and Contact Phone.
                Choose the appropriate time slot from the options provided.
                Click "Add Booking" to confirm the booking.

                Viewing a Booking:

                To view details of a booking, click on the "Booking Management" button and then select "View Booking".
                Enter the Booking ID of the booking you want to view.
                The system will display the details of the booking, including the associated room information.

                Deleting a Booking:

                To delete a booking, click on the "Booking Management" button and then select "Delete Booking".
                Enter the Booking ID of the booking you want to delete.
                Confirm the deletion when prompted.

                Exiting the System:

                To exit the room booking system, click on the "Exit" button in the menu bar.
                Confirm your choice when prompted.

                Note:

                Make sure to enter valid information when adding or editing rooms and bookings.
                Contact your system administrator if you encounter any issues or require further assistance.""", "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exitSystem() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the room booking system?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0); // Exit the application
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuScreen().setVisible(true);
            }
        });
    }
}
