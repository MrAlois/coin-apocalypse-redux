package cz.asenk.vsb.coinapocalypse.scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cz.asenk.vsb.coinapocalypse.JavaFxApplication;
import cz.asenk.vsb.coinapocalypse.game.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MenuController implements Initializable{
	@FXML
	private Button fxmlButtonPlay;
	@FXML
	private Button fxmlButtonUpgrade;
	@FXML
	private Button fxmlButtonAbout;
	@FXML
	private Button fxmlButtonStatistics;
	@FXML
	private Button fxmlButtonInstructions;
	@FXML
	private Button fxmlButtonExit;
	
	@FXML
	VBox fxmlVboxMain;
		
    @FXML
    private void handleButtons(ActionEvent event) throws IOException {
    	Sound.click.play();
    	
    	if(event.getSource() == fxmlButtonPlay) {
    		JavaFxApplication.setRoot("game_screen");
    	}
    	
    	if(event.getSource() == fxmlButtonUpgrade) {
    		JavaFxApplication.setRoot("upgrade_screen");
    	}
    	
    	if(event.getSource() == fxmlButtonAbout) {
    		JavaFxApplication.setRoot("about_screen");
    	}
    	
    	if(event.getSource() == fxmlButtonStatistics) {
    		JavaFxApplication.setRoot("statistics_screen");
    	}
    	
    	if(event.getSource() == fxmlButtonInstructions) {
    		JavaFxApplication.setRoot("instruction_screen");
    	}
    	
    	if(event.getSource() == fxmlButtonExit) {
    		JavaFxApplication.exitApp();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundImage bi = new BackgroundImage(new Image("file:src/main/resources/img/menu/MenuBackground.png", true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		fxmlVboxMain.setBackground(new Background(bi));
		
		fxmlButtonPlay.getStyleClass().add("myButton");
	}
}
