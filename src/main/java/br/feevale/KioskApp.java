package br.feevale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KioskApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/br/feevale/main.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Kiosk App");
        stage.setScene(scene);
        stage.show();
    }
}
