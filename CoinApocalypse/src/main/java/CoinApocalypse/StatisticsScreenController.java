package CoinApocalypse;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StatisticsScreenController {
	@FXML
	private Button button_menu;
	
    @FXML
    private void handleButton(ActionEvent event) throws IOException {
    	if(event.getSource() == button_menu) {
    		App.setRoot("menu_screen");
    	}
    }
}
