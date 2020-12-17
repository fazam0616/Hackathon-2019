package main;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements KeyListener{
    public Panel panel;
    public String pressed = "";
    public Window() {
        super();
        this.addKeyListener(this);
        this.setSize(1000, 700);
        panel = new Panel();
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Server");
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());
        if (key.equals("W")||key.equals("A")||key.equals("S")||key.equals("D")){
            if (!pressed.contains(key))
                pressed += key;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());
        pressed = pressed.replaceAll(key,"");
    }
}
