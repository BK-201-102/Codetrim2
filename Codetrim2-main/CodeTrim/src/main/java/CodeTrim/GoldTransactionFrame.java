package CodeTrim;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GoldTransactionFrame extends JFrame {
    private final GoldAccount goldAccount;
    private JTextField amountField;
    private final JLabel balanceLabel;
 private static final double DEFAULT_WITHDRAWAL_LIMIT = 30000.0;
    public GoldTransactionFrame(GoldAccount goldAccount) {
        this.goldAccount = goldAccount;

        // Set up the frame
        setTitle("Gold Transaction");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        goldAccount.setLimit(DEFAULT_WITHDRAWAL_LIMIT);
        // Add components
        add(new JLabel("Select Transaction:"));

        String[] transactionOptions = {"Deposit", "Withdraw"};
        JComboBox<String> transactionComboBox = new JComboBox<>(transactionOptions);
        amountField = new JTextField(20); // Increased size
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
        balanceLabel = new JLabel("Current Balance: " + goldAccount.getBalance());
        panel.add(balanceLabel);

        add(panel);

        // Add action listener for the submit button
        submitButton.addActionListener((ActionEvent e) -> {
            handleTransactionInput(transactionComboBox, amountField);
        });
         submitButton.addActionListener((ActionEvent e) -> {
            handleTransactionInput(transactionComboBox, amountField);
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
    if ((selectedTransaction.equals("Deposit"))&&!(selectedTransaction.equals("Deposit"))) {
        JOptionPane.showMessageDialog(this, "Input is empty. Please enter an amount.");
        return;
    }else if ( (selectedTransaction.equals("Withdraw"))&&!(selectedTransaction.equals("Withdraw"))) {
        JOptionPane.showMessageDialog(this, "Input is empty. Please enter an amount.");
        return;
    }
}
    if (amountInput.equalsIgnoreCase("d") || amountInput.equalsIgnoreCase("f")) {
        JOptionPane.showMessageDialog(this, "Invalid Input. Do not enter string inputs.");
        return;
    }
  
    boolean withdrawalSuccess = false;
    try {
        double amount = Double.parseDouble(amountInput);

        // Perform deposit or withdrawal based on user selection
        switch (selectedTransaction) {
            case "Deposit" -> {
                goldAccount.deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposit Successful. New balance: " + goldAccount.getBalance());
            }
            case "Withdraw" -> {
                withdrawalSuccess = goldAccount.withdraw(amount);
                if (withdrawalSuccess) {
                    JOptionPane.showMessageDialog(this, "Withdrawal Successful. New balance: " + goldAccount.getBalance());

                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds or exceeded overdraft limit.");
                }
            }
            default -> JOptionPane.showMessageDialog(this, "Invalid transaction selected.");
        }

       
    } catch (NumberFormatException ex) {
        if (!(selectedTransaction.equals("Deposit") || selectedTransaction.equals("Withdraw")) && !amountInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
   
    }
    // Update the balance label
    updateBalanceLabel();
    clearFields(); // Clear input fields for the next transaction
}

    private void updateBalanceLabel() {
        // Update the balance label
        balanceLabel.setText("Current Balance: " + goldAccount.getBalance());
    }

    private void clearFields() {
        // Clear input fields for the next transaction
        amountField.setText("");
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GoldAccount goldAccount = new GoldAccount("12345", "John Doe", "123", 500);
            GoldTransactionFrame transactionFrame = new GoldTransactionFrame(goldAccount);
            transactionFrame.setVisible(true);
        });
    }
}