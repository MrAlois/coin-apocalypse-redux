package CoinApocalypse.scenes;

import java.io.IOException;

import CoinApocalypse.App;
import CoinApocalypse.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StatisticsScreenController {
	@FXML
	private Button button_menu;
	
    @FXML
    private void handleButton(ActionEvent event) throws IOException {
    	Sound.click.play();
    	
    	if(event.getSource() == button_menu) {
    		App.setRoot("menu_screen");
    	}
    }
}
