import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;


class main extends JFrame implements KeyListener{
    private static int gamemode; //4# Siin klassis pärime funktsioonid Keylistenerilt, et koodi abil oleks võimalik klaviatuuri kaasata.
    private Draw draw;
    private Draw draw2;
    int uuenduskiirus = 500;
    public int skoor1;
    public int skoor2 = 6;
    float cooldown1;
    float cooldown2;
    String mängijaNimi;

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
            int liikumissuund = ThreadLocalRandom.current().nextInt(1, 5 + 1);
            if(liikumissuund==1){
                draw.moveDown();
            }
            else if(liikumissuund==2 || liikumissuund ==5){
                draw.moveLeft();
            }
            else if(liikumissuund==3){
                draw.moveUp();
            }
            else if(liikumissuund==4){
                draw.moveRight();
            }
            if (cooldown2>=0.2) {
                Rectangle rectangle1 = draw.getBounds();
                Rectangle rectangle2 = draw2.getBounds();
                //draw.setPilt("zeroent.png");
                collision(rectangle1, rectangle2, 0);
                if (collisionToimub(rectangle1,rectangle2)){
                    if(skoor2==0){
                        System.out.println("Mäng on läbi, sinu skoor on " + skoor1);
                        System.exit(0);
                    }
                    muudaSkoor2(draw2);
                    muudaAsukohad(50,200,50,500,draw2);
                    muudaAsukohad(300,550,50,500,draw);
                }
                rectangle1.setLocation(0, 0);
                rectangle2.setLocation(500, 500);
                draw2.refresh();
                cooldown2 = 0;
            }


        }
    };
    public void timerstart2(){
        timer2.scheduleAtFixedRate(task2,uuenduskiirus,uuenduskiirus);
    }
    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) { //#5 Siin funktsioonis kontrollime kas nuppu on vajutatud ning millist nuppu on vajutatud
        if(e.getKeyCode()== KeyEvent.VK_D){
            draw2.moveRight();
            draw2.setPilt("zeroman halbert.png");
        }
        else if(e.getKeyCode()== KeyEvent.VK_A){
            draw2.moveLeft();
            draw2.setPilt("zeroman halbertleft.png");
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){
            draw2.moveDown();
        }
        else if(e.getKeyCode()== KeyEvent.VK_W){
            draw2.moveUp();
        }
        else if(e.getKeyCode()== KeyEvent.VK_SPACE){
            if (cooldown1>=0.05) {
                System.out.println("MÄNGIJA LÖÖB");
                Rectangle rectangle1 = draw.getBounds();
                Rectangle rectangle2 = draw2.getBounds();
                draw2.setPilt("zeroman halberthit.png");
                collision(rectangle1, rectangle2, 10);
                if (collisionToimub(rectangle1,rectangle2)){
                    muudaSkoor1(draw);
                    muudaAsukohad(50,500,150,750,draw2);
                    muudaAsukohad(500,800,150,750,draw);
                    if (skoor1>=3 && skoor1<=5) {
                        draw.setPilt("samuraibveri.png");
                        draw2.setMängija1("Samuraiboss");
                    }
                    else if (skoor1>=6 && skoor1<=8) {
                        draw.setPilt("Boss nr2.png");
                        draw2.setMängija1("Kõrvitsamees");
                    }
                    else if (skoor1>=9 && skoor1<=11) {
                        draw.setPilt("bossnr3.png");
                        draw2.setMängija1("Silmamuna");
                    }
                    else if (skoor1>=12) {
                        draw.setPilt("Zeromanreal.png");
                        draw2.setMängija1("Peegelpilt");
                    }
                }
                rectangle1.setLocation(0, 0);
                rectangle2.setLocation(500, 500);
                draw2.refresh();
                System.out.println(cooldown1);
                cooldown1 = 0;
            }
        }
        else if(e.getKeyCode()== KeyEvent.VK_ENTER){
            gamemode += 1;
            if (gamemode == 1){
                    main frame = new main();
                    frame.setTitle("Zeroman version 1.1");
                    frame.setResizable(true);
                    frame.setSize(1000, 1000);
                    frame.setMinimumSize(new Dimension(1000, 1000));
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(frame.draw);
                    frame.pack();
                    frame.getContentPane().add(frame.draw2);
                    frame.pack();
                    frame.setVisible(true);
                }
            }

        }


    public void keyTyped(KeyEvent e) {
    }

    public main(){ // siin kasutame Scannerit, et saada user inputi ning anname rectangletile parameetrid
        if(gamemode==1) {
            String mängija1;
            try {
                String failinimi = "mängijanimi.txt"; //fail nimi kust loetakse nime
                File logFail = new File(failinimi);

                if (!logFail.exists()){
                    Scanner nimed = new Scanner(System.in);
                    System.out.println("Sisesta nimi: ");
                    mängija1 = nimed.nextLine();  // Read user input
                    System.out.println("Sinu kangelasnimi on: " + mängija1.toUpperCase());  // Output user in
                    FileWriter myWriter = new FileWriter(failinimi);
                    myWriter.write(mängija1);
                    myWriter.close();
                    this.draw = new Draw(500, 150, "samuraib.png", 1, mängija1, 150);
                }
                else{
                    FileInputStream fstream = new FileInputStream(failinimi);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    String strLine;
                    while ((strLine = br.readLine()) != null)   {
                        // Print the content on the console
                        System.out.println ("Tere tulemast tagasi " + strLine + "!");
                        this.draw = new Draw(500, 150, "samuraib.png", 1, strLine, 150);
                        break;
                    }

                    fstream.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("Player controls: WASD + space");

            this.draw2 = new Draw(50, 50, "Zeromanreal.png", 2, "Samuraiboss", 90);
            this.getContentPane().setBackground(new Color(160, 233, 27));
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timerstart1();
            timerstart2();
        }else {
            this.draw = new Draw(500, 150, "Zeromanreal.png", 3, "mängija1", 150);
            this.draw2 = new Draw(1000, 1000, "Zeromanreal.png", 3, "mängija2", 50);
            this.getContentPane().setBackground(new Color(255, 0, 0));
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timerstart1();
            timerstart2();
        }
    }


    public static void main(String[] args) { // #6 Siin loome mänguakna ning loome sellele parameetrid ja lisame sinna mängukarakterid
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                    main frame2 = new main();
                    frame2.setTitle("START MENU AKENNN");
                    frame2.setResizable(true);
                    frame2.setSize(1000, 1000);
                    frame2.setMinimumSize(new Dimension(1000, 1000));
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.getContentPane().add(frame2.draw2);
                    frame2.pack();
                    frame2.setVisible(true);
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
        skoor2 = skoor2 - 1;
        objekt.setSkoor(skoor2);
    }
    public void muudaAsukohad(int xMin, int xMax, int yMin, int yMax, Draw objekt){//selles funktsioonis määrame pärast kokkupõrget karakteritele suvalise asukoha
        objekt.setY(ThreadLocalRandom.current().nextInt(yMin, yMax + 1));
        objekt.setX(ThreadLocalRandom.current().nextInt(xMin, xMax + 1));
    }
}