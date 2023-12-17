package CodeTrim;

public class GoldAccount extends BankAccount {

    private double overdraftLimit;

    public GoldAccount(String numberIn, String nameIn, String pin, double limitIn) {
        super(numberIn, nameIn, pin);
        overdraftLimit = limitIn;
    }

    public void setLimit(double limitIn) {
        overdraftLimit = limitIn;
    }

    public double getLimit() {
        return overdraftLimit;
    }

    @Override
    public boolean withdraw(double amountIn) {
        if (amountIn > overdraftLimit) {
            return false; // no withdrawal was made
        }

        balance -= amountIn;
        return true; // money was withdrawn successfully
    }

}
