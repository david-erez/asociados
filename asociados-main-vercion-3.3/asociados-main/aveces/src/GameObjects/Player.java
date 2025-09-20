package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graficos.Assets;
import imput.KeyBoard;
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
    @Override
    public void update() {
    count=count+1;

    if (KeyBoard.space || KeyBoard.up) {
        if (enElSuelo) {
            velocidadY = -12.5; 
            enElSuelo = false;
        }
        }
         velocidadY += gravedad;
        position.setY(position.getY() + velocidadY);

             
    
    if (KeyBoard.right) {
    position.setX(position.getX() + 4);
    mirandoDerecha = true;
    animTick++;
}
     if (KeyBoard.left) {
        position.setX(position.getX()-4);        
     }
    
    

    if(KeyBoard.ei && count >=250){
        disparar(true);
        count=0;

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
    public void disparar(boolean activo){
        balas.add(new Bullet(new Vector2D(position.getX() + texture.getWidth(), position.getY() + texture.getHeight() / 10), Assets.bala));
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

    g.drawImage(currentFrame, (int) position.getX(), (int) position.getY(), 37,37,null);

    for (Bullet b : balas) {
        b.draw(g);
    }
}


    public Player(Object position, BufferedImage texture) {
        super(position, texture);
    }
}