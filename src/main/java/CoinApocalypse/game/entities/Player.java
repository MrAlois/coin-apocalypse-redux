package CoinApocalypse.game.entities;


import java.util.List;

import CoinApocalypse.graphics.Art;
import CoinApocalypse.graphics.Sprite;
import CoinApocalypse.game.Sound;
import CoinApocalypse.game.Game;
import CoinApocalypse.game.PlayerState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class Player extends Entity {
	public static final int PLAYER_BASELINE = 620;
	public static final double PLAYER_ANIMATION_TIME = 30;
	public static final double PLAYER_MAX_JUMP = 230;
	public static final double PLAYER_INITIAL_JUMP_STRENGTH = 50;
	//public static final double PLAYER_JUMP_STEP = 2;
	
	public static final int PLAYER_DEF_WIDTH = 64;
	public static final int PLAYER_DEF_HEIGHT = 64;
	
	private PlayerState state;
	private Art art;
	private boolean isJumping = false;
	private boolean isAtJumpMaximum = false;;
	private List<KeyCode> keysPressed;
	
	private Sprite playerImage;
	
	public Player(Art art) {
		this.art = art;
		this.width = PLAYER_DEF_WIDTH;
		this.height = PLAYER_DEF_HEIGHT;
		this.positionX = 500;
		this.positionY = PLAYER_BASELINE;
		
		this.playerImage = art.player_right;
		this.state = PlayerState.STOPPED;
	}
	
	public void setKeyList(List<KeyCode> kPrs) {
		keysPressed = kPrs;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}
	
	public PlayerState getState() {
		return state;
	}
	
	@Override
	public void update(double deltaT, int tick) {		
		// User IO (PRESSED_KEY && MAX_VELOCITY)
		if(keysPressed.contains(KeyCode.LEFT) && !keysPressed.contains(KeyCode.RIGHT) && velocityX > -10) {
			addVelocity(-1, 0);
		} 
		
		if(keysPressed.contains(KeyCode.RIGHT) && !keysPressed.contains(KeyCode.LEFT) && velocityX < 10) {
			addVelocity(1, 0);
		} 
		
		if(keysPressed.contains(KeyCode.UP) && positionY == Player.PLAYER_BASELINE) {
			setVelocity(velocityX, -PLAYER_INITIAL_JUMP_STRENGTH);
			isJumping = true;
			Sound.jump.play();
		}
		
		if(!(keysPressed.contains(KeyCode.RIGHT) || keysPressed.contains(KeyCode.LEFT)))
			setVelocity(velocityX/2, velocityY);
			
		// Check player current situation & change animation accordingly
		if(velocityX < -0.1) { 
			setState(PlayerState.MOVING_LEFT);
			playerImage = art.player_left;
			playerImage.animate(10, tick);
		}
		    			
		if(velocityX > 0.1){
			setState(PlayerState.MOVING_RIGHT);
			playerImage = art.player_right;
			playerImage.animate(10, tick);
		}
		
		if(velocityY != 0) setState(PlayerState.JUMPING); else
		if(velocityX < 0.3 && getVelocityX() > -0.3) setState(PlayerState.STOPPED);
		
		// Jumping logic
		if(isJumping) {
			if(positionY + velocityY > PLAYER_BASELINE) {
				setVelocity(velocityX, 0);
				setPosition(positionX, PLAYER_BASELINE);
				isJumping = false;
				isAtJumpMaximum = false;
			}
			
			if(positionY < PLAYER_MAX_JUMP) {
				isAtJumpMaximum = true;
				setVelocity(velocityX, 2);
			}
					
			if(!isAtJumpMaximum)
				addVelocity(0, velocityY*0.8);
			else
				addVelocity(0, velocityY*1.1);
			
			setVelocity(velocityX, velocityY/2);
		}
		
		// Floating point corrections
		if(velocityY < 0.01 && velocityY > -0.01) velocityY = 0;
		
		// Positon correction (canvas borders) AND position update
		if(positionX + velocityX < 0  )
			setPosition(0, positionY);
		else if(positionX + PLAYER_DEF_WIDTH + velocityX > Game.CANVAS_WIDTH)
			setPosition(Game.CANVAS_WIDTH - PLAYER_DEF_WIDTH, positionY);
		else
			positionX += velocityX;
		
		positionY += velocityY;
	}
				
	@Override
	public void draw(GraphicsContext gc) {	    
	    gc.drawImage(playerImage.getSpriteBook(), 
	    		  	 playerImage.getSpriteBookOffsetX(), playerImage.getSpriteBookOffsetY(), 
	    		  	 Art.DEF_TILE_WIDTH, Art.DEF_TILE_WIDTH,
	    		  	 positionX, positionY, 
	    		  	 width, height);
	}
}