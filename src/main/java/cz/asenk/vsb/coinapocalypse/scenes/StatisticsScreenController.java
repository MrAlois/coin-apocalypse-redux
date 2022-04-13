package cz.asenk.vsb.coinapocalypse.scenes;

import java.io.IOException;

import cz.asenk.vsb.coinapocalypse.JavaFxApplication;
import cz.asenk.vsb.coinapocalypse.game.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StatisticsScreenController {
	@FXML
	private Button fxmlButtonMenu;
	
    @FXML
    private void handleButton(ActionEvent event) throws IOException {
    	Sound.click.play();
    	
    	if(event.getSource() == fxmlButtonMenu) {
    		JavaFxApplication.setRoot("menu_screen");
    	}
    }
}
