package Game;

import Game.Guns.Gun;
import Window.Main;

import java.awt.*;
import java.io.Serializable;

public class Player implements Serializable {
    public double speed = 1;
    public int health = 100;
    public Gun[] guns;
    public Point pos;
    public Color color;
    public String username;

    public Player(String name, Color color, Point pos){
        this.username = name;
        this.color = color;
        this.pos = pos;
    }

    public void updateCollision(){
        Point convertedPoint = Point.cartesianToDisplay(this.pos);
        if (Main.match.map.map[(int)(convertedPoint.x/20)][(int)(convertedPoint.x/20)] != 0){
            double changeX = (pos.x - convertedPoint.x);
            double changeY = (pos.y - convertedPoint.y);

            if (Math.abs(changeX) > Math.abs(changeY)){
                if (changeX>0){
                    pos.x += 1;
                }else{
                    pos.x -= 1;
                }
            }else{
                if (changeY>0){
                    pos.y += 1;
                }else{
                    pos.y -= 1;
                }
            }
        }
    }
}
