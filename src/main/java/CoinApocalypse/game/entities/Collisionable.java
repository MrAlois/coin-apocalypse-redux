package CoinApocalypse.game.entities;

import javafx.geometry.Rectangle2D;

public interface Collisionable {
	public Collisionable intersects(Rectangle2D r);
	public String getName();
}
