import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JWindow {

    private static final int SPLASH_SCREEN_DURATION_SECONDS = 10;
    private static final int SPLASH_SCREEN_WIDTH = 600;
    private static final int SPLASH_SCREEN_HEIGHT = 400;

    private boolean isFullScreen = false;
    private GraphicsDevice device;

    public SplashScreen() {
        setSize(SPLASH_SCREEN_WIDTH, SPLASH_SCREEN_HEIGHT);
        setLocationRelativeTo(null); // Center the splash screen on the screen
        setLayout(new BorderLayout());

        // Add background image
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/RoomBooking.jpg"));
        add(backgroundLabel, BorderLayout.CENTER);

        // Add label with program details
        JLabel programDetailsLabel = new JLabel(
                "<html>" +
                        "<center>" +
                        "<font size='6' color='#333333'><b>Room Booking System</b></font><br>" +
                        "<font size='4' color='#666666'>" +
                        "<b>Version:</b> 1.0<br>" +
                        "<b>Developed by:</b> Josh Grant - 60042385<br><br>" +
                        "This program is designed to help manage room bookings efficiently. " +
                        "It provides an intuitive interface for users to schedule and organize room reservations for various purposes.<br><br>" +
                        "<b>Key Features:</b><br>" +
                        "- Easy-to-use interface for booking rooms<br>" +
                        "- Support for multiple room types and configurations<br>" +
                        "- Real-time availability tracking<br>" +
                        "- Customizable settings for room preferences" +
                        "</font>" +
                        "</center>" +
                        "</html>",
                SwingConstants.CENTER
        );
        add(programDetailsLabel, BorderLayout.SOUTH);

        // Add full screen toggle button
        JButton fullScreenButton = new JButton("Toggle Full Screen");
        fullScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleFullScreen();
            }
        });
        add(fullScreenButton, BorderLayout.NORTH);

        // Get the default screen device
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = env.getDefaultScreenDevice();

        // Set up a timer to close the splash screen after a certain duration
        Timer timer = new Timer(SPLASH_SCREEN_DURATION_SECONDS * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the splash screen
                new MenuScreen().setVisible(true); // Show the menu screen
            }
        });
        timer.setRepeats(false); // Run only once
        timer.start();
    }

    private void toggleFullScreen() {
        if (isFullScreen) {
            device.setFullScreenWindow(null); // Exit full screen
            isFullScreen = false;
        } else {
            device.setFullScreenWindow(this); // Set to full screen
            isFullScreen = true;
        }
    }

    public static void main(String[] args) {
        // Set look and feel to Metal for better appearance
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create and display the splash screen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }
}
