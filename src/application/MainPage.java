package application;

import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import library.Books;

public class MainPage {

    public void createDashboard(Stage stage) {
        // Create UI components for the library page
        GridPane root = new GridPane();
        root.setHgap(20);  // Set horizontal gap between components
        root.setVgap(10);  // Vertical gap remains the same
        root.setStyle("-fx-padding: 20;");

        // Input fields
        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");
        TextField yearField = new TextField();
        yearField.setPromptText("Year");
        TextField pagesField = new TextField();
        pagesField.setPromptText("Pages");

        // Buttons
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button logoutButton = new Button("Logout");  // Logout button

        // TableView
        TableView<Books> tableView = new TableView<>();
        TableColumn<Books, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Books, String> titleColumn = new TableColumn<>("Title");
        TableColumn<Books, String> authorColumn = new TableColumn<>("Author");
        TableColumn<Books, Integer> yearColumn = new TableColumn<>("Year");
        TableColumn<Books, Integer> pagesColumn = new TableColumn<>("Pages");
        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, yearColumn, pagesColumn);

        // Add input fields to grid
        root.add(idField, 0, 0);
        root.add(titleField, 1, 0);
        root.add(authorField, 0, 1);
        root.add(yearField, 1, 1);
        root.add(pagesField, 0, 2);

        // Add buttons to a new row (row 3)
        root.add(insertButton, 0, 3);
        root.add(updateButton, 1, 3);
        root.add(deleteButton, 2, 3);

        // Add the TableView below the buttons (row 4)
        root.add(tableView, 0, 4, 3, 1);  // Spanning the entire width

        // Add the logout button at the bottom (row 5)
        root.add(logoutButton, 0, 5, 3, 1);  // Spanning the entire width

        // Column constraints to ensure proper width
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);  // 30% for the first column
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(30);  // 30% for the second column
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(40);  // 40% for the third column

        root.getColumnConstraints().addAll(col1, col2, col3);

        // Scene and stage setup
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Library Dashboard");

        // Controller setup
        MainController controller = new MainController();
        controller.setTextFields(idField, titleField, authorField, yearField, pagesField);
        controller.setTableView(tableView);

        // Button actions
        insertButton.setOnAction(event -> controller.insertButton());
        updateButton.setOnAction(event -> controller.updateButton());
        deleteButton.setOnAction(event -> controller.deleteButton());

        // Logout button action
        logoutButton.setOnAction(event -> {
            // Clear session or authentication details here if needed
            LoginScreen loginScreen = new LoginScreen();  // Return to Login screen
            loginScreen.show(stage);
        });

        // Load data from DB into the table (this would be done in the controller)
        controller.loadData(); // Example: controller.loadData(tableView);

        stage.show();  // Show the updated stage with the dashboard
    }
}
