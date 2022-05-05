package cz.asenk.vsb.coinapocalypse.game.entities;

import cz.asenk.vsb.coinapocalypse.game.Game;
import cz.asenk.vsb.coinapocalypse.graphics.Art;
import javafx.scene.canvas.GraphicsContext;

public class Cloud extends Entity{
	private Art art;
	
	public Cloud(Art art) {
		this.art = art;
		double scaleFactor = Math.random() * 0.8 + 0.3;
		this.width = Art.DEF_TILE_WIDTH * 4 * scaleFactor ;
		this.height= Art.DEF_TILE_WIDTH * 2.5 * scaleFactor;
		
		this.generateCoordinates();
		
		this.velocityX = Math.random() * (1 - 0.1) + 0.1;
	}
	
	private void generateCoordinates() {
		this.positionX = Math.random() * Game.CANVAS_WIDTH;
		this.positionY = Math.random() * (Game.CANVAS_HEIGHT * 0.4);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
	    gc.drawImage(art.cloud.getSpriteBook(), 
	    			art.cloud.getSpriteBookOffsetX(), art.cloud.getSpriteBookOffsetY(), 
   		  	 		Art.DEF_TILE_WIDTH, Art.DEF_TILE_WIDTH,
   		  	 		positionX, positionY, 
   		  	 		width, height);
	}

	@Override
	public void update(double deltaT, int tick) {
		if(positionX > Game.CANVAS_WIDTH) {
			generateCoordinates();
			positionX = -width;
		}
		
		positionX += velocityX;
	}
}
