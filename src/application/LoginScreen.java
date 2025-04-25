package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginScreen {

    public void show(Stage stage) {
        // Create UI Components for Login Screen
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(10);
        root.setVgap(10);

        // Create labels and fields
        Label emailLabel = new Label("Email:");
        emailLabel.getStyleClass().add("label");
        TextField emailField = new TextField();
        emailField.getStyleClass().add("text-field");

        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("label");
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("password-field");

        // Create buttons
        Button loginBtn = new Button("Login");
        Button cancelBtn = new Button("Cancel");
        Button backBtn = new Button("Back");

        // Apply button styles from the CSS
        loginBtn.getStyleClass().add("button");
        cancelBtn.getStyleClass().add("button-cancel");
        backBtn.getStyleClass().add("button");

        // Add components to grid
        root.add(emailLabel, 0, 0);
        root.add(emailField, 1, 0);
        root.add(passwordLabel, 0, 1);
        root.add(passwordField, 1, 1);
        root.add(loginBtn, 1, 2);
        root.add(cancelBtn, 0, 2);
        root.add(backBtn, 0, 3, 2, 1); // Add Back button in a new row spanning 2 columns

        // Set up button actions
        loginBtn.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (validateCredentials(email, password)) {
                showAlert("Success", "Login successful!");

                // Show dashboard directly
                MainPage mainPage = new MainPage();
                mainPage.createDashboard(stage);
            } else {
                showAlert("Error", "Incorrect email or password.");
            }
        });

        cancelBtn.setOnAction(e -> stage.close());

        backBtn.setOnAction(e -> {
            new WelcomeScreen().show(stage); // Navigate back to the Welcome Screen
        });

        // Set the scene and show
        Scene scene = new Scene(root, 400, 250);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    // Validate credentials from database
    private boolean validateCredentials(String email, String password) {
        String query = "SELECT * FROM admins WHERE email = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, email);
            pst.setString(2, password); // In production, use hashed passwords!

            ResultSet rs = pst.executeQuery();
            return rs.next(); // Return true if user exists
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Helper method for alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
