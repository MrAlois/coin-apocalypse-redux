package CoinApocalypse.scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CoinApocalypse.App;
import CoinApocalypse.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable{
	@FXML
	private Button button_play, button_upgrade, button_about, button_statistics, button_instructions, button_exit;
	
	@FXML
	VBox vbox_main;
	
	public MenuController(){
		
		
	}
		
    @FXML
    private void handleButtons(ActionEvent event) throws IOException {
    	Sound.click.play();
    	
    	if(event.getSource() == button_play) {
    		App.setRoot("game_screen");
    	}
    	
    	if(event.getSource() == button_upgrade) {
    		App.setRoot("upgrade_screen");
    	}
    	
    	if(event.getSource() == button_about) {
    		App.setRoot("about_screen");
    	}
    	
    	if(event.getSource() == button_statistics) {
    		App.setRoot("statistics_screen");
    	}
    	
    	if(event.getSource() == button_instructions) {
    		App.setRoot("instruction_screen");
    	}
    	
    	if(event.getSource() == button_exit) {
    		App.exitApp();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundImage bi = new BackgroundImage(new Image("file:src/main/resources/img/menu/MenuBackground.png", true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		vbox_main.setBackground(new Background(bi));
		
		button_play.getStyleClass().add("myButton");
	}
}
