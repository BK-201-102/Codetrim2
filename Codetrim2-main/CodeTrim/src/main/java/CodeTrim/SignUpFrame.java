/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodeTrim;

/**
 *
 * @author Katharsis
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.GridLayout;

public class SignUpFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> accountTypeComboBox;

    private Map<String, UserData> userDatabase;
    private LoginFrame loginFrame;
    private JPasswordField pinField;

    public SignUpFrame(Map<String, UserData> userDatabase, LoginFrame loginFrame) {
        this.userDatabase = userDatabase;
        this.loginFrame = loginFrame;

        setTitle("Sign Up");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Use DISPOSE_ON_CLOSE instead of EXIT_ON_CLOSE
        setLocationRelativeTo(null);

        // Initialize components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        pinField = new JPasswordField(4); // Set the length of the PIN
        pinField.setEchoChar('*'); // Set the mask character for the PIN input

        accountTypeComboBox = new JComboBox<>(new String[]{"Regular Account", "Gold Account"});

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLayout(new GridLayout(5, 2));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("PIN:"));
        add(pinField);
        add(new JLabel("Account Type:"));
        add(accountTypeComboBox);
        add(signUpButton);
    }

    private String generateAccountNumber() {
        Random random = new Random();
        int accountNumber = 10000000 + random.nextInt(90000000);
        return String.valueOf(accountNumber);
    }

    public void signUp() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String accountType = (String) accountTypeComboBox.getSelectedItem();
        String pin = String.valueOf(pinField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username and password", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
        } else {
            String accountNumber = generateAccountNumber();
            userDatabase.put(username, new UserData(username, password, accountType, accountNumber, pin));
            JOptionPane.showMessageDialog(this, "Sign Up successful\nYour account number is: " + accountNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
            
            loginFrame.updateUserDatabase(userDatabase);

            // Close the sign-up frame
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame loginFrame = new LoginFrame(new HashMap<>());
                SignUpFrame signUpFrame = new SignUpFrame(loginFrame.getUserDatabase(), loginFrame);

                loginFrame.setVisible(true);
                signUpFrame.setVisible(true);
            }
        });
    }

    public static class UserData {

        public String username;
        public String password;
        public String accountType;
        public String accountNumber;
        public String pin;

        public UserData(String username, String password, String accountType, String accountNumber, String pin) {
            this.username = username;
            this.password = password;
            this.accountType = accountType;
            this.accountNumber = accountNumber;
            this.pin = pin;
        }

        public String getAccountNumber() {
            return accountNumber;
        }
    }
}
