package game.guns;

import game.ammo.Ammo;
import game.Drop;

public abstract class Gun extends Drop{
    public Ammo ammo;
    public double rate;

    public Gun(Ammo ammo, double rate){
        super();
        this.ammo = ammo;
        this.rate = rate;
    }
}
