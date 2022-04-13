package cz.asenk.vsb.coinapocalypse.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import cz.asenk.vsb.coinapocalypse.Application;
import cz.asenk.vsb.coinapocalypse.game.entities.*;
import cz.asenk.vsb.coinapocalypse.graphics.Art;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Game {
	public static final int CANVAS_WIDTH = 1024;
	public static final int CANVAS_HEIGHT = 750 ;
	
	private AnimationTimer animTimer;
	private GraphicsContext gc;
	private boolean isRunning = true;
	private boolean isPaused = false;
	
	private Player player;
	private ArrayList<Cloud> cloudList = new ArrayList<>();
	private ArrayList<Coin> coinList = new ArrayList<>();
	private ArrayList<Meteor> meteorList = new ArrayList<>();
	private ArrayList<Collisionable> collisionables = new ArrayList<>();
	private ArrayList<Drawable> drawables = new ArrayList<>();
	
	private LinkedList<KeyCode> keysPressed;
	
	private Label label_score, label_coins;
	
	private int score = 0, coins = 0, coinMultiplier = 1, tick = 0;
	private double gameDifficulty = 1; 
	private Art artbook;
	
	private long startTime;
	
	public Game(GraphicsContext gc, LinkedList<KeyCode> keysPressed) {
		this.gc = gc;
		this.keysPressed = keysPressed;
	}
	
	public void start() {
		this.startTime = System.currentTimeMillis();
		
		animTimer = new AnimationTimer() {
			private Long previous;
			
			@Override
			public void handle(long now) {
				if (previous == null) {
					previous = now;
				} else if(isRunning){
					double deltaT = (now - previous)/1e9;					
				
					if(tick%2 == 0) {
						draw();
						update(deltaT);
					}
					
					previous = now;
					tick++;
				} else {
					loseGame();
				}
			}
		};
		animTimer.start();

		artbook = new Art();
		
		// Construct entities 
		player = new Player(artbook);
		player.setKeyList(keysPressed);
		
		for(int i = 0; i != 3; i++)
			cloudList.add(new Cloud(artbook));
		
		for(int i = 0; i != 6; i++)
			coinList.add(new Coin(artbook));
		
		for(int i = 0; i != 3; i++)
			meteorList.add(new Meteor(artbook, gameDifficulty));
		
		// Prepare the lists
		collisionables.addAll(coinList);
		collisionables.addAll(meteorList);
		
		drawables.addAll(cloudList);
		drawables.add(player);
		drawables.addAll(coinList);
		drawables.addAll(meteorList);
	}
	
	public void exit() {
		animTimer.stop();
	}
	
	public void pause() {
		if(isPaused) {
			animTimer.start();
			isPaused = false;
		} else {
			animTimer.stop();
			isPaused = true;
		}
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public void setScoreLabel(Label label) {
		this.label_score = label;
	}
	
	public void setCoinLabel(Label label) {
		this.label_coins = label;
	}
		
	private void draw() {		
		// Background
		gc.drawImage(artbook.game_background, 0, 0);
				
		for(Drawable d : drawables) {
			d.draw(gc);
		}
	}
	
	private void update(double deltaT) {
		// Score
		label_score.setText(String.format("Score: %d", score));
		label_coins.setText(String.format("Coins(x%d): %d", coinMultiplier, coins));
		
		score = (int) (tick * coinMultiplier);
		
		//TODO Collections update
		player.update(deltaT, tick);
		
		for(Cloud c : cloudList)
			c.update(deltaT, tick);
		
		for(Coin c : coinList)
			c.update(deltaT, tick);
		
		for(Meteor c : meteorList)
			c.update(deltaT, tick);
		
		checkPlayerIntersection(); 
		checkGameDifficulty();
	}	
	
	private void checkPlayerIntersection() {	
		for(Collisionable c : collisionables) {
			Collisionable i = c.intersects(player.getBoundary());
			
			if(i != null) {
				switch(i.getName()) {
					case "coin":
						coins += 1 * coinMultiplier;	// Sound event and relocating of the coin is done in inside the class
						break;
						
					case "meteor":
						isRunning = false;
						break;
				}
			}		
		}
	}
	
	private void checkGameDifficulty() {
		boolean diffChange = false;
		long currentGameTime = System.currentTimeMillis() - startTime;
	
		if(currentGameTime > 5000 && gameDifficulty == 1) { 
			gameDifficulty = 2; coinMultiplier *= gameDifficulty; diffChange = true;
		}
		
		if(currentGameTime > 10000 && gameDifficulty == 2){  
			gameDifficulty = 3; coinMultiplier *= gameDifficulty; diffChange = true;
		}
		
		if(currentGameTime > 15000 && gameDifficulty == 3){
			gameDifficulty = 4; coinMultiplier *= gameDifficulty; diffChange = true;
		} 
		
		if(currentGameTime > 30000 && gameDifficulty == 4){ 
			gameDifficulty = 5; coinMultiplier *= gameDifficulty; diffChange = true;
		}
		
		if(diffChange) {
			for(Meteor m : meteorList) {
				m.updateDifficulty(gameDifficulty);
			}
			diffChange = false;
		}
	}
	
	private void loseGame() {
		
		// Death animation
		for(double x = 0; x < CANVAS_WIDTH; x += 0.01 ) {			
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 100, 50, 100);
			gc.restore();
		}
		
		exit();
		
		//TODO Insert gamedev
		//TODO GameOver screen
		
		try {
			Thread.sleep(1500);
			Application.setRoot("upgrade_screen");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
