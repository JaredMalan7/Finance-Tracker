package tracker;

import java.util.Date;

public class Transaction {
    private static int idCounter = 1; // Auto-incrementing ID for each transaction
    private int id; // Unique transaction ID
    private double amount; // Amount of the transaction (+ for income, - for expense)
    private Date date;  // Date when the transaction occurred
    private Category category;  // tracker.Category associated with the transaction
    private String description; // Optional description for transaction

    // Constructor to create a new transaction
    public Transaction(double amount, Date date, Category category, String description) {
        this.id = idCounter++;
        this.amount = amount; // tracker.Transaction amount (positive for income, negative for expense)
        this.date = date; // Date of the transaction
        this.category = category; // tracker.Category of the transaction
        this.description = description; // Description of the transaction
    }

    // Getter methods for accessing private fields
    public int getId() { return id; }
    public double getAmount() { return amount; }
    public Date getDate() { return date; }
    public Category getCategory() { return category; }
    public String getDescription() { return description; }

    // Returns a formatted string representation of the transaction
    @Override
    public String toString(){
        return String.format("tracker.Transaction{id=%d, amount=%.2f, date=%s, category=%s, description='%s'}",
                id, amount, date, category.getName(), description);
    }
}