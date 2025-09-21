package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import math.Vector2D;

public abstract class GameObjects {
    protected BufferedImage texture;
    protected Vector2D position;

    public GameObjects(Vector2D position, BufferedImage texture){
    this.position = position;
    this.texture = texture;
}


    public abstract void update();
    public abstract void draw(Graphics g);

    // 🔹 crea una hitbox personalizada
    public Rectangle createBounds(int offsetX, int offsetY, int width, int height) {
        return new Rectangle(
            (int) (position.getX() + offsetX),
            (int) (position.getY() + offsetY),
            width,
            height
        );
    }

    // 🔹 cada subclase define su propia hitbox
    public abstract Rectangle getBounds();

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    // 🔹 utilidad para dibujar la hitbox en rojo
    public void drawHitbox(Graphics g) {
        Rectangle r = getBounds();
        Color old = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(r.x, r.y, r.width, r.height);
        g.setColor(old);
    }
}
