package tracker;
import java.util.Date;

public class Main {
    private static final boolean DEBUG_MODE = true;  // Change to true to enable testing

    public static void main(String[] args) {
        System.out.println("=== Running Main Program ===");
        runMainProgram();  // Runs the actual finance tracker

        if (DEBUG_MODE) {
            System.out.println("\n=== Running Tests ===");
            runTests();
        }
    }

    public static void runMainProgram() {
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

    // Runs all test cases if DEBUG_MODE is enabled
    public static void runTests() {
        testTransactionHandling();
        testEdgeCases();
        testCategoryManagement();
        testBalanceCalculations();
        testPerformance();

    }

    // Tests transaction handling logic
    public static void testTransactionHandling() {
        System.out.println("\n=== TEST 1: Basic Transaction Handling ===");

        FinanceTracker tracker = new FinanceTracker();

        // Create categories
        Category salary = new Category("Salary", "Income");
        Category groceries = new Category("Groceries", "Expense");

        // Add categories
        tracker.addCategory(salary);
        tracker.addCategory(groceries);

        // Add transactions
        tracker.addTransaction(new Transaction(3000, new Date(), salary, "Monthly paycheck"));
        tracker.addTransaction(new Transaction(-150, new Date(), groceries, "Groceries"));
        tracker.addTransaction(new Transaction(-100, new Date(), groceries, "Snacks"));

        // Print results
        System.out.println("\nExpected: 3 Transactions");
        tracker.printTransactions();

        System.out.println("\nExpected Balance: $2750.00");  // 3000 - 150 - 100
        System.out.println("Actual Balance: $" + tracker.getTotalBalance());
    }

    public static void testEdgeCases() {
        System.out.println("\n=== TEST 2: Edge Cases ===");
        FinanceTracker tracker = new FinanceTracker();

        //Create category
        Category testCategory = new Category("Misc", "Expense");
        tracker.addCategory(testCategory);

        //Adds transactions
        tracker.addTransaction(new Transaction(0, new Date(), testCategory, "Test Transaction"));
        tracker.addTransaction(new Transaction(-100, new Date(), testCategory, "Negative income (invalid?)"));
        tracker.addTransaction(new Transaction(200, new Date(), testCategory, "Positive income (invalid?)"));

        //Print results
        System.out.println("\nExpected: Transactions should handle zero, negative, and incorrect signs correctly.");
        tracker.printTransactions();
    }

    public static void testCategoryManagement() {
        System.out.println("\n=== TEST 3: Category Management ===");
        FinanceTracker tracker = new FinanceTracker();

        //Create categories
        Category rent = new Category("Rent", "Expense");
        Category groceries = new Category("Groceries", "Expense");

        tracker.addCategory(rent);
        tracker.addCategory(groceries);

        // Attempt duplicate category
        tracker.addCategory(new Category("Rent", "Expense"));

        // Attempt to add a transaction to a non-existing category
        tracker.addTransaction(new Transaction(-1000, new Date(), new Category("Non-Existing", "Expense"), "Should fail?"));

        // Print categories
        System.out.println("\nExpected: Duplicate category should be handled, and invalid category transactions should be flagged.");
        tracker.printCategories();
    }

    public static void testBalanceCalculations() {
        System.out.println("\n=== TEST 4: Balance Calculations ===");
        FinanceTracker tracker = new FinanceTracker();

        // Create categories
        Category salary = new Category("Salary", "Income");
        Category rent = new Category("Rent", "Expense");

        tracker.addCategory(salary);
        tracker.addCategory(rent);

        // Add transactions
        tracker.addTransaction(new Transaction(5000, new Date(), salary, "Monthly salary"));
        tracker.addTransaction(new Transaction(-2000, new Date(), rent, "Apartment rent"));
        tracker.addTransaction(new Transaction(-500, new Date(), rent, "Utilities"));

        // Print balance calculations
        System.out.println("\nExpected Total Balance: $2500.00");
        System.out.println("Actual Total Balance: $" + tracker.getTotalBalance());

        System.out.println("\nExpected Total Income: $5000.00");
        System.out.println("Actual Total Income: $" + tracker.getTotalIncome());

        System.out.println("\nExpected Total Expenses: $-2500.00");
        System.out.println("Actual Total Expenses: $" + tracker.getTotalExpense());
    }

    public static void testPerformance() {
        System.out.println("\n=== TEST 5: Performance Test (1000 Transactions) ===");
        FinanceTracker tracker = new FinanceTracker();
        Category salary = new Category("Salary", "Income");

        tracker.addCategory(salary);

        long startTime = System.currentTimeMillis();

        // Add 1000 transactions
        for (int i = 0; i < 1000; i++) {
            tracker.addTransaction(new Transaction(1000, new Date(), salary, "Salary payment #" + (i + 1)));
        }

        long endTime = System.currentTimeMillis();

        // Print results
        System.out.println("\nExpected: 1000 transactions processed efficiently.");
        System.out.println("Actual: Transactions Count = " + tracker.getTotalBalance() / 1000);
        System.out.println("Time Taken: " + (endTime - startTime) + "ms");
    }
}