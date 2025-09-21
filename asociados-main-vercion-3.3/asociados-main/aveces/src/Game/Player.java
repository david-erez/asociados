package Game;

import graficos.Assets;
import imput.KeyBoard;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import math.Vector2D;

public class Player extends GameObjects {
    private boolean mirandoDerecha = true;
    private int frame = 0;        
    private int animTick = 0;

    private double velocidadY = 0;
    private final double gravedad = 0.78;
    private boolean enElSuelo = false;
    public int sueloY = 450;

    private WeaponSelected weaponS;

    public Player(Vector2D position, BufferedImage texture) {
        super(position, texture);
        
        this.weaponS = new WeaponSelected(2);
    }

    @Override
    public void update() {
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
            mirandoDerecha = false;
        }

        // disparar con arma (pasamos mirandoDerecha)
        if (KeyBoard.ei) {
            weaponS.tryShoot(
                position.getX() + texture.getWidth(),
                position.getY() + texture.getHeight() / 10,
                mirandoDerecha
            );
        } else {
            // si suelta el botón, resetea la ráfaga
            weaponS.resetBurst();
        }

        // actualizar arma (reduce cooldown y actualiza balas)
        weaponS.update();

        // “suelo” temporal mientras no hay colisiones
        if (position.getY() >= sueloY) {
            position.setY(sueloY);
            velocidadY = 0;
            enElSuelo = true;
        }
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

        // dibujar las balas del arma
        for (Bullet b : weaponS.getBullets()) {
            b.draw(g);
        }

        // dibuja la hitbox si quieres depurar
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
