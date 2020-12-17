package Game;

import javax.swing.text.Position;
import java.awt.*;
import java.io.Serializable;

public abstract class Drop implements Serializable {
    public Color color;
    public Position pos;

    public Drop(Color color, Position pos){
        this.color = color;
        this.pos = pos;
    }
    public Drop(){

    }

    public void draw (Graphics g){

    }
}
