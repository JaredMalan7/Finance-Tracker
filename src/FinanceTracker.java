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
        transactions.add(transaction);
    }

    // Adds a new category to the tracker.
    public void addCategory(Category category) {
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