package ma.skypay.banking.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for Account class.
 * Tests individual operations and edge cases.
 */
@DisplayName("Account Unit Tests")
class AccountTest {
    
    private Account account;
    
    @BeforeEach
    void setUp() {
        account = new Account();
    }
    
    @Test
    @DisplayName("Should create account with zero balance")
    void shouldCreateAccountWithZeroBalance() {
        assertThat(account.getBalance()).isEqualTo(0);
        assertThat(account.getTransactions()).isEmpty();
    }
    
    @Test
    @DisplayName("Should deposit money successfully")
    void shouldDepositMoney() {
        account.deposit(1000);
        
        assertThat(account.getBalance()).isEqualTo(1000);
        assertThat(account.getTransactions()).hasSize(1);
    }
    
    @Test
    @DisplayName("Should handle multiple deposits")
    void shouldHandleMultipleDeposits() {
        account.deposit(1000);
        account.deposit(2000);
        
        assertThat(account.getBalance()).isEqualTo(3000);
        assertThat(account.getTransactions()).hasSize(2);
    }
    
    @Test
    @DisplayName("Should withdraw money successfully")
    void shouldWithdrawMoney() {
        account.deposit(1000);
        account.withdraw(500);
        
        assertThat(account.getBalance()).isEqualTo(500);
        assertThat(account.getTransactions()).hasSize(2);
    }
    
    @Test
    @DisplayName("Should throw exception for negative deposit")
    void shouldThrowExceptionForNegativeDeposit() {
        assertThatThrownBy(() -> account.deposit(-100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Deposit amount must be positive");
    }
    
    @Test
    @DisplayName("Should throw exception for zero deposit")
    void shouldThrowExceptionForZeroDeposit() {
        assertThatThrownBy(() -> account.deposit(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Deposit amount must be positive");
    }
    
    @Test
    @DisplayName("Should throw exception for negative withdrawal")
    void shouldThrowExceptionForNegativeWithdrawal() {
        account.deposit(1000);
        
        assertThatThrownBy(() -> account.withdraw(-100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Withdrawal amount must be positive");
    }
    
    @Test
    @DisplayName("Should throw exception for insufficient funds")
    void shouldThrowExceptionForInsufficientFunds() {
        account.deposit(500);
        
        assertThatThrownBy(() -> account.withdraw(1000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Insufficient funds");
    }
    
    @Test
    @DisplayName("Should record transaction amounts correctly")
    void shouldRecordTransactionAmountsCorrectly() {
        account.deposit(1000);
        account.withdraw(500);
        
        Transaction deposit = account.getTransactions().get(0);
        Transaction withdrawal = account.getTransactions().get(1);
        
        assertThat(deposit.getAmount()).isEqualTo(1000);
        assertThat(withdrawal.getAmount()).isEqualTo(-500);
    }
    
    @Test
    @DisplayName("Should maintain running balance in transactions")
    void shouldMaintainRunningBalance() {
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        
        assertThat(account.getTransactions().get(0).getBalance()).isEqualTo(1000);
        assertThat(account.getTransactions().get(1).getBalance()).isEqualTo(3000);
        assertThat(account.getTransactions().get(2).getBalance()).isEqualTo(2500);
    }
}