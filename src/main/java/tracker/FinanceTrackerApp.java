package tracker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;

public class FinanceTrackerApp extends Application {
    private FinanceTracker tracker;
    private Label balanceLabel;
    private ListView<String> transactionListView;
    private ObservableList<String> transactionList;
    private ObservableList<String> categoryList;
    private ComboBox<String> categoryComboBox;
    private Button toggleTypeButton;
    private boolean isIncome = true; // Default is Income

    @Override
    public void start(Stage primaryStage) {
        tracker = new FinanceTracker();
        tracker.addCategory(new Category("Salary", "General"));
        tracker.addCategory(new Category("Groceries", "General"));

        // ObservableList for dynamic category updates
        categoryList = FXCollections.observableArrayList("Salary", "Groceries");

        // Initialize UI
        VBox layout = createUI();

        // Scene setup
        Scene scene = new Scene(layout, 450, 550);
        primaryStage.setTitle("Finance Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createUI() {
        // Balance Label
        balanceLabel = new Label("Total Balance: $" + tracker.getTotalBalance());

        // Transaction List
        transactionList = FXCollections.observableArrayList();
        transactionListView = new ListView<>(transactionList);

        // Input Fields for Transactions
        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        categoryComboBox = new ComboBox<>(categoryList);
        categoryComboBox.setPromptText("Select Category");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        // Toggle Button for Income/Expense
        toggleTypeButton = new Button("Income");
        toggleTypeButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        toggleTypeButton.setOnAction(e -> toggleTransactionType());

        // HBox for category & toggle
        HBox categorySelection = new HBox(10, categoryComboBox, toggleTypeButton);

        // Add Transaction Button
        Button addTransactionButton = new Button("Add Transaction");
        addTransactionButton.setOnAction(e -> handleAddTransaction(amountField, categoryComboBox, descriptionField));

        // New Category Fields
        TextField newCategoryField = new TextField();
        newCategoryField.setPromptText("New Category Name");

        Button addCategoryButton = new Button("Add Category");
        addCategoryButton.setOnAction(e -> handleAddCategory(newCategoryField));

        // Layout setup
        VBox layout = new VBox(10,
                balanceLabel, transactionListView,
                amountField, categorySelection, descriptionField, addTransactionButton,
                new Label("Add New Category"),
                newCategoryField, addCategoryButton);
        layout.setPadding(new Insets(15));

        return layout;
    }

    private void toggleTransactionType() {
        isIncome = !isIncome;
        if (isIncome) {
            toggleTypeButton.setText("Income");
            toggleTypeButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        } else {
            toggleTypeButton.setText("Expense");
            toggleTypeButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        }
    }

    private void handleAddTransaction(TextField amountField, ComboBox<String> categoryComboBox, TextField descriptionField) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String categoryName = categoryComboBox.getValue();
            String description = descriptionField.getText();

            if (categoryName == null || description.isEmpty()) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            // Adjust sign based on transaction type (income/expense)
            amount = isIncome ? Math.abs(amount) : -Math.abs(amount);

            Category selectedCategory = tracker.getCategory(categoryName);
            tracker.addTransaction(new Transaction(amount, new Date(), selectedCategory, description));

            updateUI();
            amountField.clear();
            descriptionField.clear();
        } catch (NumberFormatException ex) {
            showAlert("Error", "Invalid amount. Please enter a valid number.");
        }
    }

    private void handleAddCategory(TextField newCategoryField) {
        String categoryName = newCategoryField.getText().trim();

        if (categoryName.isEmpty()) {
            showAlert("Error", "Enter a category name.");
            return;
        }

        if (tracker.getCategory(categoryName) != null) {
            showAlert("Error", "Category already exists!");
            return;
        }

        tracker.addCategory(new Category(categoryName, "General")); // Ensure the constructor is valid
        categoryList.add(categoryName); // Update dropdown

        newCategoryField.clear();
    }

    private void updateUI() {
        balanceLabel.setText("Total Balance: $" + tracker.getTotalBalance());
        transactionList.clear();

        // Ensure `getTransactions()` exists in `FinanceTracker`
        List<Transaction> transactions = tracker.getTransactions();
        for (Transaction t : transactions) {
            // Improved display formatting
            String typeLabel = t.getAmount() > 0 ? "[Income]" : "[Expense]";
            String formattedTransaction = String.format("%s %s: %s%.2f",
                    typeLabel, t.getCategory().getName(),
                    t.getAmount() > 0 ? "+" : "-",
                    Math.abs(t.getAmount()));

            transactionList.add(formattedTransaction);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}