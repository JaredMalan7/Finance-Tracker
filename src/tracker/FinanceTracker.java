package tracker;

import java.util.ArrayList;
import java.util.List;

public class FinanceTracker {
    private List<Transaction> transactions = new ArrayList<>();  // List of all transactions
    private List<Category> categories = new ArrayList<>();  // List of all categories

    // Constructor that initializes empty lists for transactions and categories
    public FinanceTracker() {
        this.transactions = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    // Adds a new transaction to the tracker.
    public void addTransaction(Transaction transaction) {

        //Check if Category Exists before adding a transaction
        boolean categoryExists = false;
        for (Category c : categories) {
            if (c.getName().equalsIgnoreCase(transaction.getCategory().getName())) {
                categoryExists = true;
                break;
            }
        }
        if (!categoryExists) {
            System.out.println("❌ Error: Cannot add transaction. Category '" + transaction.getCategory().getName() + "' does not exist.");
            return;
        }
        //=============================//
        // Validate Transactions
        if (transaction.getAmount() == 0) {
            System.out.println("⚠️ Warning: Zero-amount transactions are ignored.");
            return;
        }
        if (transaction.getCategory().getType().equals("Income") && transaction.getAmount() < 0) {
            System.out.println("❌ Error: Negative income transactions are not allowed.");
            return;
        }
        if( transaction.getCategory().getType().equals("Expense") && transaction.getAmount() > 0){
            System.out.println("❌ Error: Positive expenses are not allowed. Use negative values.");
            return;
        }

        transactions.add(transaction);
    }

    // Adds a new category to the tracker.
    public void addCategory(Category category) {
        for (Category c : categories) {
            if (c.getName().equalsIgnoreCase(category.getName())) {
                System.out.println("⚠️ Warning: Category '" + category.getName() + "' already exists. Skipping duplicate.");
                return;
            }
        }

        categories.add(category);
    }

    // Calculates the total balance.
    public double getTotalBalance() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    // Calculates the total income.
    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Calculates the total expenses.
    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Prints all transactions stored in the tracker.
    public void printTransactions(){
        transactions.forEach(System.out::println);
    }

    // Prints all categories stored in the tracker.
    public void printCategories(){
        categories.forEach(System.out::println);
    }
}