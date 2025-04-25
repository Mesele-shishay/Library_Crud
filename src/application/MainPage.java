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
        root.setHgap(10);
        root.setVgap(10);
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

        // TableView
        TableView<Books> tableView = new TableView<>();
        TableColumn<Books, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Books, String> titleColumn = new TableColumn<>("Title");
        TableColumn<Books, String> authorColumn = new TableColumn<>("Author");
        TableColumn<Books, Integer> yearColumn = new TableColumn<>("Year");
        TableColumn<Books, Integer> pagesColumn = new TableColumn<>("Pages");
        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, yearColumn, pagesColumn);

        // Add components to grid
        root.add(idField, 0, 0);
        root.add(titleField, 1, 0);
        root.add(authorField, 0, 1);
        root.add(yearField, 1, 1);
        root.add(pagesField, 0, 2);
        root.add(insertButton, 1, 2);
        root.add(updateButton, 0, 3);
        root.add(deleteButton, 1, 3);
        root.add(tableView, 0, 4, 2, 1);

        // Column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        root.getColumnConstraints().addAll(col1, col2);

        // Scene and stage
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Library Dashboard");
        stage.show();

        // Controller setup
        MainController controller = new MainController();
        controller.setTextFields(idField, titleField, authorField, yearField, pagesField);
        controller.setTableView(tableView);

        // Button actions
        insertButton.setOnAction(event -> controller.insertButton());
        updateButton.setOnAction(event -> controller.updateButton());
        deleteButton.setOnAction(event -> controller.deleteButton());
    }
}
