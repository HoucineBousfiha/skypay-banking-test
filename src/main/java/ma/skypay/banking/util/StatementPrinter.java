package ma.skypay.banking.util;

import ma.skypay.banking.domain.Transaction;
import java.util.List;

/**
 * Utility class responsible for printing account statements.
 * Follows Single Responsibility Principle - only handles statement formatting.
 */
public class StatementPrinter {
    
    private static final String HEADER = "Date       || Amount || Balance";
    
    /**
     * Prints the statement header and all transactions in reverse chronological order.
     * 
     * @param transactions List of transactions to print
     */
    public void print(List<Transaction> transactions) {
        System.out.println(HEADER);
        
        // Print transactions in reverse order (newest first)
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            System.out.println(formatTransaction(transaction));
        }
    }
    
    /**
     * Formats a single transaction for display.
     * 
     * @param transaction The transaction to format
     * @return Formatted string representation
     */
    private String formatTransaction(Transaction transaction) {
        return String.format("%s || %d || %d",
                transaction.getFormattedDate(),
                transaction.getAmount(),
                transaction.getBalance());
    }
}