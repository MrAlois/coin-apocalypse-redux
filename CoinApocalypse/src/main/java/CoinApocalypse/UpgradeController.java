package CoinApocalypse;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UpgradeController {
	@FXML
	private Button button_play, button_menu, button_back, button_next;
	
    @FXML
    private void handleButton(ActionEvent event) throws IOException {
    	if(event.getSource() == button_play) {
    		App.setRoot("game_screen");
    	}
    	
    	if(event.getSource() == button_menu) {
    		App.setRoot("menu_screen");
    	}
    	
    	if(event.getSource() == button_back) {
    		
    	}
    	
    	if(event.getSource() == button_next) {
    		
    	}
    }
}
