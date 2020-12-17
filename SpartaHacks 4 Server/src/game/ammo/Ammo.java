package game.ammo;

public abstract class Ammo{
    public double damage; // Out of 100 HP
    public double speed; // Measured in px/tick
    public int amount; // Measure of how many there are

    public Ammo (double damage, double speed, int amount){
        this.damage = damage;
        this.speed = speed;
        this.amount = amount;
    }
}
