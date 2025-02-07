package tracker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;

public class FinanceTrackerApp extends Application {
    private FinanceTracker tracker;
    private Label balanceLabel;
    private ListView<String> transactionListView;
    private ObservableList<String> transactionList;

    @Override
    public void start(Stage primaryStage) {
        tracker = new FinanceTracker();
        tracker.addCategory(new Category("Salary", "Income"));
        tracker.addCategory(new Category("Groceries", "Expense"));

        // Initialize UI
        VBox layout = createUI();

        // Scene setup
        Scene scene = new Scene(layout, 400, 400);
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

        // Input Fields
        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Salary", "Groceries");
        categoryComboBox.setPromptText("Select Category");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        // Add Transaction Button
        Button addTransactionButton = new Button("Add Transaction");
        addTransactionButton.setOnAction(e -> handleAddTransaction(amountField, categoryComboBox, descriptionField));

        // Layout setup
        VBox layout = new VBox(10, balanceLabel, transactionListView, amountField, categoryComboBox, descriptionField, addTransactionButton);
        layout.setPadding(new Insets(15));

        return layout;
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

            Category selectedCategory = tracker.getCategory(categoryName);
            tracker.addTransaction(new Transaction(amount, new Date(), selectedCategory, description));

            updateUI();
            amountField.clear();
            descriptionField.clear();
        } catch (NumberFormatException ex) {
            showAlert("Error", "Invalid amount. Please enter a valid number.");
        }
    }

    private void updateUI() {
        balanceLabel.setText("Total Balance: $" + tracker.getTotalBalance());
        transactionList.clear();

        // Ensure `getTransactions()` exists in `FinanceTracker`
        List<Transaction> transactions = tracker.getTransactions();
        for (Transaction t : transactions) {
            transactionList.add(t.toString());
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