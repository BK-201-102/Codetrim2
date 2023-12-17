package CodeTrim;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BankAccount {
    // the attributes

    private String accountNumber;
    private String accountName;
    protected double balance;
    private String pin;

    // the methods
    // the constructor
    public BankAccount(String numberIn, String nameIn, String pin) {
        accountNumber = numberIn;
        accountName = nameIn;
        balance = 0;
        this.pin = pin;
    }

    // methods to read the attributes
    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }
    // methods to deposit and withdraw money

    public void deposit(double amountIn) {
        balance = balance + amountIn;
    }

    public boolean withdraw(double amountIn) {
        if (amountIn > balance) {
            return false; // no withdrawal was made
        } else {
            balance = balance - amountIn;
            return true; // money was withdrawn successfully
        }
    }

    public boolean validateAccount(String enteredAccountNumber, String enteredPin) {
        return accountNumber.equals(enteredAccountNumber) && pin.equals(enteredPin);
    }

}
