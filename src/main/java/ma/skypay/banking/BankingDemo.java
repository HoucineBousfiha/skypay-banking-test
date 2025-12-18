package ma.skypay.banking;

import ma.skypay.banking.domain.Account;

/**
 * Demo class to showcase the banking service implementation.
 * Demonstrates the acceptance test scenario from the requirements.
 */
public class BankingDemo {
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   Banking Service - Skypay Technical Test   ");
        System.out.println("==============================================\n");
        
        // Create a new account
        Account account = new Account();
        
        System.out.println("Executing acceptance test scenario...\n");
        
        // Execute the test scenario
        System.out.println("1. Depositing 1000...");
        account.deposit(1000);
        
        System.out.println("2. Depositing 2000...");
        account.deposit(2000);
        
        System.out.println("3. Withdrawing 500...");
        account.withdraw(500);
        
        System.out.println("\n--- Account Statement ---\n");
        account.printStatement();
        
        System.out.println("\n==============================================");
        System.out.println("Current Balance: " + account.getBalance());
        System.out.println("==============================================");
    }
}