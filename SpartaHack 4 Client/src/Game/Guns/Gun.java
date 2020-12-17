package Game.Guns;

import Game.Ammo.Ammo;
import Game.Drop;

import java.io.Serializable;

public abstract class Gun extends Drop{
    public Ammo ammo;
    public double rate;

    public Gun(Ammo ammo, double rate){
        super();
        this.ammo = ammo;
        this.rate = rate;
    }
}
