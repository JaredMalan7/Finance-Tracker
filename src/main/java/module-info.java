module tracker {
    requires javafx.controls;
    requires javafx.fxml;

    opens tracker to javafx.fxml;
    exports tracker;
}