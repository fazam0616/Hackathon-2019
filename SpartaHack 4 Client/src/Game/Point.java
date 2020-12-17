package Game;

import Window.Main;

public class Point {
    public double x,y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Point cartesianToDisplay(Point pos){
        Point point = new Point(pos.x, pos.y);
        point.x += Window.Main.match.map.map.length/2;
        point.y = Window.Main.match.map.map.length/2 - pos.y;

        return point;
    }
}
