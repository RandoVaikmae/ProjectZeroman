import javax.swing.*;
import java.awt.*;

import javax.swing.JPanel;

public class GameWindow extends JPanel{

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1020, HEIGHT= 640;

    public GameWindow(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

}
