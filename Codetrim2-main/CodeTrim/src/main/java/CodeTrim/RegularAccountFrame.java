package CodeTrim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegularAccountFrame extends JFrame {

    private JTextField accountNumberField;
    private JPasswordField pinField;
    private BankAccount regularAccount; // Assuming you have a BankAccount instance for the user
    
    public RegularAccountFrame(String numberIn, String nameIn, String pin) {
        // Set up the frame
        setTitle("Regular Account");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add components
        add(new JLabel("Welcome to Regular Account!"));
        accountNumberField = new JTextField();
        JButton submitButton = new JButton("Submit");
        pinField = new JPasswordField();
        pinField.setColumns(4);

        // Create a panel to organize components
        JPanel panel = new JPanel();
        accountNumberField.setColumns(10);
        panel.add(new JLabel("Enter account number: "));
        panel.add(accountNumberField);
        panel.add(new JLabel("Enter PIN: "));
        panel.add(pinField);
        panel.add(submitButton);

        add(panel);
        
        setBankAccount(numberIn, nameIn, pin);
        System.out.println("numberIn" + regularAccount.getAccountNumber());
        System.out.println("nameIn" + regularAccount.getAccountName());
        System.out.println("pin" + regularAccount.getPin());
        
        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAccountNumberInput();
            }
        });
    }

    private void handleAccountNumberInput() {
        String accountNumberInput = accountNumberField.getText();
        char[] pinChars = pinField.getPassword();
        String pin = new String(pinChars);
        if (accountNumberInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Input. Please enter an account number.");
            return;
        }
        // Assuming you have a mechanism to validate the account number
        // For now, I'll assume you have a regularAccount instance
        // Replace this with your actual validation logic
        regularAccount = validateAccountNumber(accountNumberInput, pin);
        if (accountNumberInput.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Input. Please enter account number and PIN.");
            return;
        }

        if (regularAccount != null) {
            // Account number is valid, open the second JFrame
            openTransactionFrame();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Account Number. Please try again.");
        }
    }

    private void openTransactionFrame() {
        // Create a new instance of TransactionFrame
        TransactionFrame transactionFrame = new TransactionFrame(regularAccount);

        // Pass the account number to TransactionFrame
        transactionFrame.setAccountNumber(regularAccount.getAccountNumber());

        // Add a window close listener to open a new RegularAccountFrame when the TransactionFrame is closed
        transactionFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                openRegularAccountFrame(regularAccount.getAccountName(), regularAccount.getAccountNumber(), regularAccount.getPin());
            }
        });

        transactionFrame.setVisible(true);
    }

    private void openRegularAccountFrame(String username, String accountNumber, String pin) {
        // Create a new instance of RegularAccountFrame
        RegularAccountFrame regularAccountFrame = new RegularAccountFrame(username, accountNumber, pin);
        regularAccountFrame.setVisible(true);

        // Close the current RegularAccountFrame if needed
        this.dispose();
    }

    private void setBankAccount(String numberIn, String nameIn, String pin) {
        // Assuming you have a mechanism to set the BankAccount instance
        // Replace this with your actual logic to set the regularAccount instance
        // Set other properties of regularAccount if needed
        // regularAccount = new BankAccount("12345", "John Doe", "123");
        regularAccount = new BankAccount(numberIn, nameIn, pin);
        System.out.println("numberIn" + numberIn);
        System.out.println("nameIn" + nameIn);
        System.out.println("pin" + pin);
    }

    public static void main(String[] args) {
        // Create an instance of RegularAccountFrame
        SwingUtilities.invokeLater(() -> {
            RegularAccountFrame frame = new RegularAccountFrame("", "", "");
            frame.setVisible(true);
        });
    }

    // Replace this method with your actual validation logic
    private BankAccount validateAccountNumber(String accountNumber, String enteredPin) {
        // Your logic to validate the account number and fetch the corresponding BankAccount
        // For now, I'll assume you have a regularAccount instance

        // Check if the entered account number matches the user's account number
        if (regularAccount != null && regularAccount.getAccountNumber().equals(accountNumber)) {
            // Check if the entered PIN matches the user's PIN
            if (regularAccount.getPin().equals(enteredPin)) {
                return regularAccount; // Return the corresponding BankAccount
            }
        }
        
        return null; // Return null if there is no match
    }
}
