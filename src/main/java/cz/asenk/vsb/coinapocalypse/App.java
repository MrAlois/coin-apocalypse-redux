package cz.asenk.vsb.coinapocalypse;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
    	primaryStage = stage;
        primaryStage.getIcons().add(new Image("file:src/main/resources/img/CoinApocalypseIco.png"));
        primaryStage.setTitle("Coin Apocalypse - v0.0.2");
        
        scene = new Scene(loadFXML("menu_screen"), 1024, 800);
        scene.getStylesheets().add("file:src/main/resources/stylesheet.css");
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public static void exitApp() {
    	primaryStage.close();
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }   
}