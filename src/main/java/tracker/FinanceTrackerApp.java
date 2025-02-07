package tracker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinanceTrackerApp extends Application {
    private FinanceTracker tracker;

    @Override
    public void start(Stage primaryStage) {
        tracker = new FinanceTracker();
        tracker.addCategory(new Category("Salary", "Income"));
        tracker.addCategory(new Category("Groceries", "Expense"));
        tracker.addTransaction(new Transaction(2000, new java.util.Date(), tracker.getCategory("Salary"), "Monthly paycheck"));
        tracker.addTransaction(new Transaction(-150, new java.util.Date(), tracker.getCategory("Groceries"), "Groceries"));

        // Create UI components
        Label balanceLabel = new Label("Total Balance: $" + tracker.getTotalBalance());
        Button addTransactionButton = new Button("Add $100 Salary");
        addTransactionButton.setOnAction(e -> {
            tracker.addTransaction(new Transaction(100, new java.util.Date(), tracker.getCategory("Salary"), "Bonus"));
            balanceLabel.setText("Total Balance: $" + tracker.getTotalBalance());
        });

        // Layout setup
        VBox layout = new VBox(10);
        layout.getChildren().addAll(balanceLabel, addTransactionButton);

        // Scene setup
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Finance Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX UI
    }
}