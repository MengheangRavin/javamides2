import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField passcodeField;
    private JButton enterButton;
    private JButton clearButton;
    private JLabel statusLabel;
    private JLabel resultLabel;
    private String savedPassword;
    private boolean isPasswordSet;

    public Main() {
        // Set up the frame
        setTitle("Locker Application");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the passcode field and buttons
        passcodeField = new JTextField(10);
        enterButton = new JButton("Enter");
        clearButton = new JButton("Clear");
        statusLabel = new JLabel("Please set your passcode.");
        resultLabel = new JLabel();
        savedPassword = null;
        isPasswordSet = false;

        // Create a panel to hold the passcode field
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Passcode:"));
        topPanel.add(passcodeField);

        // Create a panel for the number pad
        JPanel numberPadPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 1; i <= 9; i++) {
            addButtonToNumberPad(numberPadPanel, String.valueOf(i));
        }

        // Create a panel for the bottom buttons and labels
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(clearButton);
        buttonPanel.add(statusLabel);
        buttonPanel.add(enterButton);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(resultLabel, BorderLayout.EAST);

        // Add components to the frame
        add(topPanel, BorderLayout.NORTH);
        add(numberPadPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listener to the enter button
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEnterButtonClick();
            }
        });

        // Add action listener to the clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClearButtonClick();
            }
        });
    }

    private void addButtonToNumberPad(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passcodeField.setText(passcodeField.getText() + text);
            }
        });
        panel.add(button);
    }

    private void handleEnterButtonClick() {
        String enteredPasscode = passcodeField.getText();

        if (!isPasswordSet) {
            // Set the password for the first time
            savedPassword = enteredPasscode;
            isPasswordSet = true;
            statusLabel.setText("Password Set");
            resultLabel.setText("");
        } else {
            // Verify the entered password
            if (enteredPasscode.equals(savedPassword)) {
                statusLabel.setText("Password Status:");
                resultLabel.setText("Correct Password");
            } else {
                statusLabel.setText("Password Status:");
                resultLabel.setText("Incorrect Password");
            }
        }

        // Clear the passcode field after processing
        passcodeField.setText("");
    }

    private void handleClearButtonClick() {
        // Clear the passcode field and reset the status message
        passcodeField.setText("");
        statusLabel.setText("Please set your passcode.");
        resultLabel.setText("");
    }
    public static void main(String[] args) {
        // Create and display the application window
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}