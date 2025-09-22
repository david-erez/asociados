package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import math.Vector2D;

public class Bullet extends GameObjects {
    public double velocidad = 9.7;

    public Bullet(Object position, BufferedImage texture) {
        super(position, texture);
    }

    @Override
    public void update() {
        position.setX(position.getX() + velocidad);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int) position.getX(), (int) position.getY(), null);
    }

    @Override
    public Rectangle getBounds() {
        return createBounds(0, 0, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void onCollision(GameObjects other) {
        if (other instanceof Ambiente) {
         
        }
    }
}

