import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class mainDraw extends JComponent {


    public int x = 50;
    public int y = 50;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(x, y, 50, 50);
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.BLACK);

    }

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

    public void paint(Graphics g) {
        Color roheline = new Color(160,233,27);
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Zeromanreal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.setColor(roheline);
        super.paintComponent(g);

        g.drawRect(x, y, 1080, 1000);
        g.fillRect(x, y, 1800, 1000);
        g.drawImage(img, 100, 100, 100, 100, null);

    }
}