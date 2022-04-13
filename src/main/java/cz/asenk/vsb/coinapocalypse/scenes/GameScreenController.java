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
	
	@FXML
	private Canvas canvas;
	
	@FXML
	private Label label_score;
	
	@FXML
	private Label label_coins;
	
	private Game game;
	
	public GameScreenController() {	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LinkedList<KeyCode> keysPressed = new LinkedList<KeyCode>();
		
		// Set key Events
		canvas.setFocusTraversable(true);	
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if(!keysPressed.contains(e.getCode()))  
					keysPressed.add(e.getCode());
			}
		});
		
		canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				keysPressed.remove(e.getCode());
			}
		});
		
		canvas.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			
			if(key.getCode() == KeyCode.ESCAPE) {
				try {
					game.exit();
					JavaFxApplication.setRoot("upgrade_screen");
				} catch (IOException e) {;
					e.printStackTrace();
				}
			}
		});
		
		game = new Game(canvas.getGraphicsContext2D(), keysPressed);
		game.setCoinLabel(label_coins);
		game.setScoreLabel(label_score);
		game.start();
	}
}