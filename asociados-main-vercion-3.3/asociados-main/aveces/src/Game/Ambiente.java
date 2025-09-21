package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import math.Vector2D;

public class Ambiente extends GameObjects {

    // ancho total en píxeles (múltiplo exacto de la textura)
    private final int ancho;

    public Ambiente(Vector2D position, BufferedImage texture) {
        super(position, texture);

        // calcula cuántas veces entra la textura en 1000 px
        int anchoTextura = texture.getWidth();
        int baldosas = 30 / anchoTextura; // número entero de baldosas
        if (baldosas < 1) baldosas = 1;     // al menos 1

        this.ancho = baldosas * anchoTextura; // ancho exacto = n * anchoTextura
    }

    @Override
    public void update() {
        // suelo estático
    }

    @Override
    public void draw(Graphics g) {
        int anchoTextura = texture.getWidth();

        // pintamos tantas texturas como quepan en "ancho"
        for (int x = 0; x < ancho; x += anchoTextura) {
            g.drawImage(texture, x, (int) position.getY(), null);
        }

        // dibuja la hitbox en rojo para depurar
        drawHitbox(g);
    }

    @Override
    public Rectangle getBounds() {
        // ahora la hitbox tiene exactamente el ancho pintado y el alto de la textura
        return createBounds(
            0,                        // offsetX
            0,                        // offsetY
            ancho,                    // ancho total del suelo
            texture.getHeight()       // alto del suelo
        );
    }

    // si quieres ancho aleatorio pero alineado al tamaño de la textura:
    public int getRandomNumber() {
        int anchoTextura = texture.getWidth();
        int baldosas = (int)(Math.random() * 10) + 1; // de 1 a 10 baldosas
        return baldosas * anchoTextura;
    }
}
