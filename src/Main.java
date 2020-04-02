import javax.swing.*;

public class Main {
    public Main(){
        JFrame frame = new JFrame();
        GameWindow gameWindow = new GameWindow();

        frame.add(gameWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Zeroman");
        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args){

        new Main();

    }

}
