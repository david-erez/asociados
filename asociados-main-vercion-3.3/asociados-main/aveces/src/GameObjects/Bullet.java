package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObjects {

    private double velocidadX; 
    private double velocidadY; 
    private int bulletType;

    public Bullet(Object position, BufferedImage texture, double velocidadX, double velocidadY, int bulletType) {
        super(position, texture);
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.bulletType = bulletType;
    }
    public int getBulletType() {
        return bulletType;
    }
    
    @Override
    public void update() {
        position.setX(position.getX() + velocidadX);
        position.setY(position.getY() + velocidadY);
        
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int) position.getX(), (int) position.getY(), null);
    }

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
