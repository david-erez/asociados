package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import math.Vector2D;

public class Ambiente extends GameObjects {
    private int anchoPantalla = 30; // número de “bloques” que pintas

    public Ambiente(Vector2D position, BufferedImage texture) {
        super(position, texture);
    }

    @Override
    public void update() {
        // suelo estático, no hace nada
    }

    @Override
    public void draw(Graphics g) {
        int anchoTextura = texture.getWidth();

        for (int x = 0; x < anchoPantalla; x += anchoTextura) {
            g.drawImage(texture, x, (int) position.getY(), null);
        }
    }

    @Override
    public Rectangle getBounds() {
        // un rectángulo gigante cubriendo todo el suelo dibujado:
        int anchoTextura = texture.getWidth();
        return new Rectangle(
            0,
            (int) position.getY(),
            anchoPantalla, // si anchoPantalla es 30 píxeles reales es pequeño; 
                           // si querías 30 bloques, haz anchoPantalla*anchoTextura
            texture.getHeight()
        );
    }
}
