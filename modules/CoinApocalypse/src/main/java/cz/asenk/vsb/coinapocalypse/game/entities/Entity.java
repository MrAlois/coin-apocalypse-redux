package cz.asenk.vsb.coinapocalypse.game.entities;


import javafx.geometry.Rectangle2D;

public abstract class Entity implements Drawable {
	protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;
    protected double width;
    protected double height;
        
    public abstract void update(double deltaT, int tick);
    
    protected Entity() {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
        width = 0;
        height = 0;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public double getVelocityX() {
    	return velocityX;
    }
    
    public double getVelocityY() {
    	return velocityY;
    }
        
    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }
    
    public String toString() {
        return " Position: [" + positionX + "," + positionY + "]" + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}