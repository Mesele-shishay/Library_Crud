package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Create an instance of the WelcomeScreen and show it
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
