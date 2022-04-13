package cz.asenk.vsb.coinapocalypse.scenes;

import java.io.IOException;

import cz.asenk.vsb.coinapocalypse.JavaFxApplication;
import cz.asenk.vsb.coinapocalypse.game.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UpgradeController {
	@FXML
	private Button fxmlButtonPlay;
	@FXML
	private Button fxmlButtonMenu;
	@FXML
	private Button fxmlButtonBack;
	@FXML
	private Button fxmlButtonNext;
	
    @FXML
    private void handleButton(ActionEvent event) throws IOException {
    	Sound.click.play();
    	
    	if(event.getSource() == fxmlButtonPlay) {
    		JavaFxApplication.setRoot("game_screen");
    	}
    	
    	if(event.getSource() == fxmlButtonMenu) {
    		JavaFxApplication.setRoot("menu_screen");
    	}
    }
}
