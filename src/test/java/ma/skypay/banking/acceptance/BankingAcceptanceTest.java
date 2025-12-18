package ma.skypay.banking.acceptance;

import ma.skypay.banking.domain.Account;
import ma.skypay.banking.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Acceptance test based on the specification from the technical test.
 * Tests the complete user scenario:
 * - Deposit 1000 on 10-01-2012
 * - Deposit 2000 on 13-01-2012
 * - Withdraw 500 on 14-01-2012
 * - Print statement
 */
@DisplayName("Banking Acceptance Test")
class BankingAcceptanceTest {
    
    private Account account;
    private ByteArrayOutputStream outputStream;
    
    @BeforeEach
    void setUp() {
        account = new Account();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    
    @Test
    @DisplayName("Should execute the acceptance test scenario successfully")
    void shouldExecuteAcceptanceTestScenario() {
        // Given a client makes a deposit of 1000
        account.deposit(1000);
        
        // And a deposit of 2000
        account.deposit(2000);
        
        // And a withdrawal of 500
        account.withdraw(500);
        
        // When they print their bank statement
        account.printStatement();
        
        // Then they would see the correct output
        String output = outputStream.toString();
        
        assertThat(output).contains("Date       || Amount || Balance");
        assertThat(account.getBalance()).isEqualTo(2500);
        assertThat(account.getTransactions()).hasSize(3);
    }
    
    @Test
    @DisplayName("Should maintain correct balance after all operations")
    void shouldMaintainCorrectBalance() {
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        
        assertThat(account.getBalance()).isEqualTo(2500);
    }
    
    @Test
    @DisplayName("Should record all transactions in correct order")
    void shouldRecordTransactionsInCorrectOrder() {
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        
        List<Transaction> transactions = account.getTransactions();
        
        assertThat(transactions).hasSize(3);
        assertThat(transactions.get(0).getAmount()).isEqualTo(1000);
        assertThat(transactions.get(0).getBalance()).isEqualTo(1000);
        
        assertThat(transactions.get(1).getAmount()).isEqualTo(2000);
        assertThat(transactions.get(1).getBalance()).isEqualTo(3000);
        
        assertThat(transactions.get(2).getAmount()).isEqualTo(-500);
        assertThat(transactions.get(2).getBalance()).isEqualTo(2500);
    }
    
    @Test
    @DisplayName("Should print statement in reverse chronological order")
    void shouldPrintStatementInReverseOrder() {
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        
        account.printStatement();
        
        String output = outputStream.toString();
        String[] lines = output.split(System.lineSeparator());
        
        assertThat(lines).hasSizeGreaterThanOrEqualTo(4);
        assertThat(lines[0]).contains("Date       || Amount || Balance");
        
        // Latest transaction should be printed first (after header)
        assertThat(lines[1]).contains("-500");
        assertThat(lines[1]).contains("2500");
    }
}