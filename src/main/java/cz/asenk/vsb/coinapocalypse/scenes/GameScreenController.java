package cz.asenk.vsb.coinapocalypse.scenes;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import cz.asenk.vsb.coinapocalypse.JavaFxApplication;
import cz.asenk.vsb.coinapocalypse.game.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameScreenController implements Initializable{
	
	@FXML private Canvas fxmlCanvas;
	@FXML private Label fxmlLabelScore;
	@FXML private Label fxmlLabelCoins;
	
	private Game game;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LinkedList<KeyCode> keysPressed = new LinkedList<>();
		
		// Set key Events
		fxmlCanvas.setFocusTraversable(true);
		fxmlCanvas.setOnKeyPressed(e -> {
			if (!keysPressed.contains(e.getCode()))
				keysPressed.add(e.getCode());
		});
		
		fxmlCanvas.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));
		
		fxmlCanvas.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
			
			if(key.getCode() == KeyCode.ESCAPE) {
				try {
					game.exit();
					JavaFxApplication.setRoot("upgrade_screen");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		game = new Game(fxmlCanvas.getGraphicsContext2D(), keysPressed);
		game.setCoinLabel(fxmlLabelCoins);
		game.setScoreLabel(fxmlLabelScore);
		game.start();
	}
}