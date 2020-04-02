import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
public class Main {

    public Main () {


        JFrame frame = new JFrame();
        GameWindow gameWindow = new GameWindow();

        frame.add(gameWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Zeroman");
        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
        System.out.println("OKei");


    }


    public static void main(String[] args) {



        new Main();

    }
}

