package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DevelopersScreen {

    public void show(Stage stage) {
        VBox developersPage = new VBox();
        developersPage.setSpacing(20);
        developersPage.setAlignment(javafx.geometry.Pos.CENTER);
        developersPage.getStyleClass().add("root");

        Label titleLabel = new Label("👨‍💻 Meet the Developers");
        titleLabel.getStyleClass().add("title-label");

        // List of developers (ID, Name, Section, Department)
        Label developer1 = new Label("ID: UGR/179052/12 | Name: Henok Mekonnen | Section: 2 | Department: Software Engineering");
        developer1.getStyleClass().add("developer-info");

        Label developer2 = new Label("ID: UGR/178838/12 | Name: Fillimon G/Tsadik | Section: 2 | Department: Software Engineering");
        developer2.getStyleClass().add("developer-info");

        Label developer3 = new Label("ID: UGR/170168/12 | Name: Brhina Wubet | Section: 2 | Department: Software Engineering");
        developer3.getStyleClass().add("developer-info");

        Label developer4 = new Label("ID: UGR/178259/12 | Name: Mesele Shishay | Section: 2 | Department: Software Engineering");
        developer4.getStyleClass().add("developer-info");

        Label developer5 = new Label("ID: EITM/TUR181590 | Name: Mebrahtom G/Hiwot | Section: 2 | Department: Software Engineering");
        developer5.getStyleClass().add("developer-info");

        // Back button to return to the Welcome Screen
        Button backButton = new Button("Back to Welcome");
        backButton.getStyleClass().add("back-button");

        // Action for Back button
        backButton.setOnAction(e -> {
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            welcomeScreen.show(stage);  // Open the Welcome Screen
        });

        // Add all developer labels to the VBox
        developersPage.getChildren().addAll(
                titleLabel,
                developer1,
                developer2,
                developer3,
                developer4,
                developer5,  // Adding all developer labels to the layout
                backButton
        );

        Scene developersScene = new Scene(developersPage, 600, 500);
        developersScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(developersScene);
        stage.setTitle("Developers");
        stage.show();
    }
}
