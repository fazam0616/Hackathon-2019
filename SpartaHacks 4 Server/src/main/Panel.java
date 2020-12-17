package main;

import game.Player;
import game.Point;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{
    public int x = 500;
    public int y = 350;
    public int w = 20;
    public int h = 20;
    private static Color[] colors = new Color[]{Color.green, Color.gray, new Color(150,75,0)};
    public void paint(Graphics g) {
        if (Main.match!=null){
            for (int x = 0; x < 50; x++) {
                for (int y = 0; y < 35; y++) {
                    g.setColor(colors[Main.match.map.map[x][y]]);
                    g.drawRect(x*20, y*20, 20, 20);
                    g.fillRect(x*20, y*20, 20, 20);
                }
            }
            for (Player player: Main.match.players){
                draw(g, player.pos, player.color, player.username);
            }
        }
    }
    public static void draw(Graphics g, Point pos, Color color, String username) {
        Point point = new Point(pos.x,pos.y);
        g.setColor(Color.black);
        g.drawString(username, (int)(point.x-10), (int)(point.y-10));
        g.setColor(color);
        g.drawOval((int)point.x, (int)point.y, 20, 20);
        g.fillOval((int)point.x, (int)point.y, 20, 20);
        g.setColor(Color.white);
        g.drawOval((int)(point.x-2), (int)(point.y-2), 24, 24);
    }

}
