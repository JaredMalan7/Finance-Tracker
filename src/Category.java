
public class Category {
    private static int idCounter = 1; //Auto-incrementing ID for each Category
    private int id; //Unique category ID
    private String name;    // Name of the category (e.g., Groceries, Bills)
    private String type;    // Either "Income" or Expense
    
    //This is the constructor to create a new category
    public Category(String name, String type) {
        this.id = idCounter++;
        this.name = name; //Name of the category
        this.type = type;   // Type of the category ("Income" or "Expense")
    }
    
    // Getter methods for accessing private fields
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    
    
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}