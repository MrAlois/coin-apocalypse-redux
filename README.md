# Coin Apocalypse - Java clone
_Coin Apocalypse clone for a school project using JavaFX, FXML and JDBC._

![CoinApocalypseTitle](/CoinApocalypse/src/main/resources/img/menu/CoinApocalypse.png?raw=true "Coin Apocalypse Redux Logo")

## About
This project is my take at a clone of a game called _**Coin Apocalypse**_[^1] by small independent dev Drakensang. Unfortunately, the original of this game doesn't work at this time because it was developed on Adobe Flash platform - which isn't supported nowadays. 

_**CoinApocalypse-Redux**_ is as a Semestral project created for a class called "Programming in Java I". Students should learn the basics of Java (OOP, Collections, Libraries, Databases and basics of networking) and therefore our goal was to make a simple "8-Bit-like" game, that would focus on those aspects.

I'm going to work on this project throughout my career as I am certain that in the future I will be more and more embarrased by the codebase I've just released - and that's someting, no programmer wishes to have. Bad - but working - codebase (especially that is public). :)  

Feel free to inspire yourself and if need be, comment and objectively critize. 

## Used technologies 
- Maven (updating and downloading necessary libraries)
- JavaFX (application and graphics rendering)
     - FXML (layout of the application GUI)
     - CSS (styling the GUI)
- JDBC

#### Additional
- [SceneBuiler](https://gluonhq.com/products/scene-builder/) for efficient FXML editing
- Some of the knowledge about old 8-bit games[^2]
- Game patterns based on a book called [Game design patterns](https://gameprogrammingpatterns.com/contents.html)

## Project progress
##### Design patterns
- [X] Class layouts, inhertiances
- [X] JavaFX GUI controllers
- [ ] Gameplay optimalization
- [X] Rendering optimalization
##### Graphics
- [ ] Sprites, Animated sprites
- [X] Graphical user interface using FXML
- [ ] Graphical user interface styling using CSS
##### Sounds
- [X] Sound design
- [X] Sound implementaion
##### Gameloop
- [X] Physics (/w screen boundariy triggers - entities, player, etc.)
- [ ] Victory conditionals
- [X] Player upgrade implementation
- [ ] \(Optional) Statistics using in-memory database (JDBC)

## v2.0 progress
- [ ] Client-Server architecture with REST
- [ ] Persistent data saved using JPA
- [ ] Log integration using log4j2
- [ ] CompletableFuture
- [ ] Multi-lang support
- [ ] Lombok
- [ ] Using Date
- [ ] Streams and Lambda expressions
- [ ] Buildable using Maven (exports runnable .jar or equivalent)

## Graphics
This game uses so-called Sprite Book. It's a file, that is on a grid of constant lenghth (32px in this case). The Sprite book contains every frame of an drawable Entity, that is in the game (except for buttons, menus and in the future, the upgrades).

![SpriteSheet](/CoinApocalypse/src/main/resources/img/game/SpriteBook.png?raw=true "Sprite Sheet")

Every graphic component in the game is handled through **Art.java** class.

## Sounds
Sounds vere made using [jsfxr](https://sfxr.me/), and online 8-bit sound generating tool.

## Gameloop
Player catches coin while he tries to avoid meteors. In the original game the player had three lives which he could upgrade using the coins he would collect. In this case, the upgrading section needs work, so it's not released yet (and therefore the lives don't matter for now). Game ends, when the player collides with meteor.

The longer the player survives in the game, the harder it becomes. Meteors will fall more quickly, slowing the progress of the player. However, this is compensated by increased coin and score gain.

The player can jump, and move left or right. In the future this will coded so that player can ugprade the speed, height, and control of the player.

![Gameplay screenshot](/Screenshots/Gameplay1.PNG?raw=true "Gameplay")

## Explanatory notes
For auto-generated class diagram see [this](https://github.com/MrAlois/java-game-CoinApocalypse/blob/java-game-CoinApocalypse/Screenshots/ClassDiagram.png). _(WORK IN PROGRESS)_

Except for few exceptions (see below), I tried to avoid static programming as much as I could. Except for classes **Sound.java**, **Art.java** and **App.java**, I used static only to define global constants. 
The **Sound.java** class is made to represent the correct way of using staticly loaded files (it behaves just like a sound book of sorts), so the program doesn't have to load and unload files every time, it's being used.

### Static examples
_Sound.java_
```java
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static Sound hurt = loadSound("src/main/resources/snd/hitHurt.wav");
	public static Sound jump = loadSound("src/main/resources/snd/jump.wav");
	public static Sound coin = loadSound("src/main/resources/snd/pickupCoin.wav");
	public static Sound click = loadSound("src/main/resources/snd/click.wav");
	
     private Clip clip;
     
	public static Sound loadSound(String fileName) {...}
	public void play() {...} // Method used outside this class
}
```

**Art.java** behaves simillarly to the **Sound.java** with a big exception - it's not allocated statically but dynamically. (For school purposes.)

_Art.java_
```java
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
```

Last occurance of static programming is in the main class -> **App**. With this kind of approcach, I can change scene controller (= UI layout) from every class in the project. This is escpecially useful in applications with many interchanging screens. Every FXML file is separate and thus every scene is more readable for the developer. 

```java
public class App extends Application {
    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
    	primaryStage = stage;
        primaryStage.getIcons().add(new Image("file:src/main/resources/img/CoinApocalypseIco.png"));
        primaryStage.setTitle("Coin Apocalypse - v0.0.2");
        
        scene = new Scene(loadFXML("menu_screen"), 1024, 800);
        scene.getStylesheets().add("file:src/main/resources/CoinApocalypse/stylesheet.css");
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    ...
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".cz.asenk.vsb.cz.asenk.vsb.coinapocalypse.cz.asenk.vsb.coinapocalypse"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }   
```

[^1]: Gameplay: https://www.youtube.com/watch?v=w-SgjPvZOHk
[^2]: Great sources of information are youtube videos about how the games worked back in the 00's. For instatnce youtubers like Bisqwit and The 8-Bit Guy have great collection about this kind of topic.
