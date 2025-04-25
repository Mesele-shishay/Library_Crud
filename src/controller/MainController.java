package controller;

import library.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {

    private TextField idField;
    private TextField titleField;
    private TextField authorField;
    private TextField yearField;
    private TextField pagesField;
    private TableView<Books> tableView;

    public void setTextFields(TextField idField, TextField titleField, TextField authorField, TextField yearField, TextField pagesField) {
        this.idField = idField;
        this.titleField = titleField;
        this.authorField = authorField;
        this.yearField = yearField;
        this.pagesField = pagesField;
    }

    public void setTableView(TableView<Books> tableView) {
        this.tableView = tableView;
    }

    public void insertButton() {
        String query = "INSERT INTO books (Title, Author, Year, Pages) VALUES ('" + titleField.getText() + "','" + authorField.getText() + "'," + yearField.getText() + "," + pagesField.getText() + ")";
        executeQuery(query);
        loadData();  // Load the data again after insertion
    }

    public void updateButton() {
        String query = "UPDATE books SET Title='" + titleField.getText() + "', Author='" + authorField.getText() + "', Year=" + yearField.getText() + ", Pages=" + pagesField.getText() + " WHERE ID=" + idField.getText();
        executeQuery(query);
        loadData();  // Load the data again after update
    }

    public void deleteButton() {
        String query = "DELETE FROM books WHERE ID=" + idField.getText();
        executeQuery(query);
        loadData();  // Load the data again after deletion
    }

    private void executeQuery(String query) {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadData() {
        ObservableList<Books> booksList = FXCollections.observableArrayList();
        String query = "SELECT * FROM books";
        try (Connection connection = getConnection(); Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Books book = new Books(rs.getInt("ID"), rs.getString("Title"), rs.getString("Author"), rs.getInt("Year"), rs.getInt("Pages"));
                booksList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Populate the TableView with data
        tableView.getColumns().clear(); // Clear existing columns
        TableColumn<Books, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Books, String> titleColumn = new TableColumn<>("Title");
        TableColumn<Books, String> authorColumn = new TableColumn<>("Author");
        TableColumn<Books, Integer> yearColumn = new TableColumn<>("Year");
        TableColumn<Books, Integer> pagesColumn = new TableColumn<>("Pages");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));

        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, yearColumn, pagesColumn);
        tableView.setItems(booksList);  // Set the items (books data) into the TableView
    }
}
