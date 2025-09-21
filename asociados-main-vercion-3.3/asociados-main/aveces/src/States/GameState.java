package States;

import Game.Ambiente;
import Game.Player;
import graficos.Assets;
import java.awt.Graphics;
import javax.swing.JPanel;
import math.Vector2D;

public class GameState extends JPanel{
    private Player player;
    private Ambiente ambiente;

    public GameState() {
        player = new Player(new Vector2D(50.0,190), Assets.cubo);
        
        ambiente = new Ambiente(new Vector2D(0, 485), Assets.suelo);

    }
    public void update(){
        player.update();
    }
    public void draw(Graphics g){
player.draw(g);
ambiente.draw(g);

    }
}
