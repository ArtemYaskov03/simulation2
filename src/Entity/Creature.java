package Entity;

import WorldMap.*;

public abstract class Creature extends Entity {
    public int speed;
     public int hp;
    public abstract void makeMove(WorldMap worldMap);
}
