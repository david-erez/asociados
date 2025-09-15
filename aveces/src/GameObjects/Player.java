package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graficos.Assets;
import imput.KeyBoard;
import math.Vector2D;
public class Player extends GameObjects {
     private boolean disparoActivo = false;
    private boolean mirandoDerecha = true;
    private int frame = 0;        
    private int animTick = 0;

      ArrayList<Bullet> balas = new ArrayList<>();
      private double velocidadY = 0;
    private double gravedad = 0.5;
    private boolean enElSuelo = false;
      private final int sueloY = 460;
    @Override
    public void update() {
       
    if (KeyBoard.space || KeyBoard.up) {
        if (enElSuelo) {
            velocidadY = -10; 
            enElSuelo = false;
        }
        }
         velocidadY += gravedad;
        position.setY(position.getY() + velocidadY);

             
    
    if (KeyBoard.right) {
    position.setX(position.getX() + 0.3);
    mirandoDerecha = true;
    animTick++;
}
     if (KeyBoard.left) {
        position.setX(position.getX()-0.3);        
     }

    if (KeyBoard.ei) {
        if (!disparoActivo) {
            
            balas.add(new Bullet(new Vector2D(position.getX() + texture.getWidth(), position.getY() + texture.getHeight() / 10), Assets.bala));
            disparoActivo = true; 
        }
    } else {
        disparoActivo = false; 
    }
     for (Bullet b : balas) {
        b.update();
     }
    

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

    g.drawImage(currentFrame, (int) position.getX(), (int) position.getY(), null);

    for (Bullet b : balas) {
        b.draw(g);
    }
}


    public Player(Object position, BufferedImage texture) {
        super(position, texture);
    }
}
