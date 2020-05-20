import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Menuu extends JPanel {

    public Menuu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(280));
        //JFrame.getContentPane().setBackground( Color.red );

        add(Box.createVerticalGlue());

        try {
            URL url = Menu.class.getResource("music.wav");
            AudioInputStream audio = (AudioInputStream) AudioSystem.getClip();
        }   catch (Exception ex) {}
    }

    }

