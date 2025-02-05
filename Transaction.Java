import java.util.Date;

Public Class Transaction {
    private static int idCounter = 1; //Auto-incrementing ID for each transaction
    private int id; //Unique transaction ID
    private double amount; //Amount of the transaction (+ for income, -for expense)
    private Date date;  //Date when the transaction occurred 
    private Category category;  //Category associated with the transaction
    private String description; //Optional description for transaction 
    
    // This is the constructor to create a new transaction. 
    public Transaction(double amount, Date date, Category category, String description) {
        this.id = idCounter++;
        this.amount = amount;   //Transaction amount (positive for income, negative for expense)
        this.date = date;  //date of the transaction 
        this.category = category;  //category of the transaction   
        this.description = description; //Description fo the transaction
    }
    
    // Getter methods for accessing private fields
    public int getId() { return id; }
    public double get Amount () { return amount; }
    public Date getDate() {return date; }
    public Date getDate() { return date; }
    public Category getCategory() {return category; }
    public string getDescription() {return description; }
    
    
    //Returns a formatted string representation of the transaction
    @override
    public String toString(){
        return String.format("Transaction{id=%d, amount=%2.f, date=%s, category=%s, description='%s'}", id, amount, date, category.getName(), description);
    }
}