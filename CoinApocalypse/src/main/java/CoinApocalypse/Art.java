package CoinApocalypse;

import javafx.scene.image.Image;

@SuppressWarnings("exports")
public class Art {
	public static final int DEF_TILE_WIDTH = 32;
	
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

