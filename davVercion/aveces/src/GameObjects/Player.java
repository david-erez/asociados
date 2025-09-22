package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import GameObjects.MovingObjects;
import graficos.Assets;
import imput.KeyBoard;
import math.Vector2D;
public class Player extends   MovingObjects {
    private double count = 0;
    private Cronometer fireRate;
    private boolean mirandoDerecha = true;
    
    private int frame = 0;        
    private int animTick = 0;

      ArrayList<Bullet> balas = new ArrayList<>();
      private double velocidadY = 0;
    private double gravedad = 0.78;
    private boolean enElSuelo = false;
      public int sueloY = 450;
   
    
@Override
public void update() {
    count += 1;

    // gravedad
    velocidadY += gravedad;
    position.setY(position.getY() + velocidadY);

    // movimiento horizontal
    if (KeyBoard.right )      {
        position.setX(position.getX() + 4);
        mirandoDerecha = true;
    }
    if (KeyBoard.left) {
        position.setX(position.getX() - 4);
        mirandoDerecha = false;
    }

    // salto
    if ((KeyBoard.space || KeyBoard.up) && enElSuelo) {
        velocidadY = -12.5;
        enElSuelo = false;
    }

    // balas
    if (KeyBoard.ei && count >= 250) {
        disparar(true);
        count = 0;
    }

    // actualizar balas
    for (Bullet b : balas) {
        b.update();
    }
}


    public void disparar(boolean activo){
        balas.add(new Bullet(new Vector2D(position.getX() + texture.getWidth(), position.getY() + texture.getHeight() / 10), Assets.bala));
    }
    @Override
public void onCollision(GameObjects other) {
    if (other instanceof Ambiente) {
        Rectangle suelo = other.getBounds();

        // solo corregimos si viene cayendo, no si está subiendo
        if (velocidadY >= 0) {
            position.setY(suelo.getY() - getBounds().height);
            velocidadY = 0;
            enElSuelo = true;
        }
    }
}



    
 @Override
public void draw(Graphics g) {
    BufferedImage currentFrame;

    if (KeyBoard.right || KeyBoard.left) {
        animTick++;
        if (animTick >= 10) {
            frame++;
            animTick = 0;
        }

        // reiniciar frame si se pasa del límite
if (mirandoDerecha) {
    frame = frame % Assets.walkDere.length;
    currentFrame = Assets.walkDere[frame];
} else {
    frame = frame % Assets.walkHiz.length;
    currentFrame = Assets.walkHiz[frame];
}
    } 
    else { currentFrame = Assets.cubo;
        frame = 0;
        animTick = 0;
    }

    g.drawImage(currentFrame, (int) position.getX(), (int) position.getY(), 37, 37, null);

    for (Bullet b : balas) {
        b.draw(g);
    }

    drawHitbox(g);
}

@Override
public Rectangle getBounds() {
    return createBounds(5, 5, 27, 38); 
}
public Player(Vector2D position, BufferedImage texture) {
    super(position, texture);
}
}