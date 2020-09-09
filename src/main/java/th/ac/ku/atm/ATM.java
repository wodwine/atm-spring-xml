package th.ac.ku.atm;

public class ATM {
    private Bank bank;
    private Customer loginCustomer;

    public ATM(Bank bank) {
        this.bank = bank;
        this.loginCustomer = null;
    }

    public String validateCustomer(int id, int pin) {
        // Check id
        Customer customer = bank.findCustomer(id);

        // Check pin
        if (customer != null && customer.checkPin(pin)) {
            this.loginCustomer = customer;
            return this.loginCustomer.getName();
        }
        return null;
    }

    public void deposit(double amount) {
        this.loginCustomer.getAccount().deposit(amount);
    }

    public void withdraw(double amount) {
        this.loginCustomer.getAccount().withdraw(amount);
    }

    public double getBalance() {
        return this.loginCustomer.getAccount().getBalance();
    }

    public void transfer(int receivingId, double amount) {
        Customer receivingCustomer = bank.findCustomer(receivingId);
        loginCustomer.getAccount().withdraw(amount);
        receivingCustomer.getAccount().deposit(amount);
    }

    public void end() {
        loginCustomer = null;
    }
}
