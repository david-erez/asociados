package Game;

import graficos.Assets;
import java.util.ArrayList;
import math.Vector2D;

public class Weapon {

    private int weaponType;
    // 🔹 parámetros de disparo
    private int fireCooldown;      // ticks restantes para disparar de nuevo
    private int baseCooldown;      // disparos rápidos
    private int maxCooldown;      // disparos lentos
    private int burstCount;        // cuántas balas seguidas
    private int burstLimit;        // límite de ráfaga rápida
    private double bulletSpeed = 10; // velocidad de la bala    
    // 🔹 lista de balas
    private ArrayList<Bullet> balas = new ArrayList<>();

    public Weapon(int weaponType){
        this.weaponType = weaponType;

        // configuración según tipo de arma
        switch (weaponType) {
            case 1: // pistola
                baseCooldown = 15;
                maxCooldown = 30;
                burstLimit = 3;
                bulletSpeed=5;
                break;
            case 2: // rifle
                baseCooldown = 5;
                maxCooldown = 20;
                burstLimit = 10;
                bulletSpeed=30;
                break;
            default: // por defecto, pistola
                baseCooldown = 15;
                maxCooldown = 30;
                burstLimit = 3;
                break;
        }
    }
        
    
    
    public void update() {
        // disminuir cooldown
        if (fireCooldown > 0) fireCooldown--;

        // actualizar balas
        for (Bullet b : balas) {
            b.update();
        }
    }

    public ArrayList<Bullet> getBullets() {
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
                0
        ));
    }
}
