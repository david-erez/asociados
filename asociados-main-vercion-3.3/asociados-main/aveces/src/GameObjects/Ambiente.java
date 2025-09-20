package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import math.Vector2D;

public class Ambiente extends GameObjects {

    private int anchoPantalla = 3000; // ancho total en píxeles

    public Ambiente(Vector2D position, BufferedImage texture){
        super(position, texture);
    }

    @Override
    public void update() {
        // el suelo es estático, no hace nada
    }

    @Override
    public void draw(Graphics g) {
        int anchoTextura = texture.getWidth();

        for (int x = 0; x < anchoPantalla; x += anchoTextura) {
            g.drawImage(texture, x, (int) position.getY(), null);
        }

        // dibuja la hitbox si quieres depurar
        drawHitbox(g);
    }

    @Override
    public Rectangle getBounds() {
        // hitbox del suelo: desde x=0 hasta el ancho total
        return createBounds(
            0,                        // offsetX
            0,                        // offsetY
            anchoPantalla,            // ancho total del suelo
            texture.getHeight()       // alto del suelo
        );
    }
}
