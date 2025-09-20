package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import math.Vector2D;

public abstract class GameObjects {
    protected BufferedImage texture;
    protected Vector2D position;

    // 🔹 bandera global para mostrar hitboxes
    public static boolean showHitboxes = false;

    // cada objeto define su hitbox
    public abstract Rectangle getBounds();

    public GameObjects(Object position, BufferedImage texture) {
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

    // Método utilitario de colisión
    public boolean collidesWith(GameObjects other) {
        return getBounds().intersects(other.getBounds());
    }

    // 🔹 método auxiliar para dibujar la hitbox (llámalo desde draw() de cada objeto)
    protected void drawHitbox(Graphics g) {
        if (showHitboxes) {
            Rectangle r = getBounds();
            Color old = g.getColor();
            g.setColor(Color.RED);
            g.drawRect(r.x, r.y, r.width, r.height);
            g.setColor(old);
        }
    }
}
    