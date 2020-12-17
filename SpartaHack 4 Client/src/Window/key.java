package Window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class key extends KeyAdapter {

    public void keyPressed(KeyEvent event) {
        System.out.println(event.getKeyCode());

    }

}
