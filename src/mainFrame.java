import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

class mainFrame extends JFrame implements KeyListener{
    private mainDraw draw;
    private mainDraw draw2;

    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            draw.moveRight();


        }
        else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
            draw.moveLeft();
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
            draw.moveDown();
        }
        else if(e.getKeyCode()== KeyEvent.VK_UP) {
            draw.moveUp();
        }
        else if(e.getKeyCode()== KeyEvent.VK_D){
            draw2.moveRight();
        }
        else if(e.getKeyCode()== KeyEvent.VK_A){
            draw2.moveLeft();
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){
            draw2.moveDown();
        }
        else if(e.getKeyCode()== KeyEvent.VK_W) draw2.moveUp();
    }
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    public mainFrame(){
        this.draw = new mainDraw(50,50);
        this.draw2= new mainDraw(500,100);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //draw.setY(50);
        //draw.setX(50);
        //draw2.setX(100);
        //draw2.setY(100);

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame frame = new mainFrame();
                frame.setTitle("Square Move Practice");
                frame.setResizable(false);
                frame.setSize(600, 600);
                frame.setMinimumSize(new Dimension(600, 600));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(frame.draw);
                frame.pack();
                frame.getContentPane().add(frame.draw2);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }
}