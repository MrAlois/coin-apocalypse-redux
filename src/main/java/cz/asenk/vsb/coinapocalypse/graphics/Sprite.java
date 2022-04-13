package cz.asenk.vsb.coinapocalypse.graphics;

import javafx.scene.image.Image;

public class Sprite{
	private int frame = 0;
	private int row, maxCols;
	private int animStartTick = 0;
	private Image spriteBook;
	
	public Sprite(int row, int maxCols, Image spriteBook) {
		this.row = row;
		this.maxCols = maxCols;
		this.spriteBook = spriteBook;
	}
	
	public void animate(double delay, int tick) {
		if(animStartTick == 0) animStartTick = tick;
		
		if(tick - animStartTick > delay) {
			animStartTick = tick;
			
			if(frame + 1 < maxCols) 
				frame++;
			else
				frame = 0;
			
			animStartTick = 0;
		}
	}
	
	public double getSpriteBookOffsetX() {
		return frame * Art.DEF_TILE_WIDTH;
	}
	
	public double getSpriteBookOffsetY() {
		return row * Art.DEF_TILE_WIDTH;
	}
	
	public Image getSpriteBook() { return spriteBook; }
}
