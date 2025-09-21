package GameObjects;

import graficos.Assets;
import java.util.ArrayList;
import math.Vector2D;

public class Weapon {

    // 🔹 parámetros de disparo
    private int fireCooldown = 0;      // ticks restantes para disparar de nuevo
    private int baseCooldown = 4;      // disparos rápidos
    private int maxCooldown = 18;      // disparos lentos
    private int burstCount = 0;        // cuántas balas seguidas
    private int burstLimit = 8;        // límite de ráfaga rápida
    private int bulletType = 0;      // tipo de bala (0 = normal, 1 = rápida, 2 = lenta)

    // 🔹 lista de balas
    private ArrayList<Bullet> balas = new ArrayList<>();

    
    public Weapon(int baseCooldown, int maxCooldown, int burstLimit, int bulletType) {
        this.baseCooldown = baseCooldown;
        this.maxCooldown = maxCooldown;
        this.burstLimit = burstLimit;
        this.bulletType = bulletType;   
    }

    
    public void update() {
        // disminuir cooldown
        if (fireCooldown > 0) fireCooldown--;

        // actualizar balas
        for (Bullet b : balas) {
            b.update();
        }
    }

    public ArrayList<Bullet> getBullets(getBulletType()) {
        return balas;
    }

    public void resetBurst() {
        burstCount = 0;
    }

    /**
     * Intenta disparar una bala desde la posición x,y
     * @param x posición inicial X
     * @param y posición inicial Y
     * @param mirandoDerecha true si el personaje mira a la derecha
     */
    public void tryShoot(double x, double y, boolean mirandoDerecha) {
        if (fireCooldown == 0) {
            shoot(x, y, mirandoDerecha);
            burstCount++;

            if (burstCount < burstLimit) {
                fireCooldown = baseCooldown;
            } else {
                fireCooldown = Math.min(maxCooldown,
                        baseCooldown + (burstCount - burstLimit));
            }
        }
    }

    private void shoot(double x, double y, boolean mirandoDerecha) {
        double dir = mirandoDerecha ? 1 : -1;

        balas.add(new Bullet(
                new Vector2D(x, y),
                Assets.bala,
                bulletSpeed * dir, // velocidad X
                0                  // velocidad Y (0 si horizontal)
        ));
    }
}
