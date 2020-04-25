import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;


class main extends JFrame implements KeyListener{ //4# Siin klassis pärime funktsioonid Keylistenerilt, et koodi abil oleks võimalik klaviatuuri kaasata.
    private Draw draw;
    private Draw draw2;
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
    }

    public void keyPressed(KeyEvent e) { //#5 Siin funktsioonis kontrollime kas nuppu on vajutatud ning millist nuppu on vajutatud
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            draw.moveRight();
            draw.setPilt("Zeromanreal.png");
        }
        else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
            draw.moveLeft();
            draw.setPilt("zeroleftroh.png");
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
            draw.moveDown();
        }
        else if(e.getKeyCode()== KeyEvent.VK_UP) {
            draw.moveUp();
        }
        else if(e.getKeyCode()== KeyEvent.VK_D){
            draw2.moveRight();
            draw2.setPilt("Zeromanreal.png");
        }
        else if(e.getKeyCode()== KeyEvent.VK_A){
            draw2.moveLeft();
            draw2.setPilt("zeroleftroh.png");
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){
            draw2.moveDown();
        }
        else if(e.getKeyCode()== KeyEvent.VK_W){
            draw2.moveUp();
        }
        else if(e.getKeyCode()== KeyEvent.VK_SPACE){
            if (cooldown1>=0.5) {
                System.out.println("PLAYER 1 LÖÖB");
                Rectangle rectangle1 = draw.getBounds();
                Rectangle rectangle2 = draw2.getBounds();
                draw2.setPilt("zerospac.png");
                collision(rectangle1, rectangle2, 5);
                if (collisionToimub(rectangle1,rectangle2)){
                    muudaSkoor1(draw);
                    muudaAsukohad(50,200,50,500,draw2);
                    muudaAsukohad(300,550,50,500,draw);
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
                draw.setPilt("zeroent.png");
                collision(rectangle1, rectangle2, 5);
                if (collisionToimub(rectangle1,rectangle2)){
                    muudaSkoor2(draw2);
                    muudaAsukohad(50,200,50,500,draw2);
                    muudaAsukohad(300,550,50,500,draw);
                }
                rectangle1.setLocation(0, 0);
                rectangle2.setLocation(500, 500);
                draw2.refresh();
                System.out.println(cooldown2);
                cooldown2 = 0;
            }
        }

    }
    public void keyTyped(KeyEvent e) {
    }

    public main(){ // siin kasutame Scannerit, et saada user inputi ning anname rectangletile parameetrid
        Scanner nimed = new Scanner(System.in);
        System.out.println("Sisesta nimi: ");
        String mängija1 = nimed.nextLine();  // Read user input
        System.out.println("Sinu kangelasnimi on: " + mängija1.toUpperCase());  // Output user in
        Scanner nimed2 = new Scanner(System.in);
        System.out.println("Sisesta nimi: ");
        String mängija2 = nimed2.nextLine();  // Read user input
        System.out.println("Sinu kangelasnimi on: " + mängija2.toUpperCase());  // Output user in
        System.out.println("Player1 controls: WASD + space, Player2 controls: up,down,left,right + enter");
        this.draw = new Draw(500,150, "Zeromanreal.png",1, mängija1);
        this.draw2= new Draw(50,50, "Zeromanreal.png",2, mängija2);
        this.getContentPane().setBackground(new Color(160,233,27));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timerstart1();
        timerstart2();
    }

    public static void main(String[] args) { // #6 Siin loome mänguakna ning loome sellele parameetrid ja lisame sinna mängukarakterid
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                main frame = new main();
                frame.setTitle("Zeroman version 1.1");
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
    public void muudaSkoor1(Draw objekt){
        skoor1++;
        objekt.setSkoor(skoor1);
    }
    public void muudaSkoor2(Draw objekt){
        skoor2++;
        objekt.setSkoor(skoor2);
    }
    public void muudaAsukohad(int xMin, int xMax, int yMin, int yMax, Draw objekt){//selles funktsioonis määrame pärast kokkupõrget karakteritele suvalise asukoha
        objekt.setY(ThreadLocalRandom.current().nextInt(yMin, yMax + 1));
        objekt.setX(ThreadLocalRandom.current().nextInt(xMin, xMax + 1));
    }
}