package cz.asenk.vsb.coinapocalypse.scenes;

import cz.asenk.vsb.coinapocalypse.JavaFxApplication;
import cz.asenk.vsb.coinapocalypse.game.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class InstructionScreenController implements Initializable{
	@FXML
	private Button fxmlButtonMenu;
	
	@FXML
	private ImageView imageview;
	
    @FXML
    private void handleButton(ActionEvent event) throws IOException {
    	Sound.click.play();
    	
    	if(event.getSource() == fxmlButtonMenu) {
    		JavaFxApplication.setRoot("menu_screen");
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imageview.setImage(new Image("file:src/main/resources/img/menu/Instructions.png"));
	}
}
