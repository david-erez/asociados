package States;

import Game.Ambiente;
import Game.Player;
import graficos.Assets;
import java.awt.Graphics;
import javax.swing.JPanel;
import math.Vector2D;

public class GameState extends JPanel {
    private Player player;
    private Ambiente ambiente;

    public GameState() {
        player = new Player(new Vector2D(50.0, 190), Assets.cubo);
        ambiente = new Ambiente(new Vector2D(0, 485), Assets.suelo);
    }

    public void update() {
        // Actualiza jugador (aplica gravedad, movimiento, disparo...)
        player.update();

        // Colisión simple jugador <-> suelo (Ambiente)
        if (player.isCollidingWith(ambiente)) {
            // obtenemos la Y del suelo y la altura del jugador
            int sueloY = ambiente.getBounds().y;
            int alturaJugador = player.getBounds().height;

            // colocamos al jugador justo encima del suelo
            player.getPosition().setY((double) (sueloY - alturaJugador));

            // detenemos velocidad vertical y marcamos que está en el suelo
            player.setVelocidadY(0);
            player.setEnElSuelo(true);
        } else {
            player.setEnElSuelo(false);
        }
    }

    public void draw(Graphics g) {
        player.draw(g);
        ambiente.draw(g);
    }
}
