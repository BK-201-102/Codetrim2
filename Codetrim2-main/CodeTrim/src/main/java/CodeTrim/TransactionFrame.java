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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TransactionFrame extends JFrame {

    private final BankAccount regularAccount;
    private JTextField amountField;
    private final JLabel balanceLabel;
    private String accountNumber;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransactionFrame(BankAccount regularAccount) {
        this.regularAccount = regularAccount;

        // Set up the frame
        setTitle("Transaction");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add components
        add(new JLabel("Select Transaction:"));

        String[] transactionOptions = {"Deposit", "Withdraw"};
        JComboBox<String> transactionComboBox = new JComboBox<>(transactionOptions);
        JTextField amountField = new JTextField(20); // Increased size
        JButton submitButton = new JButton("Submit");
        JButton repeatButton = new JButton("Repeat Transaction");
        JButton exitButton = new JButton("Exit");

        // Create a panel to organize components
        JPanel panel = new JPanel();
        panel.add(new JLabel("Transaction:"));
        panel.add(transactionComboBox);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(submitButton);
        panel.add(repeatButton);
        panel.add(exitButton);

        // Display the current balance
        balanceLabel = new JLabel("Current Balance: " + regularAccount.getBalance());
        panel.add(balanceLabel);

        add(panel);

        // Add action listener for the submit button
        submitButton.addActionListener((ActionEvent e) -> {
            handleTransactionInput(transactionComboBox, amountField);
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClosing();
            }
        });

        // Add action listener for the repeat button
        repeatButton.addActionListener((ActionEvent e) -> {
            clearFields();
        });

        // Add action listener for the exit button
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    private void handleTransactionInput(JComboBox<String> transactionComboBox, JTextField amountField) {
        String selectedTransaction = (String) transactionComboBox.getSelectedItem();
        String amountInput = amountField.getText();

        if (amountInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Input. Please enter an amount.");
            return;
        }
        if (amountInput.contains("d") || amountInput.contains("f")) {
            JOptionPane.showMessageDialog(this, "Invalid Input. Do not enter string inputs.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountInput);

            // Validate that the amount is positive
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive number.");
                return;
            }

            // Perform deposit or withdrawal based on user selection
            switch (selectedTransaction) {
                case "Deposit" -> {
                    regularAccount.deposit(amount);
                    JOptionPane.showMessageDialog(this, "Deposit Successful. New balance: " + regularAccount.getBalance());
                }
                case "Withdraw" -> {
                    boolean withdrawalSuccess = regularAccount.withdraw(amount);
                    if (withdrawalSuccess) {
                        JOptionPane.showMessageDialog(this, "Withdrawal Successful. New balance: " + regularAccount.getBalance());

                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds for withdrawal.");
                    }
                }
                default ->
                    JOptionPane.showMessageDialog(this, "Invalid transaction selected.");
            }

            // Close the current frame
            this.setVisible(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }

        // Update the balance label
        balanceLabel.setText("Current Balance: " + regularAccount.getBalance());

        clearFields(); // Clear input fields for the next transaction
    }

    private void handleWindowClosing() {
        // Handle any actions needed when the window is closed
        // In this case, you might want to clear sensitive information or perform other tasks
        clearFields();

    }

    private void clearFields() {
        // Clear input fields for the next transaction
        amountField.setText("");
        updateBalanceLabel();
    }

    private void updateBalanceLabel() {
        // Update the balance label
        balanceLabel.setText("Current Balance: " + regularAccount.getBalance());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAccount regularAccount = new BankAccount("12345", "John Doe", "123");
            TransactionFrame transactionFrame = new TransactionFrame(regularAccount);
            transactionFrame.setVisible(true);
        });
    }
}
