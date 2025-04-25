package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomeScreen {

    public void show(Stage stage) {
        VBox welcomePage = new VBox();
        welcomePage.setSpacing(20);
        welcomePage.setAlignment(javafx.geometry.Pos.CENTER);
        welcomePage.getStyleClass().add("root");

        Label welcomeLabel = new Label("📚 Welcome to the Library Management System");
        welcomeLabel.getStyleClass().add("welcome-label");

        Label descriptionLabel = new Label("Manage books, track availability, and organize your library easily.");
        descriptionLabel.getStyleClass().add("description-text");

        // Buttons
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Button developersButton = new Button("Developers");

        loginButton.getStyleClass().add("proceed-button");
        registerButton.getStyleClass().add("proceed-button");
        developersButton.getStyleClass().add("proceed-button");

        loginButton.setOnAction(e -> new LoginScreen().show(stage));
        registerButton.setOnAction(e -> {
            new RegisterScreen().show(stage); // Open Register Screen
        });

        developersButton.setOnAction(e -> {
            new DevelopersScreen().show(stage); // Open Developers Screen
        });

        welcomePage.getChildren().addAll(welcomeLabel, descriptionLabel, loginButton, registerButton, developersButton);

        Scene welcomeScene = new Scene(welcomePage, 600, 500);
        welcomeScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(welcomeScene);
        stage.setTitle("Welcome");
        stage.show();
    }
}
