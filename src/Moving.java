import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
// Alljärgnev kood ei tööta ;(, ainult abstactiga ei viska errorit
public abstract class Moving extends Main implements KeyListener {

    public void keyPressed(KeyEvent w){
        int keyCode = w.getKeyCode();
        if(keyCode == KeyEvent.VK_W){
            System.out.println("Sa vajutad W tähte!");
        }
    }
    }

