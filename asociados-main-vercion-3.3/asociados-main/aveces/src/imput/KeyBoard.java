package imput;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    private boolean[] keys = new boolean[256];
    public static boolean up, left, right, space, ei;

    public KeyBoard(){
        up = false;
        left = false;
        right = false;
        space = false;
        ei = false;

    }
    public void update(){
        up= keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_SPACE];
        left= keys[KeyEvent.VK_LEFT];
        right= keys[KeyEvent.VK_RIGHT];
        ei= keys[KeyEvent.VK_A];
    }
    @Override
    public void keyPressed(KeyEvent e) {
    
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
