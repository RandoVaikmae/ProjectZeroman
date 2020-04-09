import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class mainDraw extends JComponent {

    boolean kokkuPorge = false;
    public int x;
    public int y;
    public mainDraw(int x, int y) {
        this.x = x;
        this.y = y;
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
    //public void paintComponent(Graphics g) {
       // super.paintComponent(g);
       // g.drawRect(x, y, 50, 50);
        //g.fillRect(x, y, 50, 50);
       // g.setColor(Color.BLACK);

   // }

    public void moveRight() {
        x = x + 40;
        repaint();
    }

    public void moveLeft() {
        x = x - 40;
        repaint();
    }

    public void moveDown() {
        y = y + 40;
        repaint();
    }

    public void moveUp() {
        y = y - 40;
        repaint();
    }
    public void refresh(){
        repaint();
    }

    public void paint(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        Color roheline = new Color(160,233,27);
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Zeromanoriginal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.setColor(Color.cyan);
        super.paintComponent(g);
        g.drawRect(x, y, 50, 50);
        //g.drawRect(x, y, 180,180);
        g.fillRect(x, y, 50, 50);
        //g.drawImage(img, 0, 0, 100, 100, null);
        if (kokkuPorge) {
            g.drawString("COLLISION", 640, 320);
            kokkuPorge = false;
        }
    }
    public Rectangle bounds(){
        return (new Rectangle(x,y,100,50));
    }


}