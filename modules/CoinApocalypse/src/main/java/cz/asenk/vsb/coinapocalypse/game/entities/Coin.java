package cz.asenk.vsb.coinapocalypse.game.entities;


import cz.asenk.vsb.coinapocalypse.game.Game;
import cz.asenk.vsb.coinapocalypse.game.Sound;
import cz.asenk.vsb.coinapocalypse.graphics.Art;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Coin extends Entity implements Collisionable {
	private final Art art;
	
	public Coin(Art art) {
		this.art = art;
		this.width = Art.DEF_TILE_WIDTH;
		this.height= Art.DEF_TILE_WIDTH;
		
		this.generateCoordinates();

		int minVelocity = 4;
		int maxVelocity = 6;
		this.velocityY = Math.random() * (maxVelocity - minVelocity) + minVelocity;
	}
		
	private void generateCoordinates() {
		this.positionX = Math.random() * Game.CANVAS_WIDTH ;
		this.positionY = Math.random() * -Game.CANVAS_HEIGHT * 0.8;
	}

	@Override
	public void update(double deltaT, int tick) {
		if(positionY + velocityY > Game.CANVAS_HEIGHT)
			generateCoordinates(); 
		
		art.coin.animate(10, tick);
		positionY += velocityY;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(art.coin.getSpriteBook(), 
					 art.coin.getSpriteBookOffsetX(), art.coin.getSpriteBookOffsetY(), 
					 Art.DEF_TILE_WIDTH, Art.DEF_TILE_WIDTH,
					 positionX, positionY, 
					 width, height);
	}

	@Override
	public Collisionable intersects(Rectangle2D r) {
		if(r.intersects(new Rectangle2D(this.positionX, this.positionY, this.width, this.height))) {
			Sound.coin.play();
			generateCoordinates();
			return this;
		}
		
		return null;
	}

	@Override
	public String getName() {
		return "coin";
	}
}