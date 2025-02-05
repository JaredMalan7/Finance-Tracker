import java.util.ArrayList;
import java.util.List;

// Manages financial transactions and categories
// Provides methods to add/remove transaction, calculate balance, and track spending. 
public class FinanceTracker {
    private List<Transaction> transaction;  // list all transactions
    private List<Category> categories;  // lists of all categories
    
    // Constructor that initializes empty lists for transactions and categories
    public FinanceTracker(){
        this.transaction = new ArrayList<>();
        this.categories = new ArrayList<>();
    }
    
    
    
}