# Finance Tracker

## Overview
Finance Tracker is a JavaFX-based desktop application designed to help users manage their finances by categorizing transactions, tracking balances, and analyzing spending habits. Users can add transactions, create categories, and visualize their financial data.

### [Software Demo Video](https://youtu.be/mdiaQjjrseA)

## Features
- **Add and Manage Transactions**: Users can enter income and expense transactions with descriptions and associated categories.
- **Category Management**: Users can create and manage categories without duplicates, ensuring flexibility in financial tracking.
- **Balance Tracking**: Automatically calculates and displays the total balance, income, and expenses.
- **Interactive UI**: A JavaFX-based graphical user interface to facilitate easy financial management.
- **Efficient Data Handling**: Transactions and categories are processed dynamically for real-time updates.

## Technologies Used
- **Java 21** (Temurin)
- **JavaFX 21**
- **Maven** (Project Management & Dependency Handling)
- **IntelliJ IDEA** (Development Environment)

## Installation & Setup
### Prerequisites
Ensure you have the following installed:
1. **Java Development Kit (JDK 21)**
2. **JavaFX SDK 21**
3. **Maven** (for building and managing dependencies)

### Cloning the Repository
```bash
git clone https://github.com/JaredMalan7/Finance-Tracker.git
cd Finance-Tracker
```

### Configuring JavaFX
If JavaFX is not bundled with your JDK, set it up manually:
```bash
export PATH_TO_FX="/path/to/javafx-sdk/lib"
export MAVEN_OPTS="--module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml"
```

### Running the Application
Use Maven to compile and run the JavaFX application:
```bash
mvn clean javafx:run
```

## Project Structure
```
Finance-Tracker/
│── src/main/java/tracker/
│   ├── FinanceTrackerApp.java   # Main JavaFX Application
│   ├── FinanceTracker.java      # Core logic for managing transactions
│   ├── Category.java            # Represents financial categories
│   ├── Transaction.java         # Represents financial transactions
│── src/main/resources/          # (Future scope: UI FXML files, stylesheets, etc.)
│── pom.xml                      # Maven configuration
│── README.md                    # Project Documentation
```

## Usage Guide
1. **Launch the Application**
    - Run `mvn javafx:run` to start the Finance Tracker.
2. **Add a Transaction**
    - Enter the amount, choose a category, and provide a description.
    - Select whether it is an income or an expense.
3. **Manage Categories**
    - Create new categories dynamically.
    - Duplicate category names are not allowed.
4. **View Financial Overview**
    - The total balance, income, and expenses update in real-time.

## Future Enhancements
- **Persistence**: Save and load transactions and categories.
- **Graphical Insights**: Charts for income and expense distribution.
- **Export Data**: Generate reports in CSV or PDF format.

##  Useful Websites
Here are some resources that were helpful during development:
* [Java 21 Documentation](https://docs.oracle.com/en/java/javase/21/)
* [JavaFX Official Documentation](https://openjfx.io/)
* [Maven Official Guide](https://maven.apache.org/guides/)
* [Homebrew (Mac Package Manager)](https://brew.sh/)
* [IntelliJ IDEA Documentation](https://www.jetbrains.com/idea/resources/)
* [GitHub Documentation](https://docs.github.com/en/get-started)




