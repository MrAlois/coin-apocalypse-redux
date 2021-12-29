package CoinApocalypse;

import javafx.scene.image.Image;

// This is an example of a class, that is not static, but instanced - which, in this case, is rendundant. (School assigment.)

@SuppressWarnings("exports")
public class Art {
	public static final int DEF_TILE_WIDTH = 32;
	
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
			return frame * DEF_TILE_WIDTH;
		}
		
		public double getSpriteBookOffsetY() {
			return row * DEF_TILE_WIDTH;
		}
		
		public Image getSpriteBook() { return spriteBook; }
	}
	
	private final Image spriteBook = new Image("file:src/main/resources/img/game/SpriteBook.png");
	
	public final Image game_background = new Image("file:src/main/resources/img/game/Background.png");
	public final Image menu_about = new Image("file:src/main/resources/img/menu/About.png");
	public final Image menu_background = new Image("file:src/main/resources/img/menu/MenuBackground.png");
	public final Image button_unpressed = new Image("file:src/main/resources/img/menu/Button.png");
	public final Image button_pressed = new Image("file:src/main/resources/img/menu/PressedButton.png");
	public final Image menu_instructions = new Image("file:src/main/resources/img/menu/Instructions.png");
	
	public final Sprite player_left;
	public final Sprite player_right;
	public final Sprite coin;
	public final Sprite meteor;
	public final Sprite cloud;
	
	public Art() {
		player_right = new Sprite(0, 3, spriteBook);
		player_left = new Sprite(1, 3, spriteBook);
		coin = new Sprite(2, 7, spriteBook);
		meteor = new Sprite(3, 3, spriteBook);
		cloud = new Sprite(4, 2, spriteBook);
	}
}


