package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterScreen {

    public void show(Stage stage) {
        // Create UI Components for Register Screen
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

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.getStyleClass().add("label");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.getStyleClass().add("password-field");

        // Create buttons
        Button registerBtn = new Button("Register");
        Button cancelBtn = new Button("Cancel");

        // Apply button styles from the CSS
        registerBtn.getStyleClass().add("button");
        cancelBtn.getStyleClass().add("button-cancel");

        // Add components to grid
        root.add(emailLabel, 0, 0);
        root.add(emailField, 1, 0);
        root.add(passwordLabel, 0, 1);
        root.add(passwordField, 1, 1);
        root.add(confirmPasswordLabel, 0, 2);
        root.add(confirmPasswordField, 1, 2);
        root.add(registerBtn, 1, 3);
        root.add(cancelBtn, 0, 3);

        // Set up button actions
        registerBtn.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // Check if passwords match
            if (password.equals(confirmPassword)) {
                registerAdmin(email, password, stage);
            } else {
                showAlert("Error", "Passwords do not match.");
            }
        });

        cancelBtn.setOnAction(e -> stage.close()); // Close the register screen

        // Set the scene and show
        Scene scene = new Scene(root, 400, 250);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Register Admin");
        stage.show();
    }

    // Method to register admin to the database
    private void registerAdmin(String email, String password, Stage stage) {
        String query = "INSERT INTO admins (email, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
             PreparedStatement pst = conn.prepareStatement(query)) {

            // Set values for the query
            pst.setString(1, email);
            pst.setString(2, password); // Password should be hashed in production!

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Success", "Admin registered successfully!");
                // Optionally, navigate to the login screen after successful registration
                new LoginScreen().show(stage); // Open Login screen after registration
            } else {
                showAlert("Error", "Failed to register admin.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while registering.");
        }
    }

    // Show alert method
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
