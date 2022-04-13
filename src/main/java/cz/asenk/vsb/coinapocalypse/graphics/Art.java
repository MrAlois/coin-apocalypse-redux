package cz.asenk.vsb.coinapocalypse.graphics;

import javafx.scene.image.Image;

@SuppressWarnings("exports")
public class Art {
	public static final int DEF_TILE_WIDTH = 32;

	public final Image imgGameBackground = new Image("file:src/main/resources/img/game/Background.png");
	public final Image imgMenuAbout = new Image("file:src/main/resources/img/menu/About.png");
	public final Image imgMenuBackground = new Image("file:src/main/resources/img/menu/MenuBackground.png");
	public final Image imgButtonUnpressed = new Image("file:src/main/resources/img/menu/Button.png");
	public final Image imgButtonPressed = new Image("file:src/main/resources/img/menu/PressedButton.png");
	public final Image imgMenuInstructions = new Image("file:src/main/resources/img/menu/Instructions.png");
	
	public final Sprite spritePlayerLeft;
	public final Sprite spritePlayerRight;
	public final Sprite coin;
	public final Sprite meteor;
	public final Sprite cloud;
	
	public Art() {
		Image spriteBook = new Image("file:src/main/resources/img/game/SpriteBook.png");
		spritePlayerRight = new Sprite(0, 3, spriteBook);
		spritePlayerLeft = new Sprite(1, 3, spriteBook);
		coin = new Sprite(2, 7, spriteBook);
		meteor = new Sprite(3, 3, spriteBook);
		cloud = new Sprite(4, 2, spriteBook);
	}
}

