package Game;

import Game.Maps.Map;

import java.io.Serializable;
import java.util.LinkedList;

public class Match implements Serializable {
    public LinkedList<Player> players = new LinkedList<>();
    public LinkedList<Bullet> bullets = new LinkedList<>();
    public Map map;


    public Match(Map map){
        this.map = map;
    }
}
