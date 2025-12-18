package ma.skypay.banking.domain;

import ma.skypay.banking.service.AccountService;
import ma.skypay.banking.util.StatementPrinter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of AccountService representing a bank account.
 * Manages deposits, withdrawals, and maintains transaction history.
 */
public class Account implements AccountService {
    
    private final List<Transaction> transactions;
    private final StatementPrinter statementPrinter;
    private int balance;
    
    /**
     * Creates a new account with zero balance.
     */
    public Account() {
        this.transactions = new ArrayList<>();
        this.statementPrinter = new StatementPrinter();
        this.balance = 0;
    }
    
    /**
     * Constructor with custom statement printer (useful for testing).
     * 
     * @param statementPrinter Custom statement printer
     */
    public Account(StatementPrinter statementPrinter) {
        this.transactions = new ArrayList<>();
        this.statementPrinter = statementPrinter;
        this.balance = 0;
    }
    
    /**
     * Deposits money into the account.
     * 
     * @param amount Amount to deposit (must be positive)
     * @throws IllegalArgumentException if amount is not positive
     */
    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        balance += amount;
        Transaction transaction = new Transaction(LocalDate.now(), amount, balance);
        transactions.add(transaction);
    }
    
    /**
     * Withdraws money from the account.
     * 
     * @param amount Amount to withdraw (must be positive)
     * @throws IllegalArgumentException if amount is not positive
     * @throws IllegalStateException if insufficient funds
     */
    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (balance < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        
        balance -= amount;
        Transaction transaction = new Transaction(LocalDate.now(), -amount, balance);
        transactions.add(transaction);
    }
    
    /**
     * Prints the account statement showing all transactions.
     * Transactions are displayed in reverse chronological order (newest first).
     */
    @Override
    public void printStatement() {
        statementPrinter.print(transactions);
    }
    
    /**
     * Gets the current balance.
     * 
     * @return Current account balance
     */
    public int getBalance() {
        return balance;
    }
    
    /**
     * Gets all transactions (for testing purposes).
     * 
     * @return Unmodifiable view of transactions
     */
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}