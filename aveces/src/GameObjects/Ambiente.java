package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import math.Vector2D;

public class Ambiente extends GameObjects {

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        
    int anchoPantalla = 3000; 
      

        int anchoTextura = texture.getWidth();

        for (int x = 0; x < anchoPantalla; x += anchoTextura) {
            g.drawImage(texture, x, (int) position.getY(), null);
        }
    }
    public Ambiente (Vector2D position, BufferedImage texture){
        super(position, texture);

    }
}
