package CoinApocalypse.game.entities;


import CoinApocalypse.Art;
import CoinApocalypse.Sound;
import CoinApocalypse.game.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;


public class Meteor extends Entity implements Collisionable{
	private Art art;
	
	private double difficulty;
	
	public Meteor(Art art, double difficulty) {
		this.art = art;
		this.width = Art.DEF_TILE_WIDTH;
		this.height= Art.DEF_TILE_WIDTH;
		
		this.difficulty = difficulty;
		this.generateCoordinates();
	}
	
	public void updateDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	
	private void generateCoordinates() {
		double max = 4 + (difficulty * 4);
		double min = 2 + (difficulty * 3);

		this.velocityY = Math.random() * (max - min) + min;
		
		this.positionX = Math.random() * Game.CANVAS_WIDTH ;
		this.positionY = Math.random() * -Game.CANVAS_HEIGHT * 0.8;
	}

	@Override
	public void update(double deltaT, int tick) {
		if(positionY + velocityY > Game.CANVAS_HEIGHT)
			generateCoordinates(); 
			
		art.meteor.animate(30, tick);
		positionY += velocityY;
	}

	@Override
	public void draw(GraphicsContext gc) {    
		gc.drawImage(art.meteor.getSpriteBook(), 
				 	 art.meteor.getSpriteBookOffsetX(), art.meteor.getSpriteBookOffsetY(), 
				 	 Art.DEF_TILE_WIDTH, Art.DEF_TILE_WIDTH,
				 	 positionX, positionY, 
				 	 width, height);
	}

	@Override
	public Collisionable intersects(Rectangle2D r) {
		if(r.intersects(new Rectangle2D(this.positionX, this.positionY, this.width, this.height))) {
			Sound.hurt.play();
			return this;
		}
			
		return null;
	}

	@Override
	public String getName() {
		return "meteor";
	}
}

