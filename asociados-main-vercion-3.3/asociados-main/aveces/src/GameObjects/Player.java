package GameObjects;

import graficos.Assets;
import imput.KeyBoard;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;

public class Player extends GameObjects {
    private double count = 0;
    private boolean mirandoDerecha = true;
    private int frame = 0;        
    private int animTick = 0;

    ArrayList<Bullet> balas = new ArrayList<>();
    private double velocidadY = 0;
    private double gravedad = 0.78;
    private boolean enElSuelo = false;
    public int sueloY = 450;

    public Player(Object position, BufferedImage texture) {
        super(position, texture);
    }

    @Override
    public void update() {
        count = count + 1;

        // salto
        if ((KeyBoard.space || KeyBoard.up) && enElSuelo) {
            velocidadY = -12.5; 
            enElSuelo = false;
        }

        // gravedad
        velocidadY += gravedad;
        position.setY(position.getY() + velocidadY);

        // movimiento lateral
        if (KeyBoard.right) {
            position.setX(position.getX() + 4);
            mirandoDerecha = true;
            animTick++;
        }
        if (KeyBoard.left) {
            position.setX(position.getX() - 4);        
        }

        // disparar
        if (KeyBoard.ei && count >= 250) {
            disparar(true);
            count = 0;
        } 

        // actualizar balas
        for (Bullet b : balas) {
            b.update();
        }

        // “suelo” temporal mientras no hay colisiones
        if (position.getY() >= sueloY) {
            position.setY(sueloY);
            velocidadY = 0;
            enElSuelo = true;
        }
    }

    public void disparar(boolean activo) {
        balas.add(new Bullet(
            new Vector2D(
                position.getX() + texture.getWidth(), 
                position.getY() + texture.getHeight() / 10
            ), 
            Assets.bala));
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage currentFrame;

        if (KeyBoard.right || KeyBoard.left) {
            animTick++;
            if (animTick >= 400) {
                frame = (frame + 1) % Assets.walk.length; 
                animTick = 0; 
            }
            currentFrame = Assets.walk[frame];
        } else {
            currentFrame = Assets.cubo;
            frame = 0;       
            animTick = 0;
        }

        g.drawImage(currentFrame, (int) position.getX(), (int) position.getY(), 37, 37, null);

        for (Bullet b : balas) {
            b.draw(g);
        }

        // dibuja la hitbox si quieres depurar:
        drawHitbox(g);
    }

    // 🔹 define la hitbox del Player
    @Override
    public Rectangle getBounds() {
        // offsetX, offsetY, ancho, alto
        return createBounds(5, 5, 27, 32);
    }

    // 🔹 para comprobar colisión con otro GameObject
    public boolean isCollidingWith(GameObjects other) {
        return getBounds().intersects(other.getBounds());
    }
}
