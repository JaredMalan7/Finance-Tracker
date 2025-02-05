package tracker;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        FinanceTracker tracker = new FinanceTracker(); // Create finance tracker instance

        // Create categories
        Category salary = new Category("Salary", "Income");
        Category groceries = new Category("Groceries", "Expense");

        // Add categories to the tracker
        tracker.addCategory(salary);
        tracker.addCategory(groceries);

        // Add transactions (Income and Expenses)
        tracker.addTransaction(new Transaction(2000, new Date(), salary, "Monthly paycheck"));
        tracker.addTransaction(new Transaction(-150, new Date(), groceries, "Bought groceries"));

        // Print categories
        System.out.println("Categories:");
        tracker.printCategories();

        // Print transactions
        System.out.println("\nTransactions:");
        tracker.printTransactions();

        // Print balance summary
        System.out.println("\nTotal Balance: $" + tracker.getTotalBalance());
        System.out.println("Total Income: $" + tracker.getTotalIncome());
        System.out.println("Total Expense: $" + tracker.getTotalExpense());
    }
}