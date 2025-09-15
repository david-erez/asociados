package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Bullet extends GameObjects {
    ArrayList<Bullet> balas = new ArrayList<>();
  public double velocidad = 0.5;

    public Bullet(Object position, BufferedImage texture) {
        super(position, texture);
    }

    @Override
    public void update() {
position.setX(position.getX() +velocidad);        
       

    
    }

    @Override
    public void draw(Graphics g) {
    g.drawImage(texture,(int)position.getX(),(int)position.getY(), null);
      }
    }
    
