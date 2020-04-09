import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;
import java.time.ZoneOffset;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;


class mainFrame extends JFrame implements KeyListener{
    private mainDraw draw;
    private mainDraw draw2;
    boolean  kokkuPorge = false;
    int uuenduskiirus = 500;
    public int skoor1;
    public int skoor2;
    public boolean onoff = false;
    float cooldown1;
    float cooldown2;

    Timer timer1 = new Timer();
    TimerTask task1 = new TimerTask(){
        public void run(){
            cooldown1 += 0.5;

        }
    };
    public void timerstart1(){
        timer1.scheduleAtFixedRate(task1,uuenduskiirus,uuenduskiirus);
    }

    Timer timer2 = new Timer();
    TimerTask task2 = new TimerTask(){
        public void run(){
            cooldown2 += 0.5;

        }
    };
    public void timerstart2(){
        timer2.scheduleAtFixedRate(task2,uuenduskiirus,uuenduskiirus);
    }
    public void keyReleased(KeyEvent e) {
        //System.out.println(e.getKeyChar());
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
        else if(e.getKeyCode()== KeyEvent.VK_SPACE){
            if (cooldown1>=0.5) {
                System.out.println("PLAYER 1 LÖÖB");
                Rectangle rectangle1 = draw.getBounds();
                Rectangle rectangle2 = draw2.getBounds();
                collision(rectangle1, rectangle2, 50);
                if (collisionToimub(rectangle1,rectangle2)){
                    muudaSkoor1(draw);
                }
                rectangle1.setLocation(0, 0);
                rectangle2.setLocation(500, 500);
                draw2.refresh();
                System.out.println(cooldown1);
                cooldown1 = 0;
            }
        }
        else if(e.getKeyCode()== KeyEvent.VK_ENTER){
            if (cooldown2>=1) {
                System.out.println("PLAYER 2 LÖÖB");
                Rectangle rectangle1 = draw.getBounds();
                Rectangle rectangle2 = draw2.getBounds();
                collision(rectangle1, rectangle2, 50);
                if (collisionToimub(rectangle1,rectangle2)){
                    muudaSkoor2(draw2);
                }
                rectangle1.setLocation(0, 0);
                rectangle2.setLocation(500, 500);
                draw2.refresh();
                System.out.println(cooldown2);
                cooldown2 = 0;
            }
        }
        else if(e.getKeyCode()== KeyEvent.VK_W) draw2.moveUp();
    }
    public void keyTyped(KeyEvent e) {
        //System.out.println("keyTyped");
    }

    public mainFrame(){
        this.draw = new mainDraw(500,150, "Zeroman offical.png",1);
        this.draw2= new mainDraw(50,50, "Zeromanreal.png",2);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timerstart1();
        timerstart2();
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
    public boolean collisionToimub(Rectangle rectangle1, Rectangle rectangle2){
        if (rectangle1.intersects(rectangle2)){
            return true;
        }else{
            return false;
        }
    }
    public void collision(Rectangle rectangle1, Rectangle rectangle2, int lisaSuurus) {
            rectangle1.grow(lisaSuurus,lisaSuurus);
            if (collisionToimub(rectangle1,rectangle2)) {
                draw.setKokkuPorge(true);
            }
            else {
                draw.setKokkuPorge(false);
            }

    }
    public void muudaSkoor1(mainDraw objekt){
        skoor1++;
        objekt.setSkoor(skoor1);
    }
    public void muudaSkoor2(mainDraw objekt){
        skoor2++;
        objekt.setSkoor(skoor2);
    }
}