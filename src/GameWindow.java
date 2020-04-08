import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class GameWindow extends JPanel {


    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1020, HEIGHT = 640;

    public GameWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

    public void paint(Graphics g) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/csgocar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Color darkGreen = new Color(27, 173, 10);
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(darkGreen);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(img, 200, 200, 200, 200, null);

    }


}

