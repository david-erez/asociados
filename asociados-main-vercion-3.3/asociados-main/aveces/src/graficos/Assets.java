package graficos;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage cubo;
    public static BufferedImage suelo;
    public static BufferedImage bala;
    public static BufferedImage[] walk;
    public static void init() {
        cubo = Loader.imageLoader("/recursos/eee.png");
        suelo = Loader.imageLoader("/recursos/pasto.png");
        walk = new BufferedImage[] {
            Loader.imageLoader("/recursos/dereuno.png"),
            Loader.imageLoader("/recursos/deredos.png")
        };
        bala = Loader.imageLoader("/recursos/bala.png");

    }
}
