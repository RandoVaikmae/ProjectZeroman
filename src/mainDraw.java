import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class mainDraw extends JComponent {

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
}
