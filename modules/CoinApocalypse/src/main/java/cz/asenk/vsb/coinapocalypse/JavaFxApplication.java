package cz.asenk.vsb.coinapocalypse;

import cz.asenk.vsb.coinapocalypse.webclient.PingerScheduler;
import cz.asenk.vsb.coinapocalypse.webclient.ServerConnector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JavaFxApplication extends javafx.application.Application {
    private static Scene scene;
    private static Stage primaryStage;

    private ServerConnector connector;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.getIcons().add(new Image("file:src/main/resources/img/CoinApocalypseIco.png"));
        primaryStage.setTitle("Coin Apocalypse - v0.0.2");

        log.info("JavaFX JavaFxApplication is starting ..");

        scene = new Scene(loadFXML("menu_screen"), 1024, 800);
        scene.getStylesheets().add("file:src/main/resources/stylesheet.css");

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        ServerConnector.withUrl("http://localhost:8080");
        PingerScheduler.start();
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void exitApp() {
        PingerScheduler.stop();
        primaryStage.close();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFxApplication.class.getResource("/fxml/" + fxml + ".fxml"));
        log.debug("Changing screen controller to {}.", fxml);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}