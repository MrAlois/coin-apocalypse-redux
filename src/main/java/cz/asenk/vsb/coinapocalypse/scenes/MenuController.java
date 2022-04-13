package cz.asenk.vsb.coinapocalypse.scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cz.asenk.vsb.coinapocalypse.Application;
import cz.asenk.vsb.coinapocalypse.game.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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
    		Application.setRoot("game_screen");
    	}
    	
    	if(event.getSource() == button_upgrade) {
    		Application.setRoot("upgrade_screen");
    	}
    	
    	if(event.getSource() == button_about) {
    		Application.setRoot("about_screen");
    	}
    	
    	if(event.getSource() == button_statistics) {
    		Application.setRoot("statistics_screen");
    	}
    	
    	if(event.getSource() == button_instructions) {
    		Application.setRoot("instruction_screen");
    	}
    	
    	if(event.getSource() == button_exit) {
    		Application.exitApp();
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
