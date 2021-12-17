package CoinApocalypse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameScreenController implements Initializable{
	
	@FXML
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	public GameScreenController() {

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Set key Events
		canvas.setFocusTraversable(true);	
		canvas.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode() == KeyCode.ESCAPE) {
				try {
					
					App.setRoot("menu_screen");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(new Image("file:src/main/resources/img/menu/MenuPlaceholder.png"), 0, -50);
	}
}