package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import math.Vector2D;

public abstract class GameObjects {
    protected BufferedImage texture;
    protected Vector2D position;
    public GameObjects (Object position, BufferedImage texture){
        this.position = (Vector2D) position;
        this.texture = texture;
    }
    public abstract void update();
    public abstract void draw(Graphics g);
  
    public Vector2D getPosition() {
        return position;
    }
    public void setPosition(Vector2D position) {
        this.position = position;
    }

}
