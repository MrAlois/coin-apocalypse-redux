# Coin Apocalypse - Java clone
_A game clone for a school project using JavaFX, FXML and JDBC. Don't take this shitty code seriously. :)_

![CoinApocalypseTitle](modules/CoinApocalypse/src/main/resources/img/menu/CoinApocalypse.png?raw=true "Coin Apocalypse Redux Logo")

## About
This repository is simply a testing environment for testing new stuff. Originally it started as a school semestral project back in 2020, when I didn't know anything related to Java. 

The original game _**Coin Apocalypse**_[^1] was made by a small independent dev Drakensang. Unfortunately, the original of this game doesn't work at this time because it was developed on the Adobe Flash platform - which isn't supported nowadays. 

_**CoinApocalypse-Redux**_ is a Semestral project created for a class called "Programming in Java I". Students should learn the basics of Java (OOP, Collections, Libraries, Databases, and basics of networking) and therefore our goal was to make a simple "8-Bit-like" game, that would focus on those aspects.


## Used technologies 
- Java 17
- Maven (updating and downloading necessary libraries)
- FXGL (JavaFX game library)
- JavaFX (application and graphics rendering)
     - FXML (layout of the application GUI)
     - CSS (styling the GUI)
- JDBC (JPA)
- SpringBoot
- Project reactor

#### Additional
- [SceneBuiler](https://gluonhq.com/products/scene-builder/) for efficient FXML editing
- Some of the knowledge about old 8-bit games[^2]
- Game patterns based on a book called [Game design patterns](https://gameprogrammingpatterns.com/contents.html)

## Java I project progress
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

## Java II project progress
- [X] Client-Server architecture with REST 	(SpringBoot)
- [X] Persistent data saved using JPA		(Hibernate)
- [X] Log integration using log4j2		(Slf4j + Logback)
- [X] CompletableFuture				(Project Reactor)
- [ ] Multi-lang support			
- [X] Lombok		
- [ ] Using Date
- [X] Streams and Lambda expressions
- [X] Buildable using Maven (exports runnable .jar or equivalent)

[^1]: Gameplay: https://www.youtube.com/watch?v=w-SgjPvZOHk
[^2]: Great sources of information are youtube videos about how the games worked back in the 00's. For instatnce youtubers like Bisqwit and The 8-Bit Guy have great collection about this kind of topic.
