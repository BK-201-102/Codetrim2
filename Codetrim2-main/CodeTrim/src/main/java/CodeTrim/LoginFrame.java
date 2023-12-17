package CodeTrim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> accountTypeComboBox;

    private Map<String, SignUpFrame.UserData> userDatabase;

    public LoginFrame(Map<String, SignUpFrame.UserData> userDatabase) {
        // Set up the frame
        setTitle("Login System");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.userDatabase = userDatabase;

        // Create components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        accountTypeComboBox = new JComboBox<>(new String[]{"Regular Account", "Gold Account"});

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpFrame();
            }
        });

        // Create layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add components to the frame
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Account Type:"));
        add(accountTypeComboBox);
        add(loginButton);
        add(signUpButton);
    }

    private void login() {
        // Get user input
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String accountType = (String) accountTypeComboBox.getSelectedItem();

        // Validate login
        SignUpFrame.UserData userData = userDatabase.get(username);
        if (userData != null && userData.password.equals(password) && userData.accountType.equals(accountType)) {
            // Successful login
            openAccountFrame(accountType, username);
        } else {
            // Failed login
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openSignUpFrame() {
        SignUpFrame signUpFrame = new SignUpFrame(userDatabase, this);
        signUpFrame.setVisible(true);
    }

    public Map<String, SignUpFrame.UserData> getUserDatabase() {
        return userDatabase;
    }

    public void updateUserDatabase(Map<String, SignUpFrame.UserData> updatedUserDatabase) {
        this.userDatabase = updatedUserDatabase;
    }

    private void openAccountFrame(String accountType, String username) {
        if ("Regular Account".equals(accountType)) {
            SignUpFrame.UserData userData = userDatabase.get(username);
            RegularAccountFrame regularAccountFrame = new RegularAccountFrame(userData.accountNumber, userData.username , userData.pin);
            regularAccountFrame.setVisible(true);
            
            
        } else if ("Gold Account".equals(accountType)) {
            GoldAccountFrame goldAccountFrame = new GoldAccountFrame();
            goldAccountFrame.setVisible(true);
        }

        // Close the login frame
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame loginFrame = new LoginFrame(new HashMap<>());
                loginFrame.setVisible(true);
            }
        });
    }
}
