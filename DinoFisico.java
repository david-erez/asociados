import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DinoFisico extends JPanel implements ActionListener, KeyListener {

    // Físicas horizontales
    double masa = 1.0;
    double fuerza = 0.0;
    double aceleracion = 0.0;
    double velocidad = 0.0;
    double posicionX = 50.0;
    //movimiento del suelo
    int sueloOg = 0;
    int velocidadSuelo = 0;
    // disparo de arma blanca
    int disparoX = -25;
    int disparoY = -10;
    boolean disparo = false;
    // Físicas verticales
    double posicionY = 190;      // posición vertical (piso)
    double velocidadY = 0.0;     // velocidad vertical
    double gravedad = 1500.0;    // fuerza de gravedad (pixeles/s^2)
    boolean enElSuelo = true;

    final int dinoAncho = 60;
    final int dinoAlto = 60;
    final int sueloY = 250;

    Timer timer;
    boolean aplicarFuerza = false;

    public DinoFisico() {
        setPreferredSize(new Dimension(800, 300));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(20, this); // 50 FPS
        // Si quieres que arranque solo:
        // timer.start();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo
        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Suelo
        // Dibujar suelo con textura
g.setColor(Color.GRAY);
g.fillRect(sueloOg, sueloY, getWidth(), 50);
g.fillRect(sueloOg + getWidth(), sueloY, getWidth(), 50);

// Textura simple (líneas)
g.setColor(Color.RED);
for (int x = sueloOg; x < sueloOg + 2 * getWidth(); x += 40) {
g.fillRect(x, sueloY + 40, 20, 10); // bloques que se repiten


        // Dino
        g.setColor(Color.BLACK); 
        g.fillRect((int) posicionX, (int) posicionY, dinoAncho, dinoAlto);
        // Disparo
        g.setColor(Color.RED);
        g.fillRect(disparoX, (int) posicionY + dinoAlto / 2 - 5, 20, 10);
    

        // Velocidades
        g.setColor(Color.BLUE);
        g.drawString(String.format("Velocidad X: %.2f", velocidad), 10, 20);
        g.drawString(String.format("Velocidad Y: %.2f", velocidadY), 10, 40);
         g.drawString(String.format("fuerza: %.2f", fuerza), 10, 60);
    }
}
    
@Override
public void keyPressed(KeyEvent e) {
int key = e.getKeyCode();
if (key == KeyEvent.VK_ENTER) {
   timer.start();
}
if (key == KeyEvent.VK_RIGHT && enElSuelo) {
    aplicarFuerza = true;
}

 if (key == KeyEvent.VK_SPACE && enElSuelo) {
    velocidadY = -600;  // impulso de salto
    enElSuelo = false;
}
if (key == KeyEvent.VK_A ) {
    disparoX = (int) posicionX + dinoAncho;
            
 }
}

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            aplicarFuerza = false;
        }
        if (key == KeyEvent.VK_A ) {
                      
        }



       
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Física horizontal
        if (aplicarFuerza) {
            fuerza += 20; 
            if (fuerza > 1000) fuerza = 1000; // Limita la fuerza máxima
        } else {
            fuerza = 0; // Sin fuerza si no está presionada
        }
        aceleracion = fuerza / masa;
        velocidad += aceleracion * 0.02;
        posicionX += velocidad * 0.1;
        
        //el cuadro dispara
        if (disparoX > 0) {
            disparoX += 15; // velocidad del disparo
            if (disparoX > getWidth()) {
                disparoX = -20; // reinicia el disparo fuera de pantalla
            }
        }

        

        // Si se sale de la pantalla por la derecha, reaparece
        if (posicionX > getWidth()) {
            posicionX = -dinoAncho;
        }
    
        if (!enElSuelo) {
            velocidadY += gravedad * 0.02;
            posicionY += velocidadY * 0.02;

            // Colisión con el suelo
            if (posicionY + dinoAlto >= sueloY) {
                posicionY = sueloY - dinoAlto;
                velocidadY = 0;
                enElSuelo = true;
            }
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("tu no te lo creeras");
        final DinoFisico panel = new DinoFisico();

        ventana.add(panel);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public double getMasa() {
        return masa;
    }

    public void setMasa(double masa) {
        this.masa = masa;
    }

    public double getFuerza() {
        return fuerza;
    }

    public void setFuerza(double fuerza) {
        this.fuerza = fuerza;
    }

    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(double velocidadY) {
        this.velocidadY = velocidadY;
    }

    public double getGravedad() {
        return gravedad;
    }

    public void setGravedad(double gravedad) {
        this.gravedad = gravedad;
    }

    public boolean isEnElSuelo() {
        return enElSuelo;
    }

    public void setEnElSuelo(boolean enElSuelo) {
        this.enElSuelo = enElSuelo;
    }

    public int getDinoAncho() {
        return dinoAncho;
    }

    public int getDinoAlto() {
        return dinoAlto;
    }

    public int getSueloY() {
        return sueloY;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isAplicarFuerza() {
        return aplicarFuerza;
    }

    public void setAplicarFuerza(boolean aplicarFuerza) {
        this.aplicarFuerza = aplicarFuerza;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}