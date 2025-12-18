package ma.skypay.banking.service;

/**
 * Public interface for banking account operations.
 **/
public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    void printStatement();
}