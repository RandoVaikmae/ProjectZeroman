import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Draw extends JComponent {


    boolean kokkuPorge = false;
    Font myFont = new Font ("Courier New", 1, 30);
    public int x;
    public int y;
    public String pilt;
    public int skoor;
    public int ruutNr;
    public int gameMode;
    public String mängija1;
    public int karakteriSuurus;

    public Draw(int x, int y, String pilt, int ruutNr, String mängija1, int karakteriSuurus) { //2# Siin loome parameetrid, mille abil on hiljem võimalik ruutu(mängukarakterit) disainida(muuta).
        this.x = x;
        this.y = y;
        this.pilt = pilt;
        this.ruutNr = ruutNr;
        this.mängija1 = mängija1;
        this.karakteriSuurus = karakteriSuurus;
    }
    public void setSkoor(int skoor) {
        this.skoor = skoor;
    }

    public int getSkoor() {
        return skoor;
    }

    public void setMängija1(String mängija1) {
        this.mängija1 = mängija1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setKokkuPorge(boolean kokkuPorge) {
        this.kokkuPorge = kokkuPorge;
    }

    public void setPilt(String pilt) {
        this.pilt = pilt;
    }
    public void moveRight() {
        x = x + 25;
        repaint();
    }

    public void moveLeft() {
        x = x - 25;
        repaint();
    }

    public void moveDown() {
        y = y + 25;
        repaint();
    }

    public void moveUp() {
        y = y - 25;
        repaint();
    }
    public void refresh(){
        repaint();
    }

    public void paint(Graphics g) { //3# Siin funktsioonis joonistame kõik vajaliku pildile, loome tekstiväljad ning muudame taustapilti.
        Graphics2D g2d = (Graphics2D) g;
        Color roheline = new Color(160,233,27);
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResourceAsStream(pilt));

        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(Color.black);
        g.setFont(myFont);
        super.paintComponent(g);
        g.drawRect(x, y, karakteriSuurus, karakteriSuurus);
        g.fillRect(x, y, karakteriSuurus, karakteriSuurus);
        g.drawImage(img, x, y, karakteriSuurus+5, karakteriSuurus+5, null);
        if (kokkuPorge) {
            kokkuPorge = false;
        }
        if (ruutNr == 1) {
            g2d.drawString("SCORE: " + skoor, 75, 25);
            g2d.drawString(mängija1, 75, 50);

        }
        if (ruutNr == 2) {
            g2d.drawString("ELUD: "+ "♥".repeat(skoor), 750, 25);
            g2d.drawString(mängija1, 750, 50);
        }
        if (ruutNr == 3) {
            g2d.drawString("Press enter to start...", 275, 650);
            g2d.drawString("Welcome to Zeroman!", 300, 450);
            g2d.drawString("Enter your name below", 275, 100);
            g2d.drawString("Controls: WASD + space", 275, 750);
        }
    }
    public Rectangle bounds(){
        return (new Rectangle(x,y,karakteriSuurus,karakteriSuurus));
    }


}