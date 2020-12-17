package Game;

import Window.Main;

public class Bullet {
    public double rate;
    public double damage;
    public double angle;
    public Point pos;

    public Bullet(Point pos,double rate, double damage, double angle){
        this.rate = rate;
        this.damage = damage;
        this.angle = angle;
        this.pos = pos;
    }

    public void update(){
        this.pos.x += Math.cos(Math.toRadians(angle))*this.rate;
        this.pos.y += Math.sin(Math.toRadians(angle))*this.rate;

        Point convertedPoint = Point.cartesianToDisplay(pos);
        if (Main.match.map.map[(int)convertedPoint.x][(int)convertedPoint.x] != 0){
            int index = Main.match.bullets.indexOf(this);
            Main.match.bullets.remove(index);
        }
    }
}
